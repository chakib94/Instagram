package com.taki.instagram.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.taki.instagram.R
import com.taki.instagram.data.models.Photo
import com.taki.instagram.data.models.User
import com.taki.instagram.databinding.FragmentHomeBinding
import com.taki.instagram.ui.adapters.PostAdapter
import com.taki.instagram.ui.adapters.UserAdapter
import com.taki.instagram.ui.viewmodel.UserViewModel
import com.taki.instagram.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*


//@AndroidEntryPoint   //I can inject things into this class //we need to inject dependencies into the activities/fragments
//if we want to inject dependencies into android component such activities frgments views so that we need @AndroidEntryPoint
//@AndroidEntryPoint   tells dagger Hilt to inject this activity/fragment with its dpendencies

// inject the field of its class exp when we create a viewmodel this annotation @AndroidEntryPoint  will take care of the viewmodel
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), UserAdapter.OnUserClickListener,
    PostAdapter.OnPostClickListener {

/*    if we want to inject viewModel in our activity for that we dont use inject because of all that viewModel factory stuff
    private val viewModel: TestViewModel by viewModels()
    this by viewModels() will make dagger hilt inject the ViewModel*/
    private val viewModel: UserViewModel by viewModels()
    private var homeFragmentBinding: FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        homeFragmentBinding = binding

        val userAdapter = UserAdapter(this@HomeFragment)
        val userList: MutableList<User> = mutableListOf()

        binding.apply {
            usersRv.apply {
                adapter = userAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }

            viewModel.users.observe(viewLifecycleOwner) { result ->
                if (!result.data.isNullOrEmpty()) {
                    result.data!!.forEach {
                        userList.add(it.user)
                    }

                    initPostsRV(userList)
                    userAdapter.submitList(userList.toList())

                }

                //result.data.isNullOrEmpty() when we dont cache data yet
                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                errorTv.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                errorTv.text = result.error?.localizedMessage
            }

        }
    }

    private fun initPostsRV(photoList: MutableList<User>) {
        val postAdapter = PostAdapter(photoList, this)
        posts_rv.adapter = postAdapter
        posts_rv.layoutManager = GridLayoutManager(activity, 2)
        posts_rv.setHasFixedSize(true)


        search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if ("" != query) {
                    search_bar.post { search_bar.clearFocus() }
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                postAdapter.filter.filter(newText)
                return true
            }
        })
    }

    override fun onUserClick(user: User) {
/*        val image = user.profileImage!!.large
        val bundle = Bundle()
         bundle.putString("url", "image")
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFullScreenFragment(image)
        findNavController().navigate(action)*/
    }

    override fun onPostClick(user: User) {
/*        val action = HomeFragmentDirections.actionHomeFragmentToUserDetailFragment(user)
        findNavController().navigate(action)*/
    }

}
