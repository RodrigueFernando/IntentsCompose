package br.edu.ifsp.scl.sc3018237.intentscompose.navigation

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home")
    data object AddWordScreen : Screen("add_work")
}