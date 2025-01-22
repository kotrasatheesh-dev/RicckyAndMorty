package com.example.domain.usecase

import com.google.common.truth.Truth.assertThat
import domain.repository.Character
import domain.repository.CharactersRepository
import domain.usecase.CharacterUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TestCharactersUseCase {

    private lateinit var useCase: CharacterUseCase
    private lateinit var repository: CharactersRepository

    @Before
    fun setup() {
        repository = mockk() // Mock the repository
        useCase = CharacterUseCase(repository) // Inject the mock into the use case
    }

    @Test
    fun `given valid characters, when Characters is called, then returns character list`() = runTest {
        // Given
        val expectedCharacters = listOf(
            Character(
                id = "1",
                name = "Rick Sanchez",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
            ),
            Character(
                id = "2",
                name = "Morty Smith",
                image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
            )
        )
        coEvery { repository.getCharacters() } returns expectedCharacters
        val result = useCase.invokeCharacters()
        assertThat(result).isEqualTo(expectedCharacters)
        coVerify { repository.getCharacters() }
    }

    @Test
    fun `given no characters, when Characters is called, then returns empty list`() = runTest {
        coEvery { repository.getCharacters() } returns emptyList()
        val result = useCase.invokeCharacters()
        assertThat(result).isEmpty()
        coVerify { repository.getCharacters() }
    }
}
