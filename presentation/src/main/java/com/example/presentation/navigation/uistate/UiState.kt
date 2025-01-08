package com.example.presentation.navigation.uistate

import com.exmple.rickandmorty.GetCharactersQuery
import kotlinx.coroutines.flow.Flow

sealed class UiState {
    data object Empty : UiState()

    data object Loading : UiState()

    data class Success(
        val data: Flow<List<GetCharactersQuery.Result>>
    ) : UiState()

    data class Error(
        val exception: Exception,
    ) : UiState()
}
