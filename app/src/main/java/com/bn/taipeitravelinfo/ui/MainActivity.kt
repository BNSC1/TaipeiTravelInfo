package com.bn.taipeitravelinfo.ui

import android.os.Bundle
import com.bn.taipeitravelinfo.R
import com.bn.taipeitravelinfo.arch.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : NavigationActivity() {
    override val navHostId = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}