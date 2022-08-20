package com.hamed.presentation.breedImages.navigation

import com.hamed.common.presentation.navigation.NavigationDestination

object ImagesDestination : NavigationDestination {
    override val route = "images_route"
    override val destination = "images_destination"
    const val breedNameArg = "BreedName"
}