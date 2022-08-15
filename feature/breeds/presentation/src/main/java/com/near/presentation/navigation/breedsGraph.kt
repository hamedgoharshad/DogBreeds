package com.near.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.near.presentation.allBreeds.BreedsRoute
import com.near.presentation.allBreeds.navigation.AllBreedsDestination
import com.near.presentation.bookmark.BookmarkRoute
import com.near.presentation.bookmark.navigation.BookmarkDestination
import com.near.presentation.breedImages.ImagesRoute
import com.near.presentation.breedImages.navigation.ImagesDestination

fun NavGraphBuilder.breedsGraph(
    navigateToImages: (String) -> Unit,
    navigateToFavorite: () -> Unit,
) {
    composable(route = AllBreedsDestination.route) {
        BreedsRoute(
            navigateToBookmark = navigateToFavorite,
            navigateToImages = navigateToImages,
        )
    }
    composable(
        route = "${ImagesDestination.route}/{${ImagesDestination.breedNameArg}}",
        arguments = listOf(
            navArgument(ImagesDestination.breedNameArg) {
                type = NavType.StringType
            }
        )) {
        ImagesRoute(
            navigateToBookmark = navigateToFavorite
        )
    }
    composable(route = BookmarkDestination.route) {
        BookmarkRoute()
    }
}