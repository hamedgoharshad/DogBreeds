package com.near.platform.mobile.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.near.presentation.breedImages.navigation.ImagesDestination
import com.near.presentation.allBreeds.navigation.AllBreedsDestination
import com.near.presentation.bookmark.navigation.BookmarkDestination
import com.near.presentation.navigation.breedsGraph

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
            navigateToFavorite = { navController.navigate(BookmarkDestination.route) },
            navigateToImages = { navController.navigate("${ImagesDestination.route}/$it") },
        )
    }
}
