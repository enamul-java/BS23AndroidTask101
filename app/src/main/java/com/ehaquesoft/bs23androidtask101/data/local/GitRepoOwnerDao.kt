package com.ehaquesoft.bs23androidtask101.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepo
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepoOwner

@Dao
interface GitRepoOwnerDao {

    //@Query("SELECT * FROM gitrepo WHERE id = :id")
    //fun getGitRepo(id: Int): LiveData<GitRepo>

    @Query("SELECT * FROM gitrepoowner WHERE repo_id = :id")
    fun getGitRepoOwner(id: Int): LiveData<GitRepoOwner>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gitRepoOwner: GitRepoOwner)

}