package com.taki.instagram.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.taki.instagram.databinding.UserItemBinding
import com.taki.instagram.data.models.User
import com.taki.instagram.ui.fragments.HomeFragmentDirections
import javax.inject.Inject

/*ListAdapter is a subclass of a rv adapter we use it whenever we have a reactive dataSource, when we have a completely new list passed to you
because when something in room changes we get a completely new list of task through the Flow object we don't fit single items or changes
db doesn't says hey this item was deleted or this one is changed we always get a completely new list and ListAdapter can handle this properly
because it calculates the diff between the old and the new list and automatically dispatch the old changes and the correct animations
and importantly it does all this in the bg thread again to avoid that we  have a starter in new interface*/
class UserAdapter(private val listener: OnUserClickListener) :
    ListAdapter<User, UserAdapter.UserViewHolder>(UserComparator()) {
    //ListAdapter we can just pass a completely new list and it calculate the changes between the old and the new list but of course the adapter
    //doesnt know this automatically we have to tell it how it can detect changes between items.
    //for this we have to create such an item callback which is a nested class into this adapter DiffCallBack

    //DiffCallback() will know how to handle a list of Tasks and how to dispatch a appropriate animations and updates methods when we pass a new list to it

    @Inject
    lateinit var requestManager: RequestManager

    //rv knows whatever it needs a new item in the list; ths is how it get one ;it call this method
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))  //getItem(position) rv method that can get us and handle a reference to the item at this position
    }

    //VH is basically a class that know where the single views in our layout are  and what data we should put in there
    //inner class is basically the equivalent of static class in java which is independent from other class with inner we can access to UserAdapter from  the outside
    //This also means that we  coupled this VH to this adapter but it is fine because they belong together we don't to use this VH anywhere else  so we can now call getItem and pass the position
    inner class UserViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //this fun will later put the actual data into the diff views inside this layout
        fun bind(user: User) {
            //we define what data is this task object we want to put into which use of our item user layout
            binding.apply {
                try {
                    //requestManager.load(user.profileImage?.large).into(img)
                    Glide.with(itemView)
                        .load(user.profileImage?.large)
                        .into(img)
                    titleTV.text = user.firstName
                } catch (e: Exception) {
                    Log.e("UserAdapter", "bind:!!!! ${e.message} ")
                }

                root.setOnClickListener {
                    //NO_POSITION: when we delete an item it gets animated from the list and theoritically it is possible to click this item
                    //while is still animating but not valid anymore
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onUserClick(getItem(position),position)

                /*          val image = getItem(position).profileImage!!.large
                          val action = HomeFragmentDirections.actionHomeFragmentToDetailFullScreenFragment(image)
                          Navigation.findNavController(it).navigate(action)*/
                     /*   currentList.removeAt(position)
                        bindingAdapter?.notifyItemRemoved(position)
                        notifyItemRangeChanged(position, currentList.size);*/

                    }
                }
            }
        }
    }

    //User item that we want to compare
    class UserComparator : DiffUtil.ItemCallback<User>() {
        //areItemsTheSame means that two items represents the same logical item because for exp an item can change its position in the list
        //without changing its content and with this callback knows when it has to move an item around
        override fun areItemsTheSame(oldItem: User, newItem: User) =
            oldItem.name == newItem.name


        //we have to tell our callback when the contents within the items has changed because then the adapter will knows that it has to update this particular item
        //it has to refresh it on the screen
        override fun areContentsTheSame(oldItem: User, newItem: User) =
        // == it is the same equals method (data class automatically gets equals , the comparision behaviour)
        //this means whenever an attribute of Task has changed => this callback areContentsTheSame will know these items are the same anymore
            //and it has to update them on the screen
            oldItem == newItem
    }


    interface OnUserClickListener {
        fun onUserClick(user: User, position : Int)
    }

}