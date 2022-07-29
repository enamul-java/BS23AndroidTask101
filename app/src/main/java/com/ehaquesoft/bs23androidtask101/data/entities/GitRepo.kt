package com.ehaquesoft.bs23androidtask101.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gitrepo")
data class GitRepo(
    val description: String?,
    val created_at: String?,
    val updated_at: String?,
    val watchers: String?,
    val visibility: String?,
    val forks_count: String?,
    val language: String?,
    @PrimaryKey
    val id: Int,
    val name: String,
    val full_name: String?,
    val html_url: String?
)