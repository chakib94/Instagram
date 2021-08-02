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
import com.taki.instagram.data.models.Photo
import com.taki.instagram.data.models.User
import kotlinx.android.synthetic.main.post_item.view.*
import kotlinx.android.synthetic.main.user_item.view.titleTV
import javax.inject.Inject

open class PostAdapter(
    private val photoList: MutableList<Photo> = mutableListOf(),
    private val listener: OnPostClickListener
) : RecyclerView.Adapter<PostAdapter.PostVH>(), Filterable {

    private var photoListFull: List<Photo> = ArrayList(photoList)
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
        val currentPhotoItem = photoList[position]

        try {
            //requestManager.load(currentUserItem.profileImage?.medium).into(holder.image)
            mContext?.let {
                Glide.with(it)
                    .load(currentPhotoItem.urls.regular)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.ic_close)
                    .into(holder.image)
            }
            holder.title.text = currentPhotoItem.altDescription
            holder.date.text = currentPhotoItem.createdDateFormatted
            holder.username.text = currentPhotoItem.user.firstName
        } catch (e: Exception) {
            Log.e("PostAdapter", "bind:!!!! ${e.message} " )
        }
    }

    override fun getItemCount(): Int = photoList.size

    override fun getFilter(): Filter = photosFiltered

    private val photosFiltered = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<Photo>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(photoListFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim()
                for (photo in photoListFull) {
                    if (photo.altDescription!!.toLowerCase().contains(filterPattern)) {
                        filteredList.add(photo)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            photoList.clear()
            photoList.addAll(filterResults?.values as MutableList<Photo>)
            notifyDataSetChanged()
        }
    }

    inner class PostVH(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val image: ImageView = itemView.image
        val title: TextView = itemView.titleTV
        val username: TextView = itemView.user_name_tv
        val date: TextView = itemView.dateTV

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION)
                listener.onPostClick(photoList[position].user, photoList[position])
        }
    }

    interface OnPostClickListener {
        fun onPostClick(user: User, photo: Photo)
    }

}