package com.rickyandmonty.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.presentation.navigation.navigation.NavigationController
import com.rickyandmonty.ui.ui.theme.RickyAndMontyTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as RMApplication).appComponent.inject(this)
        enableEdgeToEdge()
        setContent {
            RickyAndMontyTheme {

                    Column(
                        modifier =
                        Modifier
                            .fillMaxSize()
                            .systemBarsPadding(),
                    ) {
                        NavigationController(viewModelFactory)
                    }

            }
        }
    }
}

