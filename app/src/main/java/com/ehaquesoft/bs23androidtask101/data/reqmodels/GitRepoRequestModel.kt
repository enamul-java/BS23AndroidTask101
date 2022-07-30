package com.ehaquesoft.bs23androidtask101.data.reqmodels

import androidx.room.Entity
import androidx.room.PrimaryKey

data class GitRepoRequestModel(
    val id: Int,
    val searchQuery: String,
    val sort: String?,
    val order: String?
)