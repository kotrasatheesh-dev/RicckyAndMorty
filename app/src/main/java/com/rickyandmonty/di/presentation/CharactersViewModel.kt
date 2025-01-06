package com.rickyandmonty.di.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickyandmonty.di.presentation.viewState.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import com.example.domain.domain.usecase.usecase.CharacterUseCase
import javax.inject.Inject

class CharactersViewModel
    @Inject
    constructor(
        private val charactersUseCase: CharacterUseCase,
    ) : ViewModel() {
        private val _charactersState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Empty)
        val charactersState: StateFlow<ViewState> get() = _charactersState

        init {
            fetchData()
        }

        fun fetchData() {
            viewModelScope.launch(Dispatchers.IO) {
                charactersUseCase
                    .invokeCharacters()
                    .onStart {
                        _charactersState.emit(ViewState.Loading)
                    }.catch { _charactersState.emit(ViewState.Empty) }
                    .collect {
                        _charactersState.emit(ViewState.Success(charactersUseCase.invokeCharacters()))
                    }
            }
        }
    }
