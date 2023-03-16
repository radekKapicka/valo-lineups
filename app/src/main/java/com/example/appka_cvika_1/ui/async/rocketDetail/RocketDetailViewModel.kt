package com.example.appka_cvika_1.ui.async.rocketDetail

import com.example.appka_cvika_1.base.BaseViewModel
import com.example.appka_cvika_1.data.model.response.RocketDetailResponse
import com.example.appka_cvika_1.data.repository.SpeceXRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RocketDetailViewModel(
    private val rocketId: String,
    private val spaceXRepository: SpeceXRepository
):BaseViewModel() {

    private val _rocketDetailState = MutableStateFlow<RocketDetailResponse?>(null)
    val rocketDetailState = _rocketDetailState.asStateFlow()

    init {
        fetchRocketDetail()
    }

    fun fetchRocketDetail() = launch (
        block = {
            spaceXRepository.fetchRocketDetail(rocketId).also { rocketDetail ->
                _rocketDetailState.emit(rocketDetail)
            }
        }
    )

}