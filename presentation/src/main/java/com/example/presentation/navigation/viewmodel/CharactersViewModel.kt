package com.example.presentation.navigation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation.navigation.uistate.UiState
import domain.repository.Character
import domain.usecase.CharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val charactersUseCase: CharacterUseCase,
) : ViewModel() {

    private val _charactersState: MutableStateFlow<UiState<List<Character>>> =
        MutableStateFlow(UiState.Loading)

    val charactersState: StateFlow<UiState<List<Character>> > get() = _charactersState

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            _charactersState.value = UiState.Loading
            try {
                val characters = charactersUseCase.invokeCharacters()
                if (characters.isNotEmpty()) {
                    _charactersState.value = UiState.Success(characters)
                } else {
                    _charactersState.value = UiState.Error(Exception("No characters found"))
                }
            } catch (e: Exception) {
                _charactersState.value = UiState.Error(e)
            }
        }
    }
}


