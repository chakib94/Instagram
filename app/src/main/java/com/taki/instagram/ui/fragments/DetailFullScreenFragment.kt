package com.taki.instagram.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.taki.instagram.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail_full_screen.*
import javax.inject.Inject


@AndroidEntryPoint
class DetailFullScreenFragment : Fragment(R.layout.fragment_detail_full_screen) {

    private val args : DetailFullScreenFragmentArgs by navArgs()

    @Inject
    lateinit var requestManager : RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            val image = args.url
            requestManager.load(image).into(detail_img)
        } catch (e: Exception) {
            Log.e(TAG, "onViewCreated:${e.message}!!! " )
        }
        back_btn.setOnClickListener { findNavController().popBackStack() }
    }
}