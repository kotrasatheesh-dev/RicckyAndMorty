package com.example.presentation.navigation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation.navigation.uistate.UiState
import domain.usecase.CharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
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
            charactersUseCase.invokeCharacters()
                .onStart {
                    // Emit loading state
                    _charactersState.emit(UiState.Loading)
                }
                .catch { exception ->
                    // Emit error state with the exception message
                    _charactersState.emit(UiState.Error(exception.message ?: "Unknown error"))
                }
                .collect { characters ->
                    // Emit success state with the collected characters
                    _charactersState.emit(UiState.Success(characters))
                }
        }
    }
}

