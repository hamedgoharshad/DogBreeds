package com.hamed.platform.mobile.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.hamed.presentation.breedImages.navigation.ImagesDestination
import com.hamed.presentation.allBreeds.navigation.AllBreedsDestination
import com.hamed.presentation.bookmark.navigation.BookmarkDestination

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
