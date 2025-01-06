package com.example.presentation.uistate

import com.example.common.GetCharactersQuery
import kotlinx.coroutines.flow.Flow

sealed class UiState {
    data object Empty : UiState()

    data object Loading : UiState()

    data class Success(
        val data: Flow<List<GetCharactersQuery.Result>>, // Changed type
    ) : UiState()

    data class Error(
        val exception: Exception,
    ) : UiState()
}
