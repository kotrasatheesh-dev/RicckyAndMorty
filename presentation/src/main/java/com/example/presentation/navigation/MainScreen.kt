package com.example.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.presentation.R
import com.example.presentation.navigation.viewmodel.CharactersViewModel

@Composable
fun MainScreen(viewModel: CharactersViewModel) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CharacterAppBar(stringResource(R.string.app_name)) },
        content = { innerPadding ->
            NavigationController(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel
            )
        },
    )
}
