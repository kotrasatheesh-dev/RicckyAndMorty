package com.rickyandmonty.di.presentation.viewState

import androidx.paging.PagingData
import com.example.common.GetCharactersQuery
import kotlinx.coroutines.flow.Flow

sealed class ViewState {
    data object Empty : ViewState()

    data object Loading : ViewState()

    data class Success(
        val data: Flow<PagingData<GetCharactersQuery.Result>>,
    ) : ViewState()

    data class Error(
        val exception: Exception,
    ) : ViewState()
}
