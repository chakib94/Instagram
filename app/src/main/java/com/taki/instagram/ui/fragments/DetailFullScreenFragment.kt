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
import com.taki.instagram.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail_full_screen.*
import kotlinx.android.synthetic.main.fragment_user_detail.*


@AndroidEntryPoint
class DetailFullScreenFragment : Fragment(R.layout.fragment_detail_full_screen) {

    private val args : DetailFullScreenFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image = args.url

        try {
            Glide.with(requireContext())
                .load(image)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.ic_close)
                .into(detailImg)

        } catch (e: Exception) {
            Log.e(TAG, "onViewCreated:${e.message}!!! " )
        }

        back_btn.setOnClickListener { findNavController().popBackStack() }
    }

}