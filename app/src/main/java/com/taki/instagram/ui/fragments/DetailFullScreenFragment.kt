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
class DetailFullScreenFragment : Fragment(R.layout.fragment_detail_full_screen) {

    private val args : DetailFullScreenFragment by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /*Glide.with(requireContext())
            .load(args)
            .placeholder(R.drawable.placeholder)
            .into(img)*/

        back.setOnClickListener { findNavController().popBackStack() }
    }

}