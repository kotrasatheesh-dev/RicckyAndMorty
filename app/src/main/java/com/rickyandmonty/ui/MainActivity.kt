package com.rickyandmonty.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.presentation.navigation.uistate.MainScreenContainer
import com.example.presentation.navigation.viewmodel.CharactersViewModel
import com.rickyandmonty.ui.ui.theme.RickyAndMontyTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject lateinit var viewModel: CharactersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as RMApplication).appComponent.inject(this)

        enableEdgeToEdge()
        setContent {
            RickyAndMontyTheme {
                MainScreenContainer(viewModel)
            }
        }
    }
}

