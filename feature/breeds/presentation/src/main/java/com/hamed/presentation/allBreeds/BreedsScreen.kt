package com.hamed.presentation.allBreeds

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hamed.common.presentation.compose.component.FailureScreen
import com.hamed.common.presentation.compose.component.LoadingScreen
import com.hamed.domain.model.Breed

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun BreedsRoute(
    modifier: Modifier = Modifier,
    viewModel: BreedsViewModel = hiltViewModel(),
    navigateToBookmark: () -> Unit,
    navigateToImages: (String) -> Unit,
) {
    val uiState by viewModel.breedsUiState.collectAsStateWithLifecycle()

    BreedsScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToBookmark = navigateToBookmark,
        navigateToImages = navigateToImages
    )
}

@Composable
fun BreedsScreen(
    modifier: Modifier,
    uiState: BreedsUiState,
    navigateToBookmark: () -> Unit,
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
                            navigateToBookmark()
                        }
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .padding(8.dp),
                    colorFilter = ColorFilter.lighting(Color.Red, Color.Red)
                )
            }
        }) {
         when (uiState) {
             BreedsUiState.Loading -> {
                 LoadingScreen()
             }
             is BreedsUiState.Failed -> {
                 FailureScreen(uiState.exception.message?: String())
             }
             is BreedsUiState.Success -> {
                 BreedsContent(modifier, uiState, navigateToImages)
             }
         }
    }
}

@Composable
fun BreedsContent(
    modifier: Modifier = Modifier,
    uiState: BreedsUiState.Success,
    navigateToImages: (String) -> Unit
) {
    LazyColumn(Modifier.padding(8.dp)) {
        items(uiState.breeds) {
            BreedItem(it, modifier, navigateToImages)
        }
    }
}

@Composable
fun BreedItem(breed: Breed, modifier: Modifier = Modifier, onClicked: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = .1f),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClicked(breed.name) }, elevation = 5.dp
    ) {
        Column(
            modifier = modifier
                .wrapContentHeight(Alignment.CenterVertically)
                .padding(16.dp),
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

@Preview
@Composable
fun BreedPrev() {
    MaterialTheme {
        BreedsContent(uiState = BreedsUiState.Success(
            listOf(
                Breed("hadm"),
                Breed("hadm"),
                Breed("hadm"),
                Breed("hadm"),
                Breed("hadm"),
                Breed("hadm"),
            )
        ), navigateToImages = {})
    }
}