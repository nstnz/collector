package com.nstnz.collector.androidApp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.nstnz.collector.common.Android
import com.nstnz.collector.common.RootView
import com.nstnz.collector.common.basic.data.BaseActivity
import com.nstnz.collector.common.saveGoogleAuthToken
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent

class MainActivity : PreComposeActivity(), BaseActivity {

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                data?.let {
                    saveGoogleAuthToken(it)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Android.activity = this
        setContent {
            RootView()
        }
    }

    override fun launchIntent(intent: Intent) {
        resultLauncher.launch(intent)
    }
}
