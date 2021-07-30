package com.taki.instagram.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.taki.instagram.R
import com.taki.instagram.data.models.User
import kotlinx.android.synthetic.main.post_item.view.*
import kotlinx.android.synthetic.main.user_item.view.titleTV
import javax.inject.Inject

open class PostAdapter(
    private val userList: MutableList<User> = mutableListOf(),
    private val listener: OnPostClickListener
) : RecyclerView.Adapter<PostAdapter.PostVH>(), Filterable {

    private var userListFull: List<User> = ArrayList(userList)
    private var mContext: Context? = null


    @Inject
    lateinit var requestManager : RequestManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.post_item,
            parent,
            false
        )
        mContext = parent.context
        return PostVH(itemView)
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        val currentItem = userList[position]

        try {
            //requestManager.load(currentItem.profileImage?.medium).into(holder.image)
            mContext?.let {
                Glide.with(it)
                    .load(currentItem.profileImage?.medium)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.ic_close)
                    .into(holder.image)
            }
            holder.title.text = currentItem.username
            holder.date.text = currentItem.updatedAt
        } catch (e: Exception) {
            Log.e("PostAdapter", "bind:!!!! ${e.message} " )
        }
    }

    override fun getItemCount(): Int = userList.size

    override fun getFilter(): Filter = postsFiltered

    private val postsFiltered = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<User>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(userListFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim()
                for (post in userListFull) {
                    if (post.username!!.toLowerCase().contains(filterPattern)) {
                        filteredList.add(post)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            userList.clear()
            userList.addAll(filterResults?.values as MutableList<User>)
            notifyDataSetChanged()
        }
    }

    inner class PostVH(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val image: ImageView = itemView.images
        val title: TextView = itemView.titleTV
        val date: TextView = itemView.dateTV

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION)
                listener.onPostClick(userList.get(position))
        }
    }

    interface OnPostClickListener {
        fun onPostClick(user: User)
    }



}