package com.ehaquesoft.bs23androidtask101.ui.gitrepos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ehaquesoft.bs23androidtask101.R
import com.ehaquesoft.bs23androidtask101.databinding.GitreposFragmentBinding
import com.ehaquesoft.bs23androidtask101.utils.Resource
import com.ehaquesoft.bs23androidtask101.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitReposFragment : Fragment(), GitReposAdapter.GitReposItemListener {

    private var binding: GitreposFragmentBinding by autoCleared()
    //private val viewModel: GitReposViewModel by viewModels()
    private val viewModel: GitReposViewModel2 by viewModels()
    private lateinit var adapter: GitReposAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GitreposFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setQueryAndSorting("Android")

        setupRecyclerView()
        setupObservers()
    }

    private fun setQueryAndSorting(searchQuery:String){
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

    override fun onClickedGitRepo(gitRepoId: Int) {
        findNavController().navigate(
            R.id.action_gitReposFragment_to_gitRepoDetailFragment,
            bundleOf("id" to gitRepoId)
        )
    }
}
