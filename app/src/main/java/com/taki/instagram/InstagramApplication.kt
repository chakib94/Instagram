package com.taki.instagram

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//it will generate all the components that we need so we can start to use dagger hilt
//necessary to kickoff the dependency injection process for our hole  app
@HiltAndroidApp
class InstagramApplication : Application()