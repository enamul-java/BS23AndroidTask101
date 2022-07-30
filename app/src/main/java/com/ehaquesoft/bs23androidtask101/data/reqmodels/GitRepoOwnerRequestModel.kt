package com.ehaquesoft.bs23androidtask101.data.reqmodels

import androidx.room.Entity
import androidx.room.PrimaryKey

data class GitRepoOwnerRequestModel(
    val id: Int,
    val owner: String,
    val repo: String
)