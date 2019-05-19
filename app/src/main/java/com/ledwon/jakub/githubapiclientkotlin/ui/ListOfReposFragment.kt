package com.ledwon.jakub.githubapiclientkotlin.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ledwon.jakub.githubapiclientkotlin.R
import com.ledwon.jakub.githubapiclientkotlin.data.model.Repo
import com.ledwon.jakub.githubapiclientkotlin.databinding.FragmentListOfReposBinding
import javax.inject.Inject

class ListOfReposFragment @Inject constructor() : Fragment() {

    private var adapter : ReposAdapter

    init {
        adapter = ReposAdapter(object : ReposAdapter.OnItemClickListener{
            override fun onItemClick(repo: Repo) {
                val intent = Intent(activity, RepoDetailsActivity::class.java)
                intent.putExtra(RepoDetailsActivity.BUNDLE_USERNAME_KEY, repo.owner.login)
                intent.putExtra(RepoDetailsActivity.BUNDLE_REPONAME_KEY, repo.name)
                startActivity(intent)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_list_of_repos, container, false)
        val binding : FragmentListOfReposBinding? = DataBindingUtil.bind(rootView)

        binding?.recyclerViewRepos?.adapter = adapter
        binding?.recyclerViewRepos?.layoutManager = LinearLayoutManager(activity)
        binding?.recyclerViewRepos?.setHasFixedSize(true)

        return binding?.root
    }

    fun submitReposList(repos : List<Repo>) { adapter.submitList(repos)}
}