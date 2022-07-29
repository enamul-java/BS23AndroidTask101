package com.ehaquesoft.bs23androidtask101.ui.gitrepos

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepo
import com.ehaquesoft.bs23androidtask101.databinding.ItemGitrepoBinding

class GitReposAdapter(private val listener: GitReposItemListener) : RecyclerView.Adapter<GitReposViewHolder>() {

    interface GitReposItemListener {
        fun onClickedGitRepo(characterId: Int)
    }

    private val items = ArrayList<GitRepo>()

    fun setItems(items: ArrayList<GitRepo>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitReposViewHolder {
        val binding: ItemGitrepoBinding = ItemGitrepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitReposViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GitReposViewHolder, position: Int) = holder.bind(items[position])
}

class GitReposViewHolder(private val itemBinding: ItemGitrepoBinding, private val listener: GitReposAdapter.GitReposItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var character: GitRepo

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: GitRepo) {
        this.character = item
        itemBinding.name.text = item.name
        //itemBinding.speciesAndStatus.text = """${item.species} - ${item.status}"""
        Glide.with(itemBinding.root)
            .load("https://avatars.githubusercontent.com/u/8892251?v=4")
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        listener.onClickedGitRepo(character.id)
    }
}

