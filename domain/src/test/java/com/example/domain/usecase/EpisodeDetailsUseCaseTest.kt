package com.example.domain.usecase

import domain.mapper.EpisodeDetailsMapper
import domain.repository.EpisodeDetailsRepository
import domain.usecase.EpisodeDetailsUseCase
import io.mockk.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Before

@ExperimentalCoroutinesApi
class EpisodeDetailsUseCaseTest {

    private lateinit var episodeDetailsRepository: EpisodeDetailsRepository
    private lateinit var episodeDetailsUseCase: EpisodeDetailsUseCase

    @Before
    fun setUp() {
        episodeDetailsRepository = mockk()
        episodeDetailsUseCase = EpisodeDetailsUseCase(episodeDetailsRepository)
    }

    @Test
    fun `test successful data retrieval`() = runBlocking {
        val episodeDetails = EpisodeDetailsMapper(
            id = "1",
            name = "Test Episode",
            airDate = "2025-01-01",
            characters = listOf(
                EpisodeDetailsMapper.Character(id = "1", name = "Test Character", image = "image_url")
            )
        )
        coEvery { episodeDetailsRepository.getEpisodeDetailsById("1") } returns Result.success(episodeDetails)
        val result = episodeDetailsUseCase.invoke("1")
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), episodeDetails)
        coVerify { episodeDetailsRepository.getEpisodeDetailsById("1") }
    }

    @Test
    fun `test error when repository returns an error`() = runBlocking {
        coEvery { episodeDetailsRepository.getEpisodeDetailsById("1") } returns Result.failure(Exception("Error"))
        val result = episodeDetailsUseCase.invoke("1")
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is Exception)
        coVerify { episodeDetailsRepository.getEpisodeDetailsById("1") }
    }

    @Test
    fun `test null data from repository`() = runBlocking {
        coEvery { episodeDetailsRepository.getEpisodeDetailsById("1") } returns Result.failure(Exception("Character details not found"))
        val result = episodeDetailsUseCase.invoke("1")
        assertTrue(result.isFailure)
        assertEquals(result.exceptionOrNull()?.message, "Character details not found")
        coVerify { episodeDetailsRepository.getEpisodeDetailsById("1") }
    }
}
