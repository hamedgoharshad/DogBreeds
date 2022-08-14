package com.near.presentation.breedImage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.near.domain.model.Breed

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ImagesRoute(
    modifier: Modifier = Modifier,
    viewModel: ImagesViewModel = viewModel(),
    navigateToFavorites: () -> Unit,
) {
    val uiState by viewModel.imagesUiState.collectAsStateWithLifecycle()

    ImagesScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToFavorites = navigateToFavorites,
    )
}

@Composable
fun ImagesScreen(
    modifier: Modifier,
    uiState: ImagesUiState,
    navigateToFavorites: () -> Unit,
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
                    contentDescription = null,
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
                ImagesContent(modifier, uiState)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesContent(
    modifier: Modifier,
    uiState: ImagesUiState.Success,
) {
    LazyVerticalGrid(cells = GridCells.Fixed(5)) {
        items(uiState.urls) {
            ImageItem(it, modifier)
        }
    }
}

@Composable
fun ImageItem(url: String,isBookmarked:Boolean, modifier: Modifier = Modifier, onBookmarked: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Column(
            modifier = modifier.height(200.dp).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BookmarkButton(isBookmarked, {onBookmarked(url)},modifier.align(Alignment.Start).padding(8.dp))
            AsyncImage(
                model = url,
                contentDescription = null
            )
        }
    }
}
