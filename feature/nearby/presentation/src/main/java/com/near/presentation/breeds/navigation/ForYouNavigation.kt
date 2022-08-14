package com.near.presentation.breeds.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.near.common.presentation.navigation.NavigationDestination
import com.near.presentation.breeds.BreedsRoute

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
    }
}

