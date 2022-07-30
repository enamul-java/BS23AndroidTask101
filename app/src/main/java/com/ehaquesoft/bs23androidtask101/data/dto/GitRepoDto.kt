package com.ehaquesoft.bs23androidtask101.data.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitRepoDto(
    val description: String?,
    val created_at: String?,
    val updated_at: String?,
    val watchers: String?,
    val visibility: String?,
    val forks_count: String?,
    val language: String?,
    val id: Int,
    val name: String,
    val full_name: String?,
    val html_url: String?
): Parcelable