package com.ehaquesoft.bs23androidtask101.ui.gitrepodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.ehaquesoft.bs23androidtask101.data.dto.GitRepoDto
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepoOwner
import com.ehaquesoft.bs23androidtask101.data.reqmodels.GitRepoOwnerRequestModel
import com.ehaquesoft.bs23androidtask101.databinding.GitrepoDetailFragmentBinding
import com.ehaquesoft.bs23androidtask101.utils.ConverterUtil
import com.ehaquesoft.bs23androidtask101.utils.DateUtils
import com.ehaquesoft.bs23androidtask101.utils.Resource
import com.ehaquesoft.bs23androidtask101.utils.autoCleared
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import java.security.acl.Owner
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class GitRepoDetailFragment: Fragment() {

    private var binding: GitrepoDetailFragmentBinding by autoCleared()
    private val viewModel: GitRepoDetailsViewModel by viewModels()
    private lateinit var gitRepoDto:GitRepoDto

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GitrepoDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gitRepoDto = arguments?.getParcelable<GitRepoDto>("gitrepo") as GitRepoDto

        //showToast(gitRepoDto.full_name!!)
        setOwnerRequest(GitRepoOwnerRequestModel(gitRepoDto.id!!,
            gitRepoDto.full_name.toString().substring(0, gitRepoDto.full_name!!.indexOf("/")),
            gitRepoDto.full_name.toString().substring(gitRepoDto.full_name!!.indexOf("/")+1)))
        setupObservers()
    }

    private fun setOwnerRequest(gitRepoOwnerRequestModel:GitRepoOwnerRequestModel){
        //showToast("Owner:  "+ gitRepoOwnerRequestModel.owner +" Repo: "+ gitRepoOwnerRequestModel.repo)
        gitRepoOwnerRequestModel.let { viewModel.start(gitRepoOwnerRequestModel) }
    }

    private fun showToast(message:String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun setupObservers(){

        viewModel.gitRepoDetails.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when(it.status){
                Resource.Status.SUCCESS -> {
                    bindGitRepoDetails(it.data)
                    binding.progressBar.visibility = View.GONE
                    binding.gitRepoDetailsCl.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.gitRepoDetailsCl.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.gitRepoDetailsCl.visibility = View.GONE
                }

            }
        })
    }

    private fun bindGitRepoDetails(gitRepoOwner: GitRepoOwner?) {
        try {
            binding.ownerName.text = ConverterUtil.emptyConvert(gitRepoOwner?.login, "By ")
            binding.name.text = ConverterUtil.emptyConvert(gitRepoDto.name) +
                    ConverterUtil.emptyConvert(gitRepoDto.language, "(", ")")
            binding.visibility.text = gitRepoDto.visibility

            binding.creat.text = ConverterUtil.emptyConvert(DateUtils.convertDate(gitRepoDto.created_at), "Create On")
            binding.update.text = ConverterUtil.emptyConvert(DateUtils.convertDate(gitRepoDto.updated_at), "Last Update On")

            binding.watchAndFork.text = ConverterUtil.emptyConvert(gitRepoDto.watchers, "Total Views")+
                    ConverterUtil.emptyConvert(gitRepoDto.forks_count,"Total Fork")
            binding.link.text = ConverterUtil.emptyConvert(gitRepoDto.html_url)
            binding.description.text = ConverterUtil.emptyConvert(gitRepoDto.description)
            Glide.with(binding.root)
                .load(gitRepoOwner?.avatar_url)
                .transform(CircleCrop())
                .into(binding.image)
        }catch (e:Exception){
            showToast("No Data Found! Err: "+ e.toString())
        }


    }
}