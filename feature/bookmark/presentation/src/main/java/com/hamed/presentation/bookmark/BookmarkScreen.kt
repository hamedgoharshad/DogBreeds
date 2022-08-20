package com.hamed.presentation.bookmark

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hamed.common.domain.utils.ifNullReturn
import com.hamed.common.presentation.R
import com.hamed.common.presentation.compose.component.FailureScreen
import com.hamed.common.presentation.compose.component.LoadingScreen
import timber.log.Timber

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun BookmarkRoute(
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val uiState by viewModel.bookmarkUiState.collectAsStateWithLifecycle()

    BookmarkScreen(
        modifier = modifier,
        uiState = uiState,
        viewModel::onFiltered
    )
}

@Composable
fun BookmarkScreen(
    modifier: Modifier,
    uiState: BookmarkUiState,
    onFiltered: (String) -> Unit
) {
    when (uiState) {
        BookmarkUiState.Loading -> {
            LoadingScreen()
        }
        is BookmarkUiState.Failed -> {
            FailureScreen(uiState.exception.message ?: String())
        }
        is BookmarkUiState.Success -> {
            Column(Modifier.fillMaxSize()) {
                if (uiState.bookmarks.isNotEmpty()) {
                    Surface(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.1f)
                    ) {
                        FilterDropDown(
                            uiState.bookmarks.map { it.breed }.distinct(),
                            onFiltered
                        )
                    }
                }
                Timber.tag("hamed").d(uiState.toString())
                BookmarkContent(modifier, uiState)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookmarkContent(
    modifier: Modifier,
    uiState: BookmarkUiState.Success,
) {
    LazyVerticalGrid(cells = GridCells.Fixed(3), Modifier.padding(4.dp)) {
        uiState.run {
            val filtered =
                filter.ifNullReturn(bookmarks) { bookmarks.filter { it.breed == filter } }
            items(filtered) {
                BookmarkItem(it.id, it.breed, modifier)
            }
        }
    }
}

@Composable
fun BookmarkItem(
    url: String,
    breed: String,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(2.dp),
        backgroundColor = MaterialTheme.colors.surface, modifier = Modifier.padding(4.dp)
    ) {
        Column(
            modifier = modifier
                .height(200.dp)
                .padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = breed,
                modifier = Modifier.padding(vertical = 8.dp),
                style = MaterialTheme.typography.h6
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

@Composable
fun FilterDropDown(
    items: List<String>,
    onFiltered: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("all") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        Button(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .background(
                    Color.Green.copy(alpha = .2f)
                ), onClick = { expanded = true }
        ) {
            Text(
                text = selected, style = MaterialTheme.typography.h5, textAlign = TextAlign.Center
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colors.primary.copy(alpha = .3f)
                )
        ) {
            for (breed in items)
                DropdownMenuItem(onClick = {
                    selected = breed
                    onFiltered(
                        selected
                    )
                    expanded = false
                }) {
                    Text(text = breed)
                }
        }
    }
}


