package com.example.domain.usecase

import com.google.common.truth.Truth.assertThat
import domain.mapper.CharacterDetailsMapper
import domain.repository.CharacterDetailsRepository
import domain.repository.Character
import domain.usecase.CharacterDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TestCharacterDetailsUseCase {

    private lateinit var useCase: CharacterDetailsUseCase
    private lateinit var repository: CharacterDetailsRepository

    @Before
    fun setup() {
        repository = mockk() // Mock the repository
        useCase = CharacterDetailsUseCase(repository) // Inject the mock into the use case
    }

    @Test
    fun `given valid character details, when invoke is called, then returns character details`() = runTest {
        // Given
        val characterId = "1"
        val expectedDetails = CharacterDetailsMapper(
            id = "1",
            name = "Rick Sanchez",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            status = "Alive",
            species = "Human",
            gender = "Male",
            originName = "Earth (C-137)",
            originDimension = "Dimension C-137",
            locationName = "Citadel of Ricks",
            locationDimension = "Interdimensional",
            episodes = listOf(
                Character.Episode(
                    "Pilot", "https://rickandmortyapi.com/api/episode/1",
                    airDate = "December 2, 2013"
                )
            )
        )
        coEvery { repository.getCharacterDetailsById(characterId) } returns Result.success(expectedDetails)

        // When
        val result = useCase.invoke(characterId)

        // Then
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(expectedDetails)
        coVerify { repository.getCharacterDetailsById(characterId) }
    }

    @Test
    fun `given invalid character ID, when invoke is called, then returns failure`() = runTest {
        // Given
        val characterId = "999"
        coEvery { repository.getCharacterDetailsById(characterId)
        } returns Result.failure(Throwable("Character not found"))

        // When
        val result = useCase.invoke(characterId)

        // Then
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()?.message).isEqualTo("Character not found")
        coVerify { repository.getCharacterDetailsById(characterId) }
    }
}
