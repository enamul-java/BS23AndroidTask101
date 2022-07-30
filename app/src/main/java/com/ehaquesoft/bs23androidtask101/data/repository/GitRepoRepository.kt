package com.ehaquesoft.bs23androidtask101.data.repository

import com.ehaquesoft.bs23androidtask101.data.local.GitRepoDao
import com.ehaquesoft.bs23androidtask101.data.remote.GitRepoRemoteDataSource
import com.ehaquesoft.bs23androidtask101.utils.performGetOperation
import javax.inject.Inject

class GitRepoRepository @Inject constructor(
    private val remoteDataSource: GitRepoRemoteDataSource,
    private val localDataSource: GitRepoDao
) {

    fun getGitRepos(searchQuery: String) = performGetOperation(
        databaseQuery = { localDataSource.getAllGitRepos() },
        networkCall = { remoteDataSource.getGitRepos(searchQuery) },
        saveCallResult = { localDataSource.insertAll(it.items) }
    )


    fun getGitRepos(searchQuery: String, sort:String) = performGetOperation(
        databaseQuery = { localDataSource.getAllGitRepos() },
        networkCall = { remoteDataSource.getGitRepos(searchQuery) },
        saveCallResult = {
            localDataSource.insertAll(it.items)
        }
    )

    fun getGitReposWithParam(searchQuery: String, sort:String) = performGetOperation(
        databaseQuery = { localDataSource.getAllGitRepos(searchQuery, sort) },
        networkCall = { remoteDataSource.getGitRepos(searchQuery) },
        saveCallResult = {
            for(i in 0 until it.items.size) {
                var repo = it.items.get(i)
                repo.search_query = searchQuery
                repo.sort = sort
                localDataSource.insert(repo)
            }
        }
    )
    fun getGitRepos() = performGetOperation(
        databaseQuery = { localDataSource.getAllGitRepos() },
        networkCall = { remoteDataSource.getGitRepos() },
        saveCallResult = { localDataSource.insertAll(it.items) }
    )

    fun getGitRepoDetails(id:Int, owner:String, repo:String) = performGetOperation(
        databaseQuery = { localDataSource.getGitRepo(id) },
        networkCall = { remoteDataSource.getGitRepos() },
        saveCallResult = { localDataSource.insertAll(it.items) }
    )
}