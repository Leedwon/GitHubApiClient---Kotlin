package com.ledwon.jakub.githubapiclientkotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ledwon.jakub.githubapiclientkotlin.R
import com.ledwon.jakub.githubapiclientkotlin.data.model.Repo
import com.ledwon.jakub.githubapiclientkotlin.di.ViewModelFactory
import com.ledwon.jakub.githubapiclientkotlin.ui.viewmodels.RepoDetailsViewModel
import com.ledwon.jakub.githubapiclientkotlin.utils.RepoJsonConverter
import dagger.android.AndroidInjection
import javax.inject.Inject

class RepoDetailsActivity : AppCompatActivity() {

    companion object {
        val BUNDLE_USERNAME_KEY = "com.ledwon.jakub.githubapiclientkotlin.USERNAME"
        val BUNDLE_REPONAME_KEY = "com.ledwon.jakub.githubapiclientkotlin.REPONAME"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: RepoDetailsViewModel

    @Inject
    lateinit var loadingFragment: LoadingFragment
    @Inject
    lateinit var repoDetailsFragment: RepoDetailsFragment

    lateinit var loadedRepo : Repo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        setContentView(R.layout.activity_repo_details)
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_repo_details_fragment_container, loadingFragment).commit()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoDetailsViewModel::class.java)

        val username = intent.getStringExtra(BUNDLE_USERNAME_KEY)
        val repoName = intent.getStringExtra(BUNDLE_REPONAME_KEY)


        viewModel.fetchRepo(username, repoName)

        viewModel.repo.observe(this, Observer { repo -> loadedRepo = repo })

        viewModel.loading.observe(this, Observer {
            if (it == false) {
                val bundle = Bundle()
                bundle.putString(RepoDetailsFragment.BUNDLE_REPO_KEY, RepoJsonConverter.toJsonString(loadedRepo))
                repoDetailsFragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    .replace(R.id.activity_repo_details_fragment_container, repoDetailsFragment).commit()
            }
        })
    }
}