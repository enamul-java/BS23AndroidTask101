package com.ehaquesoft.bs23androidtask101.data.remote

import com.ehaquesoft.bs23androidtask101.data.entities.GitRepoDetails
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepoList
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepoOwner
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitRepoService {

    /*
        1. q = Query
        2. sort = want sort with
                  'sort' Can be one of: stars, forks, help-wanted-issues, updated
        3. order = data order asc/desc

        Original URL: https://api.github.com/search/repositories?q=Android&sort=stars&order=desc
        Request Method Details: @GET("search/repositories?q={query}&sort={sort}&order={order}")
     */

    @GET("search/repositories")
    suspend fun getAllGitRepo(
        @Query("q") q: String,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): Response<GitRepoList>

    /*
         1. owner = owner of the repository
         2. repo = name of repository
         Original URL : https://api.github.com/repos/chvin/react-tetris
     */
    @GET("repos/{owner}/{repo}")
    suspend fun getGirRepositoryDetails(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<GitRepoDetails>
}