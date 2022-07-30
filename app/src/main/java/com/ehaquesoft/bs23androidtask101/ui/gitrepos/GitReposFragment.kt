package com.ehaquesoft.bs23androidtask101.ui.gitrepos

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ehaquesoft.bs23androidtask101.R
import com.ehaquesoft.bs23androidtask101.data.dto.GitRepoDto
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepo
import com.ehaquesoft.bs23androidtask101.data.reqmodels.GitRepoRequestModel
import com.ehaquesoft.bs23androidtask101.databinding.GitreposFragmentBinding
import com.ehaquesoft.bs23androidtask101.utils.Resource
import com.ehaquesoft.bs23androidtask101.utils.autoCleared
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitReposFragment : Fragment(), GitReposAdapter.GitReposItemListener {

    private var binding: GitreposFragmentBinding by autoCleared()
    //private val viewModel: GitReposViewModel by viewModels()
    private val viewModel: GitReposViewModel3 by viewModels()
    private lateinit var adapter: GitReposAdapter

    var search:String = "Android"
    var sort:String = "updated"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GitreposFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionOnClick()

        setQueryAndSorting(GitRepoRequestModel(0, search, sort, ""))

        setupRecyclerView()
        setupObservers()
    }

    private fun actionOnClick(){
        binding.sortText.setOnClickListener{
            showSortingPopUpMenu()
        }

        binding.searchText.setOnClickListener{
            showDialog()
        }
    }



    private fun showSortingPopUpMenu() {
        //val view = activity?.findViewById<View>(R.id.stars) ?: return
        PopupMenu(requireContext(), binding.sortText).run {
            menuInflater.inflate(R.menu.sort_repo, menu)

            setOnMenuItemClickListener {
                //stars, forks, help-wanted-issues, updated
                    when (it.itemId) {
                        R.id.stars -> searching("stars")
                        R.id.forks -> searching("forks")
                        R.id.issue -> searching("help-wanted-issues")
                        else -> searching("updated")
                    }
                true
            }
            show()
        }
    }

    fun showDialog(){
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)

            var view:View? = requireActivity().layoutInflater.inflate(R.layout.search_input_layout, null)
            var editTextSearch: TextInputEditText = view!!.findViewById(R.id.editTextSearch)

            builder.setView(view)
            builder.apply {
                setPositiveButton(R.string.dialog_search,{ dialog, id ->
                       if(!editTextSearch.text.toString().isNullOrEmpty()){
                           sorting(editTextSearch.text.toString())
                           dialog.dismiss()
                       }else{
                           dialog.dismiss()
                       }

                    })
                setNegativeButton(R.string.dialog_cancel,{ dialog, id ->
                        dialog.dismiss()
                    })
            }
            builder.create()
        }

        alertDialog?.show()

    }

    private fun sorting(sort:String){
        this.sort = sort
        customNetworkCall(search,sort)
    }

    private fun searching(search:String){
        this.search = search
        customNetworkCall(search,sort)
    }

    fun customNetworkCall(search:String,sort:String){
        setQueryAndSorting(GitRepoRequestModel(0, search, sort, ""))
    }

    private fun setQueryAndSorting(searchQuery:GitRepoRequestModel){
        searchQuery.let {
            viewModel.start(it)
        }
    }

    private fun setupRecyclerView() {
        adapter = GitReposAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.gitRepos.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    /*
    override fun onClickedGitRepo(gitRepoId: Int, owner:String, repo:String) {
        findNavController().navigate(
            R.id.action_gitReposFragment_to_gitRepoDetailFragment,
            bundleOf("id" to gitRepoId, "owner" to owner, "repo" to repo)
        )
    }*/

    override fun onClickedGitRepo(gitRepoDto: GitRepoDto) {
        findNavController().navigate(
            R.id.action_gitReposFragment_to_gitRepoDetailFragment,
            bundleOf("gitrepo" to gitRepoDto)
        )
    }

}
