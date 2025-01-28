package com.example.presentation

import android.util.Log
import app.cash.turbine.test
import com.example.presentation.navigation.uistate.UiState
import com.example.presentation.navigation.viewmodel.CharactersViewModel
import com.google.common.truth.Truth.assertThat
import domain.usecase.CharacterUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class CharactersViewModelTest {
    private var useCase: CharacterUseCase = mockk(relaxed = true)
    private lateinit var charactersViewModel: CharactersViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic(Log::class)
        every { Log.isLoggable(any(), any()) } returns false
        Dispatchers.setMain(testDispatcher)
        charactersViewModel = CharactersViewModel(useCase)
    }

    @Test
    fun `given valid characters, when fetchData is called, then emits loading and success states`() =
        runTest(testDispatcher) {
            // Arrange
            val mockCharacters = getMockCharacters()
            coEvery { useCase.invoke() } returns flowOf(mockCharacters)

            // Act & Assert
            charactersViewModel.charactersState.test {
                charactersViewModel.fetchData()
                assertThat(awaitItem()).isEqualTo(UiState.Loading)
                advanceUntilIdle()
                val successState = awaitItem()
                assertThat(successState).isInstanceOf(UiState.Success::class.java)
                val actualData = (successState as UiState.Success).data
                assertThat(actualData).isEqualTo(mockCharacters)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given useCase throws error, when fetchData is called, then emits loading and error states`() =
        runTest(testDispatcher) {
            // Arrange
            val errorMessage = "Network error"
            coEvery { useCase.invoke() } throws Exception(errorMessage)

            // Act & Assert
            charactersViewModel.charactersState.test {
                charactersViewModel.fetchData()
                assertThat(awaitItem()).isEqualTo(UiState.Loading)
                advanceUntilIdle()
                val errorState = awaitItem()
                assertThat(errorState).isInstanceOf(UiState.Error::class.java)
                assertThat((errorState as UiState.Error).exception.message).isEqualTo(errorMessage)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }



}
