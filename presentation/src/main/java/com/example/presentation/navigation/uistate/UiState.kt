package com.example.presentation.navigation.uistate

import com.exmple.rickandmorty.GetCharactersQuery
import domain.repository.Character

sealed class UiState {
    data object Empty : UiState()

    data object Loading : UiState()

    data class Success(
        val data: List<Character>
    ) : UiState()

    data class Error(
        val exception: String,
    ) : UiState()
}
