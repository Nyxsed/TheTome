package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopButtonsRow(
    modifier: Modifier = Modifier,
    onAdd: () -> Unit,
    onEdit: () -> Unit,
    onSettings: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 12.dp, end = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SmallRoundIconButton(
            onClick = onAdd,
            icon = {
                Icon(
                    contentDescription = "Add",
                    imageVector = Icons.Default.Add,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )

        Spacer(modifier = Modifier.width(12.dp))

        SmallRoundIconButton(
            onClick = onEdit,
            icon = {
                Icon(
                    contentDescription = "edit",
                    imageVector = Icons.Default.Edit,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )

        Spacer(modifier = Modifier.width(12.dp))

        SmallRoundIconButton(
            onClick = onSettings,
            icon = {
                Icon(
                    contentDescription = "settings",
                    imageVector = Icons.Default.Settings,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}

@Composable
fun SmallRoundIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primary,
        tonalElevation = 2.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
    }
}