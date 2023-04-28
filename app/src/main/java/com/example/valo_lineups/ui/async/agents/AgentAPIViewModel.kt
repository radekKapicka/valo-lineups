package com.example.valo_lineups.ui.async.agents

import com.example.valo_lineups.data.model.response.AgentDetailResponse
import com.example.valo_lineups.data.repository.ValoRepository
import com.example.valo_lineups.ui.async.base.APIBaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AgentAPIViewModel(
    private val agentId: String,
    private val valoRepository: ValoRepository
) : APIBaseViewModel() {

    private val _agentDetailState = MutableStateFlow<AgentDetailResponse?>(null)
    val agentDetailState get() = _agentDetailState.asStateFlow()

    init {
        fetchAgentDetail()
    }

    private fun fetchAgentDetail() = launch(
        block = {
            valoRepository.fetchAgentDetail(agentId).also {
                _agentDetailState.emit(it)
            }
        },
    )

}