package com.example.presentation.navigation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation.navigation.uistate.UiState
import domain.mapper.CharacterDetailsMapper
import domain.usecase.CharacterDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    private val characterDetailsUseCase : CharacterDetailsUseCase
) : ViewModel() { private val _characterDetails : MutableStateFlow<UiState<CharacterDetailsMapper>>
= MutableStateFlow(UiState.Loading)
    val characterDetails : StateFlow<UiState<CharacterDetailsMapper>> = _characterDetails
    var characterNameForTopBar= ""
        private set

    fun getCharacterDetails(characterId : String){
        viewModelScope.launch {
          val result = characterDetailsUseCase.invoke(characterId)
            result.onSuccess {characterDetails->
                characterNameForTopBar = characterDetails.name?:""
                _characterDetails.emit(UiState.Success(characterDetails))
            }.onFailure {
                _characterDetails.emit(UiState.Error(Exception("Character Details are Empty")))
            }

        }
    }

    fun getCharacterName(): String {
        return characterNameForTopBar
    }
}
