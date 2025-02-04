package com.example.presentation.navigation.navigation

interface Navigator {
    fun navigateTo(route: String)
    fun popBackStack()

}
