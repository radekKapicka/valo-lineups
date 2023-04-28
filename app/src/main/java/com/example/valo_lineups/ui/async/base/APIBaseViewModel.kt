package com.example.valo_lineups.ui.async.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


abstract class APIBaseViewModel: ViewModel() {


    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)

    private val _state = MutableStateFlow<State>(State.None)
    val state = _state.asStateFlow()

    /**
     * @param block     Your block of application suspending instructions, ex.: api call,
     *                  calculations or any operation that should not be processed on the main th.
     * @param state     State where are the lifecycle of launch is emitted. You can insert null
     *                  if you don't care about the lifecycle of the block.
     * @param onError   Optional onError method that is called extra when error appears.
     *
     * @return          Coroutine Job that can be operated with from anywhere, ex.: Cancelling when
     *                  the result of job is not needed anymore due to changes in the app.
     *
     * @sample          `class MyViewModel(private val api: ApiService) : ComposeViewModel() {
     *                      fun fetchDataWithState() {
     *                          launch {
     *                              // in state.collect {...} will appear Loading, [Success|Failure]
     *                              val result = api.fetchData()
     *                          }
     *                      }
     *                      fun fetchDataWithoutState() {
     *                          launch(state = null, onError = { /* Here I can solve exceptions */}) {
     *                              // in state.collect will appear nothing
     *                              val result = api.fetchData()
     *                          }
     *                      }
     *                  }
     */
    protected fun <Result> launch(
        onError: ((Throwable) -> Unit)? = null,
        state: MutableStateFlow<State>? = _state,
        block: (suspend CoroutineScope.() -> Result),
    ) = scope.launch(throwableHandler(onError, state, block)) {

        // 1) Show loading via emitted in state
        state?.emit(State.Loading)

        // 2) Process operation (providing result in block is not necessary but can be useful)
        val result = block()

        // 3) Create Success and emit the possible result if state available
        state?.emit(State.Success(result))
    }

    /**
     * Method that handles the exceptions via [CoroutineExceptionHandler] and handles proper
     * delegation of exception to state or onError method when provided.
     */
    private fun <Result> throwableHandler(
        onError: ((Throwable) -> Unit)?,
        state: MutableStateFlow<State>?,
        block: suspend CoroutineScope.() -> Result,
    ) = CoroutineExceptionHandler { _, throwable ->
        onError?.invoke(throwable)
        state?.tryEmit(
            State.Failure(
                throwable = throwable,
                repeat = {
                    repeat(onError, state, block) // To avoid recursive problem
                }
            )
        )
    }

    /**
     * This method is declared only as a solution to avoid recursive problem in Kotlin
     * when adding action of repeat to Failure in throwableHandler
     */
    private fun <Result> repeat(
        onError: ((Throwable) -> Unit)?,
        state: MutableStateFlow<State>?,
        block: suspend CoroutineScope.() -> Result,
    ) {
        launch(onError, state, block)
    }



}