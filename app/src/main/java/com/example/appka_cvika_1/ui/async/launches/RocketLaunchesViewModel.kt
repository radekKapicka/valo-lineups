package com.example.appka_cvika_1.ui.async.launches

import com.example.appka_cvika_1.base.BaseViewModel
import com.example.appka_cvika_1.data.model.response.RocketLaunchResponse
import com.example.appka_cvika_1.data.repository.SpeceXRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RocketLaunchesViewModel(
    private val spaceXRepository: SpeceXRepository
): BaseViewModel() {

    private val _successRocketLaunches = MutableStateFlow<List<RocketLaunchResponse>>(emptyList())
    val successRocketLaunches = _successRocketLaunches.asStateFlow()

    init {
        fetchSuccessRocketLaunches()
    }

    private fun fetchSuccessRocketLaunches() = launch(
        block = {
            spaceXRepository.fetchAllSuccessfulLaunches().also {
                _successRocketLaunches.emit(it)
            }
        }
    )

}