package com.taki.instagram.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.taki.instagram.R
import com.taki.instagram.databinding.FragmentUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_detail.*
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailFragment : Fragment(R.layout.fragment_user_detail) {

    companion object {
        private const val TAG = "UserDetailFragment"
    }

    private val args: UserDetailFragmentArgs by navArgs()

    @Inject
    lateinit var requestManager : RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentUserDetailBinding.bind(view)
        binding.apply {
            try {
                dateTv.text = args.photo.createdDateFormatted
                updatedDateTv.text = args.photo.updatedDateFormatted
                descTv.text = args.photo.altDescription
                requestManager.load(args.user.profileImage?.large).into(img)
                requestManager.load(args.photo.urls.full).into(image_view)
            } catch (e: Exception) {
                Log.e(TAG, "onViewCreated: ${e.message} ")
            }

            back.setOnClickListener { findNavController().popBackStack() }
        }
    }
}