    package com.rickyandmonty.di

    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import com.rickyandmonty.di.presentation.CharactersViewModel
    import com.rickyandmonty.di.presentation.HomesScreen
    import com.rickyandmonty.di.theme.RickAndMortyTheme
    import javax.inject.Inject


    class MainActivity : ComponentActivity() {
        @Inject
        lateinit var viewModel: CharactersViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                RickAndMortyTheme {
                    HomesScreen()
                }
            }
        }
    }
