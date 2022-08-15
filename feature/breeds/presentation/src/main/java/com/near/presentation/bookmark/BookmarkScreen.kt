package com.near.presentation.bookmark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.near.common.presentation.R

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun BookmarkRoute(
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val uiState by viewModel.bookmarkUiState.collectAsStateWithLifecycle()

    BookmarkScreen(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
fun BookmarkScreen(
    modifier: Modifier,
    uiState: BookmarkUiState
) {
    when (uiState) {
        BookmarkUiState.Loading -> {}
        is BookmarkUiState.Failed -> {}
        is BookmarkUiState.Success -> {
            BookmarkContent(modifier, uiState)
        }
    }
}

@Composable
fun BookmarkContent(
    modifier: Modifier,
    uiState: BookmarkUiState.Success,
) {
    LazyColumn {
        items(uiState.bookmarks) {
            BookmarkItem(it.id, it.breed, modifier)
        }
    }
}

@Composable
fun BookmarkItem(
    url: String,
    breed: String,
    //onRemoved: (Bookmark) -> Unit,
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
            /*RemoveButton(
                isBookmarked,
                { onBookmarked(Bookmark(url, breed)) },
                modifier.align(Alignment.Start).padding(8.dp)
            )*/
            Text(text = breed)
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
