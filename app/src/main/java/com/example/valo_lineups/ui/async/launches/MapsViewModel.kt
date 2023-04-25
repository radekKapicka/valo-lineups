package com.example.valo_lineups.ui.async.launches

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewModelScope
import com.example.valo_lineups.base.BaseViewModel
import com.example.valo_lineups.data.model.response.MapsResponse
import com.example.valo_lineups.data.repository.ValoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class MapsViewModel(
    private val valoRepository: ValoRepository
): BaseViewModel() {

    private val _maps = MutableStateFlow<List<MapsResponse>>(emptyList())
    val maps = _maps.asStateFlow()

    init {
        fetchMaps()
    }

    private fun fetchMaps() = launch(
        block = {
            valoRepository.fetchAllMaps().also {
                _maps.emit(it)
            }
        }
    )

}
