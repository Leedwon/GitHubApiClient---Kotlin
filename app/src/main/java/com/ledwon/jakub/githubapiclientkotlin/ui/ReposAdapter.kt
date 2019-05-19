package com.ledwon.jakub.githubapiclientkotlin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ledwon.jakub.githubapiclientkotlin.R
import com.ledwon.jakub.githubapiclientkotlin.data.model.Repo
import com.ledwon.jakub.githubapiclientkotlin.databinding.RepoItemBinding

class ReposAdapter(private val listener: OnItemClickListener) : ListAdapter<Repo, ReposAdapter.ReposHolder>(object : DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.equals(newItem)
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.equals(newItem)
    }
}) {
    private var repoItemBinding : RepoItemBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposHolder {
        repoItemBinding = DataBindingUtil.bind(LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false))
        return ReposHolder(repoItemBinding!!.root)
    }

    override fun onBindViewHolder(holder: ReposHolder, position: Int) {
        repoItemBinding?.repo = getItem(position)

    }

    inner class ReposHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { v ->
                if(adapterPosition != RecyclerView.NO_POSITION)
                    listener.onItemClick(getItem(adapterPosition))
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(repo: Repo)
    }
}