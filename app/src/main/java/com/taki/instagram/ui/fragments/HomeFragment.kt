package com.taki.instagram.ui.fragments

import android.os.Bundle
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
//if we want to inject dependencies into android component such activities fragments views so that we need @AndroidEntryPoint

// inject the field of its class exp when we create a viewmodel this annotation @AndroidEntryPoint  will take care of the viewmodel
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),
    PostAdapter.OnPostClickListener {

/*    if we want to inject viewModel in our activity for that we dont use inject because of all that viewModel factory stuff
    private val viewModel: TestViewModel by viewModels()
    this by viewModels() will make dagger hilt inject the ViewModel*/
    private val viewModel: UserViewModel by viewModels()
    private var homeFragmentBinding: FragmentHomeBinding? = null
    var userList: MutableList<User> = mutableListOf()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        homeFragmentBinding = binding

        val userAdapter = UserAdapter()

        binding.apply {
            usersRv.apply {
                adapter = userAdapter
                //layoutManager responsible for how the rv should actually layouts the items on the screen (horizontally or vertically)
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                //optimization method of the rv wr should use if we know that the rv doesn't change it dimensions on the screen
                setHasFixedSize(true)
            }

            viewModel.users.observe(viewLifecycleOwner) { result ->
                if (!result.data.isNullOrEmpty()) {
                    result.data!!.forEach {
                        userList.add(it.user)
                    }
                    initPostsRV(userList)

                    //whenever in the db changes we receive it in here
                    //submitList method of ListAdapter and after we have passed the new list here DiffUtil takes care of the rest of calculating the changes
                    //of the old list and dispatching the appropriate update events and animations
                    //compare the old and new list
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


        homeFragmentBinding!!.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

    override fun onPostClick(user: User) {
  /*      userList.remove(user)
        posts_rv.adapter?.notifyItemRemoved(1)*/
        val action = HomeFragmentDirections.actionHomeFragmentToUserDetailFragment(user)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        homeFragmentBinding = null
        super.onDestroyView()
    }
}
