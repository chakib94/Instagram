package com.taki.instagram.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.taki.instagram.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_detail.*

@AndroidEntryPoint
class UserDetailFragment : Fragment(R.layout.fragment_user_detail) {

    private val args : UserDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

 /*       dateTV.setText(args.user.updatedAt)
        updatedDateTV.setText(args.user.updatedAt)

        Glide.with(requireContext())
            .load(args.user.profileImage?.large)
            .placeholder(R.drawable.placeholder)
            .into(img)*/

        back.setOnClickListener { findNavController().popBackStack() }

    }

}