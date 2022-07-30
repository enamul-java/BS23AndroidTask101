package com.ehaquesoft.bs23androidtask101.data.repository

import com.ehaquesoft.bs23androidtask101.data.local.GitRepoDao
import com.ehaquesoft.bs23androidtask101.data.local.GitRepoOwnerDao
import com.ehaquesoft.bs23androidtask101.data.remote.GitRepoDetailsRemoteDataSource
import com.ehaquesoft.bs23androidtask101.data.remote.GitRepoRemoteDataSource
import com.ehaquesoft.bs23androidtask101.utils.performGetOperation
import javax.inject.Inject

class GitRepoOwnerRepository @Inject constructor(
    private val remoteDataSource: GitRepoDetailsRemoteDataSource,
    private val localDataSource: GitRepoOwnerDao
) {

    fun getGitRepoDetails(id:Int, owner:String, repo:String) = performGetOperation(
        databaseQuery = { localDataSource.getGitRepoOwner(id) },
        networkCall = { remoteDataSource.getGitRepoDetails(owner,repo) },
        saveCallResult = {
            it.owner.repo_id = id
            localDataSource.insert(it.owner)
        }
    )
}