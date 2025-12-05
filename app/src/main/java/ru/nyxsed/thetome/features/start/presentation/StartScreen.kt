package ru.nyxsed.thetome.features.start.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.core.presentation.components.GameScreenBackground
import ru.nyxsed.thetome.ui.theme.DarkPurple

@Composable
fun StartScreen(
    viewModel: StartViewModel = hiltViewModel(),
    onNewGameClicked: () -> Unit,
    onRestoreGameClicked: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    StartScreenContent(
        state = state,
        onNewGameClicked = { onNewGameClicked() },
        onRestoreGameClicked = { onRestoreGameClicked() }
    )
}

@Composable
fun StartScreenContent(
    state: GameState,
    onNewGameClicked: () -> Unit,
    onRestoreGameClicked: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp

    GameScreenBackground(null)

    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Logo(modifier = Modifier.weight(1.2f))
            Spacer(modifier = Modifier.width(32.dp))
            Buttons(
                modifier = Modifier.weight(1f),
                state = state,
                onNewGameClicked = onNewGameClicked,
                onRestoreGameClicked = onRestoreGameClicked,
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 48.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Logo(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(48.dp))
            Buttons(
                modifier = Modifier.fillMaxWidth(),
                state = state,
                onNewGameClicked = onNewGameClicked,
                onRestoreGameClicked = onRestoreGameClicked,
            )
        }
    }
}

@Composable
fun Logo(
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.icon_tome),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.text_app_name),
            color = Color.Black,
            fontSize = 48.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Buttons(
    modifier: Modifier,
    state: GameState,
    onNewGameClicked: () -> Unit,
    onRestoreGameClicked: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = onNewGameClicked,
            colors = ButtonDefaults.buttonColors()
                .copy(containerColor = DarkPurple)
        ) {
            Text(
                text = stringResource(R.string.text_new_game),
                color = Color.White,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = onRestoreGameClicked,
            colors = ButtonDefaults.buttonColors()
                .copy(containerColor = DarkPurple, disabledContainerColor = DarkPurple.copy(alpha = 0.5f)),
            enabled = state.scenery != null
        ) {
            Text(
                stringResource(R.string.text_restore_game),
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}