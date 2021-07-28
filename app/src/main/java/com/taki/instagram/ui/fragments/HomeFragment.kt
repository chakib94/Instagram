package com.taki.instagram.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.taki.instagram.R
import com.taki.instagram.data.models.User
import com.taki.instagram.databinding.FragmentHomeBinding
import com.taki.instagram.ui.adapters.PostAdapter
import com.taki.instagram.ui.adapters.UserAdapter
import com.taki.instagram.ui.viewmodel.UserViewModel
import com.taki.instagram.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), UserAdapter.OnUserClickListener, PostAdapter.OnPostClickListener {

    private val TAG = "HomeFragment"

    private val viewModel: UserViewModel by viewModels()

    private var homeFragmentBinding : FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        homeFragmentBinding = binding

        val userAdapter = UserAdapter(this)
        val userList: MutableList<User> = mutableListOf()


       /* binding.apply {
            users_rv.apply {
                adapter = UserAdapter(this@HomeFragment)
                layoutManager = LinearLayoutManager(activity)
            }

            viewModel.users.observe(viewLifecycleOwner) { result ->
                Log.d(TAG, "onViewCreated: ${result.data} ")
           *//*     Log.d(TAG, "onViewCreated: ${result.data?.forEach { userList.add(it) }} ")
                userAdapter.submitList(result.data)

                //result.data.isNullOrEmpty() when we dont cache data yet
                progress_bar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                text_view_error.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                text_view_error.text = result.error?.localizedMessage*//*
            }
        }*/
    }

    override fun onUserClick(user: User) {
        /*val action = HomeFragmentDirections.actionHomeFragmentToDetailFullScreenFragment(user)
        findNavController().navigate(action)*/
    }

    override fun onPostClick(user: User) {
/*        val action = HomeFragmentDirections.actionHomeFragmentToUserDetailFragment(user)
        findNavController().navigate(action)*/
    }

}