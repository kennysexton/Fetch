package com.kennysexton.fetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.kennysexton.fetch.ui.components.ListRetrieval
import com.kennysexton.fetch.ui.theme.FetchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListRetrieval(vm = FetchViewModel(),modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }


}