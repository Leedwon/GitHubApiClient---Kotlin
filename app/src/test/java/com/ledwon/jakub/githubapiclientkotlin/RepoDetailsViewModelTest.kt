package com.ledwon.jakub.githubapiclientkotlin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ledwon.jakub.githubapiclientkotlin.data.GitHubRepository
import com.ledwon.jakub.githubapiclientkotlin.data.model.Repo
import com.ledwon.jakub.githubapiclientkotlin.data.model.RepoOwner
import com.ledwon.jakub.githubapiclientkotlin.ui.viewmodels.RepoDetailsViewModel
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepoDetailsViewModelTest {
    @get:Rule
    var testSchedulerRule = RxSchedulerRule()

    @get:Rule
    var taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var gitHubRepository : GitHubRepository

    @InjectMocks
    lateinit var viewModel : RepoDetailsViewModel

    private var username = "username"
    private var repoName = "repoName"

    @Test
    fun receiveSuccessfulResponse() : Unit {
        val responseRepoMock = Repo("", RepoOwner(""), false, 5, 5,"", "","")
        Mockito.`when`(gitHubRepository.getRepo(anyString(), anyString())).thenReturn(Single.just(responseRepoMock))

        viewModel.fetchRepo(username, repoName)
        viewModel.repoFound.observeForever { repoFound -> assertTrue(repoFound) }
        viewModel.repo.observeForever { repo -> assertEquals(repo, responseRepoMock) }
    }

    @Test
    fun receiveRepoNotFound() : Unit {
        val message = "HTTP 404 Not Found"
        Mockito.`when`(gitHubRepository.getRepo(anyString(), anyString())).thenReturn(Single.error(Throwable(message)))

        viewModel.fetchRepo(username, repoName)
        viewModel.repoFound.observeForever { repoFound -> assertFalse(repoFound) }
        viewModel.repo.observeForever { repo -> assertNull(repo) }
    }
}