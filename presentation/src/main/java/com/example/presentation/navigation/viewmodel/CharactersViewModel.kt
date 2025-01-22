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
            _charactersState.value = UiState.Loading
            val characters = charactersUseCase.invokeCharacters()
            if (characters.isNotEmpty()) {
                _charactersState.value = UiState.Success(characters)
            } else {
                _charactersState.value = UiState.Error("No characters found")
            }
        }
    }
}

