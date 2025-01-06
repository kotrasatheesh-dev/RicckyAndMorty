package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rickyandmonty.di.HomesScreen
import com.rickyandmonty.di.theme.RickAndMortyTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as RickAndMortyApplication).appComponent.inject(this)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                HomesScreen()
            }
        }
    }
}
