package com.near.presentation.breeds

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    viewModel: BreedsViewModel = viewModel(),
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()
    val formState = viewModel.formState
    viewModel.navController = navController
    LoginScreen(
        modifier = modifier,
        uiState = uiState,
        viewModel.uiState::handleEvent,
        formState
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier,
    uiState: LoginUiState,
    handleEvent: (LoginIntent) -> Unit,
    formState: FormState<TextFieldState>
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Row(
                Modifier
                    .background(colorResource(id = R.color.colorPrimaryDark))
                    .fillMaxWidth()
                    .fillMaxHeight(
                        0.08f
                    )
                    .padding(start = 4.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_support_black),
                    contentDescription = "support",
                    Modifier
                        .clickable {

/*TODO*/

                        }
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .padding(8.dp),
                    colorFilter = ColorFilter.lighting(Color.White, Color.White)
                )
            }
        }) {
        LoginContent(modifier, uiState, handleEvent, formState)
    }
}

@Composable
fun LoginContent(
    modifier: Modifier,
    uiState: LoginUiState,
    handleEvent: (LoginIntent) -> Unit,
    formState: FormState<TextFieldState>
) {
    val usernameState: TextFieldState = formState.getState("username")
    val passwordState: TextFieldState = formState.getState("password")

}
