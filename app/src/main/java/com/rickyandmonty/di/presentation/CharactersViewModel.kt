package com.example.rickandmorty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.presentation.uistate.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import usecases.CharacterUseCase
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
            viewModelScope.launch(Dispatchers.IO) {
                charactersUseCase
                    .invokeCharacters()
                    .onStart {
                        _charactersState.emit(UiState.Loading)
                    }.catch { _charactersState.emit(UiState.Empty) }
                    .collect {
                        _charactersState.emit(UiState.Success(charactersUseCase.invokeCharacters()))
                    }
            }
        }
    }
