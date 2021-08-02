package com.taki.instagram.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.taki.instagram.R
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint   tells dagger Hilt to inject this activity/fragment with its dependencies
@AndroidEntryPoint
class
MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val navHostFragment = supportFragmentManager.findFragmentById(androidx.navigation.ui.R.id.nav_host_fragment) as NavHostFragment
    }

}