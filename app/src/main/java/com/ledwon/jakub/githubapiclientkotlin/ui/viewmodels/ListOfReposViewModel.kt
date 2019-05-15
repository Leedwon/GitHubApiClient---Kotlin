package com.ledwon.jakub.githubapiclientkotlin.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ledwon.jakub.githubapiclientkotlin.data.GitHubRepository
import com.ledwon.jakub.githubapiclientkotlin.data.model.Repo
import com.ledwon.jakub.githubapiclientkotlin.utils.NetworkUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ListOfReposViewModel @Inject constructor(private var repository: GitHubRepository) : ViewModel() {
    var tag = "ListOfReposViewModel"
    private var disposable = CompositeDisposable()

    private var mRepos = MutableLiveData<List<Repo>>()
    var repos : LiveData<List<Repo>> = mRepos
    private var mUserFound = MutableLiveData<Boolean>()
    var userFound : LiveData<Boolean> = mUserFound

    fun fetchRepos(username: String) =
        disposable.add(
            repository.getListOfRepos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Repo>>() {
                    override fun onSuccess(t: List<Repo>) {
                        mRepos.value = t
                        mUserFound.value = true
                    }
                    override fun onError(e: Throwable) {
                        if (Regex(NetworkUtils.HTTP_NOT_FOUND.toString()).containsMatchIn(e.message ?: return))
                            mUserFound.value = false
                    }
                })
        )

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}