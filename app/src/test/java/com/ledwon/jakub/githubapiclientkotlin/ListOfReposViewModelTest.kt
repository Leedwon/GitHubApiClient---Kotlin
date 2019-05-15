package com.ledwon.jakub.githubapiclientkotlin


import com.ledwon.jakub.githubapiclientkotlin.data.GitHubRepository
import com.ledwon.jakub.githubapiclientkotlin.ui.viewmodels.ListOfReposViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ledwon.jakub.githubapiclientkotlin.data.model.Repo
import com.ledwon.jakub.githubapiclientkotlin.data.model.RepoOwner
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mockito

@RunWith(MockitoJUnitRunner::class)
class ListOfReposViewModelTest {
    @get:Rule
    var testSchedulerRule = RxSchedulerRule()

    @get:Rule
    var taskExecutorRule = InstantTaskExecutorRule()


    @Mock
    lateinit var gitHubRepository: GitHubRepository

    @InjectMocks
    lateinit var viewModel: ListOfReposViewModel


    var username = "username"

    @Test
    fun receiveSuccessfulResponse(): Unit {
        val repoMock = Repo("", RepoOwner(""), false, 0, 0, "", "", "")
        val repoListMock = ArrayList<Repo>()
        repoListMock.add(repoMock)

        Mockito.`when`(gitHubRepository.getListOfRepos(anyString())).thenReturn(Single.just(repoListMock))
        viewModel.fetchRepos(username)
        viewModel.repos.observeForever { list -> assertEquals(list, repoListMock) }
        viewModel.userFound.observeForever { userFound -> assertTrue(userFound) }
    }

    @Test
    fun receiveFailedResponse(): Unit {
        val errorMessage = "HTTP 404 Not Found"
        Mockito.`when`(gitHubRepository.getListOfRepos(anyString())).thenReturn(Single.error(Throwable(errorMessage)))

        viewModel.fetchRepos(username)
        viewModel.repos.observeForever { list -> assertNull(list) }
        viewModel.userFound.observeForever { userFound -> assertFalse(userFound) }
    }
}