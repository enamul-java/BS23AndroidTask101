package com.ehaquesoft.bs23androidtask101.data.remote

import javax.inject.Inject

class GitRepoRemoteDataSource @Inject constructor(
    private val gitRepoService: GitRepoService
): BaseDataSource() {

    suspend fun getGitRepos(searchQuery:String) = getResult { gitRepoService.getAllGitRepo(searchQuery,"updated","desc") }
    suspend fun getGitRepos() = getResult { gitRepoService.getAllGitRepo("Android","updated","desc") }
}