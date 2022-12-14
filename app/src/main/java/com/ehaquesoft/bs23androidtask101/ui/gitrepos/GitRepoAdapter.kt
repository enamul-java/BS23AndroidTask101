package com.ehaquesoft.bs23androidtask101.ui.gitrepos

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.ehaquesoft.bs23androidtask101.data.dto.GitRepoDto
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepo
import com.ehaquesoft.bs23androidtask101.databinding.ItemGitrepoBinding
import com.ehaquesoft.bs23androidtask101.utils.ConverterUtil
import com.ehaquesoft.bs23androidtask101.utils.DateUtils

class GitReposAdapter(private val listener: GitReposItemListener) : RecyclerView.Adapter<GitReposViewHolder>() {

    interface GitReposItemListener {
        //fun onClickedGitRepo(gitRepoId: Int, owner:String, repo:String)
        //fun onClickedGitRepo(gitRepo: GitRepo)
        fun onClickedGitRepo(gitRepo: GitRepoDto)
    }

    private val items = ArrayList<GitRepo>()

    fun setItems(items: ArrayList<GitRepo>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItems(items: ArrayList<GitRepo>) {
        for(i in 0 until items.size) {
            this.items.add(items.get(i))
        }
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

    private lateinit var gitRepo: GitRepo

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: GitRepo) {
        this.gitRepo = item
        itemBinding.name.text = ConverterUtil.emptyConvert(item.name)
        itemBinding.language.text = ConverterUtil.emptyConvert(item.language)
        itemBinding.fullName.text = ConverterUtil.emptyConvert(item.full_name)
        itemBinding.visibilityFork.text = item.visibility + " Repository "+ ConverterUtil.emptyConvert(item.forks_count,"Total fork")
        itemBinding.url.text = item.html_url
        itemBinding.createAndUpdate.text = ConverterUtil.emptyConvert(DateUtils.convertDate(item.created_at),"Create on")+ ConverterUtil.emptyConvert(DateUtils.convertDate(item.updated_at),"Last Update")
        Glide.with(itemBinding.root)
            .load(ConverterUtil.languageImageUrl(item.language))
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        /*listener.onClickedGitRepo(gitRepo.id,
            gitRepo.full_name.toString().substring(0, gitRepo.full_name!!.indexOf("/")),
            gitRepo.full_name.toString().substring(gitRepo.full_name!!.indexOf("/"))
        )*/
        //listener.onClickedGitRepo(gitRepo)
        listener.onClickedGitRepo(GitRepoDto(
            gitRepo.description,
            gitRepo.created_at,
            gitRepo.updated_at,
            gitRepo.watchers,
            gitRepo.visibility,
            gitRepo.forks_count,
            gitRepo.language,
            gitRepo.id,
            gitRepo.name,
            gitRepo.full_name,
            gitRepo.html_url
        ))

    }
}

