package com.taki.instagram.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taki.instagram.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  val navHostFragment = supportFragmentManager.findFragmentById(androidx.navigation.ui.R.id.nav_host_fragment) as NavHostFragment
    }

}