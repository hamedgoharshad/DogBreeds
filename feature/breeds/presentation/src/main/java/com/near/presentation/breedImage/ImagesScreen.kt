package com.near.presentation.breedImage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.near.domain.model.Breed

@Composable
fun ImagesRoute(
    modifier: Modifier = Modifier,
    viewModel: ImagesViewModel = viewModel(),
    navigateToFavorites: () -> Unit,
    navigateToImages: (String) -> Unit,
) {
    val uiState by viewModel.ImagesUiState.collectAsState()

    ImagesScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToFavorites = navigateToFavorites,
        navigateToImages = navigateToImages
    )
}

@Composable
fun ImagesScreen(
    modifier: Modifier,
    uiState: ImagesUiState,
    navigateToFavorites: () -> Unit,
    navigateToImages: (String) -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Row(
                Modifier
                    .background(MaterialTheme.colors.primary)
                    .fillMaxWidth()
                    .fillMaxHeight(
                        0.08f
                    )
                    .padding(start = 4.dp),
            ) {
                Image(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "support",
                    Modifier
                        .clickable {
                            navigateToFavorites()
                        }
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .padding(8.dp),
                    colorFilter = ColorFilter.lighting(Color.White, Color.White)
                )
            }
        }) {
        when (uiState) {
            ImagesUiState.Loading -> {}
            is ImagesUiState.Failed -> {}
            is ImagesUiState.Success -> {
                ImagesContent(modifier, uiState,navigateToImages)
            }
        }
    }
}

@Composable
fun ImagesContent(
    modifier: Modifier,
    uiState: ImagesUiState.Success,
    navigateToImages: (String) -> Unit
) {
    LazyColumn {
        items(uiState.Images) {
            BreedItem(it, modifier,navigateToImages)
        }
    }
}

@Composable
fun BreedItem(breed: Breed, modifier: Modifier = Modifier, onClicked: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface, modifier = modifier.clickable { onClicked(breed.name) }
    ) {
        Column(
            modifier = modifier.height(200.dp).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = breed.name,
                style = MaterialTheme.typography.h4
            )
        }
    }
}
