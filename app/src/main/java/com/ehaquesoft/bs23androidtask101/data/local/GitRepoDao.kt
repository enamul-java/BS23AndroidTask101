package com.ehaquesoft.bs23androidtask101.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepo

@Dao
interface GitRepoDao {

    @Query("SELECT * FROM gitrepo")
    fun getAllGitRepos() : LiveData<List<GitRepo>>

    @Query("SELECT * FROM gitrepo WHERE search_query = :search_query AND sort = :sort")
    fun getAllGitRepos(search_query: String, sort:String) : LiveData<List<GitRepo>>

    @Query("SELECT * FROM gitrepo WHERE id = :id")
    fun getGitRepo(id: Int): LiveData<GitRepo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(gitrepos: List<GitRepo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gitrepo: GitRepo)


}