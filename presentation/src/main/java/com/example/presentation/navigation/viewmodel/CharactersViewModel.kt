package com.example.presentation.navigation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation.navigation.uistate.UiState
import domain.usecase.CharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel
@Inject
constructor(
    private val charactersUseCase: CharacterUseCase,
) : ViewModel() {

    private val _charactersState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Empty)
    val charactersState: StateFlow<UiState> get() = _charactersState

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            try {
                _charactersState.value = UiState.Loading
                val characters = charactersUseCase.invokeCharacters()

                _charactersState.value = if (characters.isNotEmpty()) {
                    UiState.Success(characters)
                } else {
                    UiState.Empty
                }
            } catch (exception: Exception) {
                _charactersState.value = UiState.Error(exception.localizedMessage ?: "Unknown error")
            }
        }
    }
}

