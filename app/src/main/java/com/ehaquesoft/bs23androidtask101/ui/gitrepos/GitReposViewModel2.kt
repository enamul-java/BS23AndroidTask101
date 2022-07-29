package com.ehaquesoft.bs23androidtask101.ui.gitrepos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepo
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepoList
import com.ehaquesoft.bs23androidtask101.data.repository.GitRepoRepository
import com.ehaquesoft.bs23androidtask101.utils.Resource
import retrofit2.Response

class GitReposViewModel2 @ViewModelInject constructor(
    private val repository: GitRepoRepository
) : ViewModel() {

    private val _searchQueary = MutableLiveData<String>()

    private val _gitRepositoryList = _searchQueary.switchMap { searchQuery ->
        repository.getGitRepos(searchQuery)
    }
    val gitRepos: LiveData<Resource<List<GitRepo>>> = _gitRepositoryList


    fun start(searchQuery: String) {
        _searchQueary.value = searchQuery
    }
}
