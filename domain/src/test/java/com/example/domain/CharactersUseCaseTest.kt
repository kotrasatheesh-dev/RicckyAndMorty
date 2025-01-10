package com.example.domain

import domain.model.Character
import domain.repository.CharactersRepository
import domain.usecase.CharacterUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersUseCaseTest {
    private lateinit var useCase: CharacterUseCase
    private val repository: CharactersRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = CharacterUseCase(repository)
    }

    @Test
    fun `invokeCharacters should return characters from mocked repository`() = runTest(testDispatcher) {
        // Mocked data
        val mockedCharacters = listOf(
            Character("1", "Rick Sanchez", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
            Character("2", "Morty Smith", "https://rickandmortyapi.com/api/character/avatar/2.jpeg")
        )

        // Stubbing the mocked repository
        coEvery { repository.getCharacters() } returns mockedCharacters

        // Use case result
        val result = useCase.invokeCharacters()

        // Assertion
        assertEquals(mockedCharacters, result)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
