package com.hamed.platform.mobile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hamed.presentation.allBreeds.BreedsRoute
import com.hamed.presentation.allBreeds.navigation.AllBreedsDestination
import com.hamed.presentation.bookmark.BookmarkRoute
import com.hamed.presentation.bookmark.navigation.BookmarkDestination
import com.hamed.presentation.breedImages.ImagesRoute
import com.hamed.presentation.breedImages.navigation.ImagesDestination

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