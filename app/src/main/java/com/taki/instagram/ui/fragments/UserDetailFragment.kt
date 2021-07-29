package com.taki.instagram.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.taki.instagram.R
import com.taki.instagram.databinding.FragmentUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_detail.*
import kotlinx.android.synthetic.main.post_item.*

@AndroidEntryPoint
class UserDetailFragment : Fragment(R.layout.fragment_user_detail) {

    private val args: UserDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentUserDetailBinding.bind(view)
        binding.apply {
            dateTv.setText(args.user.updatedAt)
            updatedDateTv.setText(args.user.updatedAt)

            try {
                Glide.with(requireContext())
                    .load(args.user.profileImage?.large)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.ic_close)
                    .into(img)

            } catch (e: Exception) {
                Log.e(TAG, "onViewCreated: ${e.message} ")
            }

            back.setOnClickListener { findNavController().popBackStack() }
        }





    }

}