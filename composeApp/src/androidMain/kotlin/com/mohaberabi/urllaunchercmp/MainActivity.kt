package com.mohaberabi.urllaunchercmp

import App
import UrlLauncher
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    lateinit var urlLauncher: UrlLauncher
    override fun onCreate(savedInstanceState: Bundle?) {
        urlLauncher = UrlLauncher(this)
        super.onCreate(savedInstanceState)

        setContent {

            App(
                urlLauncher = urlLauncher,
            )
        }
    }
}

