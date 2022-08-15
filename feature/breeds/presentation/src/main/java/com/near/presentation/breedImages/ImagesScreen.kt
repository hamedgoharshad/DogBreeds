package com.near.presentation.breedImages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.near.common.domain.model.Bookmark
import com.near.domain.model.Breed
import com.near.presentation.breedImages.component.BookmarkButton

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ImagesRoute(
    modifier: Modifier = Modifier,
    viewModel: ImagesViewModel = hiltViewModel(),
    navigateToBookmark: () -> Unit,
) {
    val uiState by viewModel.imagesUiState.collectAsStateWithLifecycle()
    ImagesScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToBookmark = navigateToBookmark,
        viewModel::addBookmark,
    )
}

@Composable
fun ImagesScreen(
    modifier: Modifier,
    uiState: ImagesUiState,
    navigateToBookmark: () -> Unit,
    onBookmarked: (Bookmark) -> Unit,
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
                            navigateToBookmark()
                        }
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .padding(8.dp),
                    colorFilter = ColorFilter.lighting(Color.Red, Color.Red)
                )
                if (uiState is ImagesUiState.Success)
                    Text(
                        text = uiState.breed.name,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxSize(), style = MaterialTheme.typography.h4
                    )
            }
        }) {
        when (uiState) {
            ImagesUiState.Loading -> {}
            is ImagesUiState.Failed -> {}
            is ImagesUiState.Success -> {
                ImagesContent(modifier, uiState, onBookmarked)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesContent(
    modifier: Modifier,
    uiState: ImagesUiState.Success,
    onBookmarked: (Bookmark) -> Unit
) {
    LazyVerticalGrid(cells = GridCells.Fixed(2), Modifier.padding(8.dp)) {
        uiState.run {
            items(urls) { url ->
                ImageItem(
                    url,
                    bookmarks.map { bookmark -> bookmark.id }.contains(url),
                    breed.name,
                    onBookmarked,
                    modifier
                )
            }
        }
    }
}

@Composable
fun ImageItem(
    url: String,
    isBookmarked: Boolean,
    breed: String,
    onBookmarked: (Bookmark) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(2.dp),
        backgroundColor = MaterialTheme.colors.surface, modifier = Modifier.padding(4.dp)
    ) {
        Column(
            modifier = modifier
                .height(200.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BookmarkButton(
                isBookmarked,
                { onBookmarked(Bookmark(url, breed)) },
                modifier
                    .align(Alignment.Start)
                    .padding(2.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(com.near.common.presentation.R.drawable.ic_placeholder),
                contentDescription = null,
                contentScale = ContentScale.None,
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Preview
@Composable
fun ImagePrev() {
    ImagesContent(
        modifier = Modifier, uiState = ImagesUiState.Success(
            listOf("fff", "fff", "fff", "fff", "fff"), listOf(
                Bookmark("f", "fefe"),
                Bookmark("f", "fefe"),
                Bookmark("f", "fefe"),
                Bookmark("f", "fefe"),
                Bookmark("f", "fefe"),
                Bookmark("f", "fefe"),
            ), Breed("")
        ), onBookmarked = {})

}
