package com.ehaquesoft.bs23androidtask101.data.remote

import javax.inject.Inject

class GitRepoDetailsRemoteDataSource @Inject constructor(
    private val gitRepoService: GitRepoService
): BaseDataSource() {
    suspend fun getGitRepoDetails(owner:String,repo:String) = getResult { gitRepoService.getGirRepositoryDetails(owner,repo) }
}