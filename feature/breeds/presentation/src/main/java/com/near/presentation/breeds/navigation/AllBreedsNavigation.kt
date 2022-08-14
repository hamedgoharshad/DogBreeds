package com.near.presentation.breeds.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.near.common.presentation.navigation.NavigationDestination
import com.near.presentation.breedImage.ImagesRoute
import com.near.presentation.breedImage.navigation.ImagesDestination
import com.near.presentation.breeds.BreedsRoute
import com.near.presentation.favorite.navigation.FavoriteDestination

object AllBreedsDestination : NavigationDestination {
    override val route = "all_breeds_route"
    override val destination = "all_breeds_destination"
}

fun NavGraphBuilder.breedsGraph(
    navigateToImages: (String) -> Unit,
    navigateToFavorite: () -> Unit,
) {
    navigation(
        route = AllBreedsDestination.route,
        startDestination = AllBreedsDestination.destination
    ) {
        composable(route = AllBreedsDestination.destination) {
            BreedsRoute(
                navigateToFavorites = navigateToFavorite,
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
                navigateToFavorites = navigateToFavorite)
        }
        composable(route = FavoriteDestination.destination) {
            BreedsRoute(
                navigateToFavorites = navigateToFavorite,
                navigateToImages = navigateToImages,
            )
        }
    }
}

