package com.example.presentation.navigation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation.navigation.uistate.UiState
import domain.mapper.EpisodeDetails
import domain.mapper.toEpisodeDetails
import domain.usecase.EpisodeDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

import android.util.Log

class EpisodeDetailsViewModel @Inject constructor(
    private val episodeDetailsUseCase: EpisodeDetailsUseCase
) : ViewModel() {

    private val _episodeDetails: MutableStateFlow<UiState<EpisodeDetails>> =
        MutableStateFlow(UiState.Loading)
    val episodeDetails: StateFlow<UiState<EpisodeDetails>> = _episodeDetails

    var episodeTitleForTopBar = ""
        private set

    fun getEpisodeDetailsByUsingEpisodeId(episodeId: String) {
        Log.d("EpisodeDetailsViewModel", "Fetching details for Episode ID: $episodeId")

        viewModelScope.launch {
            val result = episodeDetailsUseCase.invoke(episodeId)

            result.onSuccess { episodeDetailsMapper ->
                Log.d("EpisodeDetailsViewModel", "Episode details fetched: $episodeDetailsMapper")

                episodeTitleForTopBar = episodeDetailsMapper.name ?: ""
                _episodeDetails.value = UiState.Success(episodeDetailsMapper.toEpisodeDetails())
            }.onFailure { error ->
                Log.e("EpisodeDetailsViewModel", "Error fetching episode details: ${error.message}")
                _episodeDetails.value = UiState.Error(Exception("Episode Details are Empty"))
            }
        }
    }
}





