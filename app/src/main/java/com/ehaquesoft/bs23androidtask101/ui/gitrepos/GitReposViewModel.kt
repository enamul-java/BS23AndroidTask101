package com.ehaquesoft.bs23androidtask101.ui.gitrepos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.ehaquesoft.bs23androidtask101.data.repository.GitRepoRepository

class GitReposViewModel @ViewModelInject constructor(
    private val repository: GitRepoRepository
) : ViewModel() {

    val characters = repository.getGitRepos("Android")
}
