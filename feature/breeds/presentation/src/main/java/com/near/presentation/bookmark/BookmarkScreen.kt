package com.near.presentation.bookmark

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.near.common.domain.model.Bookmark
import com.near.common.presentation.R
import com.near.domain.model.Breed
import com.near.presentation.allBreeds.BreedsViewModel
import com.near.presentation.breedImages.BookmarkButton

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun BreedsRoute(
    modifier: Modifier = Modifier,
    viewModel: BreedsViewModel = viewModel(),
    navigateToFavorites: () -> Unit,
    navigateToImages: (String) -> Unit,
) {
    val uiState by viewModel.breedsUiState.collectAsStateWithLifecycle()

    BreedsScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToFavorites = navigateToFavorites,
        navigateToImages = navigateToImages
    )
}

@Composable
fun BreedsScreen(
    modifier: Modifier,
    uiState: BreedsUiState,
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
            BreedsUiState.Loading -> {}
            is BreedsUiState.Failed -> {}
            is BreedsUiState.Success -> {
                BreedsContent(modifier, uiState,navigateToImages)
            }
        }
    }
}

@Composable
fun BreedsContent(
    modifier: Modifier,
    uiState: BreedsUiState.Success,
    navigateToImages: (String) -> Unit
) {
    LazyColumn {
        items(uiState.breeds) {
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

@Composable
fun BookmarkItem(
    url: String,
    isBookmarked: Boolean,
    breed: String,
    onBookmarked: (Bookmark) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Column(
            modifier = modifier.height(200.dp).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BookmarkButton(
                isBookmarked,
                { onBookmarked(Bookmark(url, breed)) },
                modifier.align(Alignment.Start).padding(8.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)
            )
        }
    }
}
