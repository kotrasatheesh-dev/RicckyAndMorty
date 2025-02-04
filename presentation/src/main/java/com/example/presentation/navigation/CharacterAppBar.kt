    package com.example.presentation.navigation

    import androidx.compose.material3.CenterAlignedTopAppBar
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Text
    import androidx.compose.material3.TopAppBarDefaults
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CharacterAppBar(
        title: String,
        modifier: Modifier = Modifier
    ) {
        CenterAlignedTopAppBar(
            title = { Text(title) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground
            ),
            modifier = modifier
        )
    }


