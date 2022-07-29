package com.ehaquesoft.bs23androidtask101.ui.gitrepodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ehaquesoft.bs23androidtask101.databinding.GitrepoDetailFragmentBinding
import com.ehaquesoft.bs23androidtask101.utils.autoCleared

class GitRepoDetailFragment: Fragment() {

    private var binding: GitrepoDetailFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GitrepoDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}