package com.ehaquesoft.bs23androidtask101.ui.gitrepodetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepoOwner
import com.ehaquesoft.bs23androidtask101.data.repository.GitRepoOwnerRepository
import com.ehaquesoft.bs23androidtask101.data.reqmodels.GitRepoOwnerRequestModel
import com.ehaquesoft.bs23androidtask101.utils.Resource

class GitRepoDetailsViewModel @ViewModelInject constructor(
    private val repository: GitRepoOwnerRepository
) : ViewModel() {

    private val _detailsReqModel = MutableLiveData<GitRepoOwnerRequestModel>()

    private val _gitRepositoryList = _detailsReqModel.switchMap { detailsReqModel ->
        repository.getGitRepoDetails(detailsReqModel.id, detailsReqModel.owner, detailsReqModel.repo)
    }
    val gitRepoDetails: LiveData<Resource<GitRepoOwner>> = _gitRepositoryList


    fun start(detailsReqModel: GitRepoOwnerRequestModel) {
        _detailsReqModel.value = detailsReqModel
    }
}
