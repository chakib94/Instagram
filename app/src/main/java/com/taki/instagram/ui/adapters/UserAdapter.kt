package com.taki.instagram.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taki.instagram.databinding.UserItemBinding
import com.taki.instagram.data.models.User

class UserAdapter(private val listener: OnUserClickListener) : ListAdapter<User, UserAdapter.UserViewHolder>(UserComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

   inner class UserViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.apply {
                Glide.with(itemView)
                    .load(user.profileImage?.large)
                    .into(img)

                titleTV.text = user.firstName

                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val task = getItem(position)
                        listener.onUserClick(task)
                    }
                }

            }
        }
    }

    class UserComparator : DiffUtil.ItemCallback<User>() {
        //areItemsTheSame means that two items represents the same logical item because for exp an item can change its position in the list
        //without changing its content and with this callback knows when it has to move an item around
        override fun areItemsTheSame(oldItem: User, newItem: User) =
            oldItem.name == newItem.name


        //we have to tell our callback when the contents within the items has changed because then the adapter will knows that it has to update this particular item
        //it has to refresh it on the screen
        override fun areContentsTheSame(oldItem: User, newItem: User) =
        // == it is the same equals method (data class automatically gets equals , the comparision behaviour)
        //this means whenever an attribute of Task has changed => this calback areContentsTheSame will know these items are the same anymore
            //and it has to update them on the screen
            oldItem == newItem
    }

    interface OnUserClickListener {
        fun onUserClick(user: User)
    }
}