package com.ledwon.jakub.githubapiclientkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ledwon.jakub.githubapiclientkotlin.R
import com.ledwon.jakub.githubapiclientkotlin.data.model.Repo
import com.ledwon.jakub.githubapiclientkotlin.databinding.FragmentRepoDetailsBinding
import com.ledwon.jakub.githubapiclientkotlin.utils.RepoJsonConverter
import javax.inject.Inject

class RepoDetailsFragment @Inject constructor() : Fragment() {

    companion object {
        val BUNDLE_REPO_KEY = "com.ledwon.jakub.githubapiclientkotlin.ui.REPO"
    }

    private var binding : FragmentRepoDetailsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_repo_details, container, false)
        binding = DataBindingUtil.bind(rootView)

        binding?.repo = RepoJsonConverter.toRepo(arguments!!.getString(BUNDLE_REPO_KEY))

        return binding?.root
    }

    fun setRepo(repo : Repo) {binding?.repo = repo}

    fun setNoDescriptionString(noDescStr : String) {binding?.noDescriptionString = noDescStr}
}