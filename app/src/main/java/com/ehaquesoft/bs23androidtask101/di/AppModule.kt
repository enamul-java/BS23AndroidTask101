package com.ehaquesoft.bs23androidtask101.di

import android.content.Context
import com.ehaquesoft.bs23androidtask101.data.local.AppDatabase
import com.ehaquesoft.bs23androidtask101.data.local.GitRepoDao
import com.ehaquesoft.bs23androidtask101.data.local.GitRepoOwnerDao
import com.ehaquesoft.bs23androidtask101.data.remote.GitRepoDetailsRemoteDataSource
import com.ehaquesoft.bs23androidtask101.data.remote.GitRepoRemoteDataSource
import com.ehaquesoft.bs23androidtask101.data.remote.GitRepoService
import com.ehaquesoft.bs23androidtask101.data.repository.GitRepoOwnerRepository
import com.ehaquesoft.bs23androidtask101.data.repository.GitRepoRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideGitRepoService(retrofit: Retrofit): GitRepoService =
        retrofit.create(GitRepoService::class.java)

    @Singleton
    @Provides
    fun provideGitRepoRemoteDataSource(gitRepoService: GitRepoService) =
        GitRepoRemoteDataSource(gitRepoService)

    @Singleton
    @Provides
    fun provideGitRepoDetailsRemoteDataSource(gitRepoService: GitRepoService) =
        GitRepoDetailsRemoteDataSource(gitRepoService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideGitRepoDao(db: AppDatabase) = db.gitRepoDao()

    @Singleton
    @Provides
    fun provideGitRepoOwnerDao(db: AppDatabase) = db.gitRepoOwnerDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: GitRepoRemoteDataSource,
        localDataSource: GitRepoDao
    ) =
        GitRepoRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideGitRepoOwnerRepository(
        remoteDataSource: GitRepoDetailsRemoteDataSource,
        localDataSource: GitRepoOwnerDao
    ) =
        GitRepoOwnerRepository(remoteDataSource, localDataSource)
}