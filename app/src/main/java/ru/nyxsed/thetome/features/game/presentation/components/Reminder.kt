package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.nyxsed.thetome.core.domain.models.Action
import ru.nyxsed.thetome.core.presentation.components.CircleItem

@Composable
fun Reminder(
    modifier: Modifier = Modifier,
    action: Action?,
    onBeforeClicked: () -> Unit,
    onAfterClicked: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        SmallRoundIconButton(
            onClick = onBeforeClicked,
            icon = {
                Icon(
                    contentDescription = "before",
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )

        Column(modifier = Modifier
            .padding(8.dp)
            .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (action?.role != null) {
                CircleItem(
                    size = 80.dp,
                    backgroundColor = Color.DarkGray,
                    bottomText = action.role.roleId.name,
                )
            }
            action?.let {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = stringResource(it.actionResId),
                    textAlign = TextAlign.Center
                )
            }
        }

        SmallRoundIconButton(
            onClick = onAfterClicked,
            icon = {
                Icon(
                    contentDescription = "after",
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}