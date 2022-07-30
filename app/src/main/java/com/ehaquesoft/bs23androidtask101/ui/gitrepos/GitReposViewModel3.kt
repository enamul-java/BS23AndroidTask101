package com.ehaquesoft.bs23androidtask101.ui.gitrepos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepo
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepoList
import com.ehaquesoft.bs23androidtask101.data.repository.GitRepoRepository
import com.ehaquesoft.bs23androidtask101.data.reqmodels.GitRepoRequestModel
import com.ehaquesoft.bs23androidtask101.utils.Resource
import retrofit2.Response

class GitReposViewModel3 @ViewModelInject constructor(
    private val repository: GitRepoRepository
) : ViewModel() {

    private val _searchQueary = MutableLiveData<GitRepoRequestModel>()

    private val _gitRepositoryList = _searchQueary.switchMap { searchQuery ->
        repository.getGitReposWithParam(searchQuery.searchQuery!!, searchQuery.sort!!)
    }
    val gitRepos: LiveData<Resource<List<GitRepo>>> = _gitRepositoryList


    fun start(searchQuery: GitRepoRequestModel) {
        _searchQueary.value = searchQuery
    }
}
