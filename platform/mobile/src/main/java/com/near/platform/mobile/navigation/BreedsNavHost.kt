package com.near.platform.mobile.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.near.presentation.breedImages.navigation.ImagesDestination
import com.near.presentation.allBreeds.navigation.AllBreedsDestination
import com.near.presentation.allBreeds.navigation.breedsGraph
import com.near.presentation.bookmark.navigation.FavoriteDestination

@Composable
fun BreedsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AllBreedsDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        breedsGraph(
            navigateToFavorite = { navController.navigate(FavoriteDestination.route) },
            navigateToImages = { navController.navigate("${ImagesDestination.route}/$it") },
        )
    }
}
