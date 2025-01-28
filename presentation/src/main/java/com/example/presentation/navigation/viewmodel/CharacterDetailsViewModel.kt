package com.example.presentation.navigation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.presentation.navigation.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    private val characterDetailsUseCase: CharacterDetailsUseCase
) : ViewModel() {
    private val _characterDetails: MutableStateFlow<UiState<CharacterDetailsMapper>> = MutableStateFlow(UiState.Loading)
    val characterDetails: StateFlow<UiState<CharacterDetailsMapper>> = _characterDetails
    private var _characterNameForTopBar = ""
    val characterNameForTopBar: String get() = _characterNameForTopBar
    fun getCharacterDetails(characterId: String) {
        viewModelScope.launch {
            val result = characterDetailsUseCase.invoke(characterId)
            result.fold(
                onSuccess = { characterDetails ->

                    _characterNameForTopBar = characterDetails.name.orEmpty()
                    _characterDetails.emit(UiState.Success(characterDetails))
                },
                onFailure = { exception ->
                    _characterDetails.emit(UiState.Error(Exception("Failed to load character details", exception)))
                }
            )
        }
    }

}
