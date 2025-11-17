package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.nyxsed.thetome.core.presentation.components.CircleMenuItem
import kotlin.collections.isNotEmpty


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopButtonsRow(
    modifier: Modifier = Modifier,
    menuItems: List<CardsMenuItem> = emptyList(),
    onEditClicked: () -> Unit,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 12.dp, end = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SmallRoundIconButton(
            onClick = {
                isMenuExpanded = true
            },
            icon = {
                if (menuItems.isNotEmpty()) {
                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = { isMenuExpanded = false }
                    ) {
                        menuItems.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item.title) },
                                onClick = {
                                    isMenuExpanded = false
                                    item.onClick()
                                }
                            )
                        }
                    }
                }

                Icon(
                    contentDescription = "Cards",
                    imageVector = Icons.Default.DateRange,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )

        Spacer(modifier = Modifier.width(12.dp))

        SmallRoundIconButton(
            onClick = onEditClicked,
            icon = {
                Icon(
                    contentDescription = "edit",
                    imageVector = Icons.Default.Edit,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}

data class CardsMenuItem(
    val title: String,
    val onClick: () -> Unit,
)