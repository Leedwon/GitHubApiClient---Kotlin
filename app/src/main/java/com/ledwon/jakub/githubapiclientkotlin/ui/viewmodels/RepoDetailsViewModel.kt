package com.ledwon.jakub.githubapiclientkotlin.ui.viewmodels

import android.util.Log
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

class RepoDetailsViewModel @Inject constructor(private var repository : GitHubRepository) : ViewModel() {
    companion object {
        val TAG = "RepoDetailsViewModel"
    }
    private var disposable = CompositeDisposable()

    private var mRepo = MutableLiveData<Repo>()
    var repo : LiveData<Repo> = mRepo
    private var mRepoFound = MutableLiveData<Boolean>()
    var repoFound : LiveData<Boolean> = mRepoFound
    private var mLoading = MutableLiveData<Boolean>()
    var loading : LiveData<Boolean> = mLoading

    init {
        mLoading.value = true
    }

    fun fetchRepo(username: String, repoName : String) =
        disposable.add(
            repository.getRepo(username, repoName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Repo>() {
                    override fun onSuccess(t: Repo) {
                        mRepo.value = t
                        mRepoFound.value = true
                        mLoading.value = false
                        Log.d(TAG, mRepo.value.toString() + "when username = $username and repoName = $repoName")
                    }

                    override fun onError(e: Throwable) {
                        if (Regex(NetworkUtils.HTTP_NOT_FOUND.toString()).containsMatchIn(e.message ?: return)) {
                            mRepoFound.value = false
                            mLoading.value = false
                            Log.d(TAG, e.message + "when username = $username and repoName = $repoName")
                        }

                    }
                })
        )

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}