package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.nyxsed.thetome.core.domain.models.Role


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopButtonsRow(
    modifier: Modifier = Modifier,
    menuItems: List<CardsMenuItem> = emptyList(),
    sceneryRoles: List<Role>?,
    fabledEnabled: Boolean,
    positionMode: Boolean,
    onFabledClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onMemoClicked: () -> Unit,
    onShareClicked: () -> Unit,
    onAddPlayerClicked: () -> Unit,
    onLockClicked: () -> Unit,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    var isDialogVisible by remember { mutableStateOf(false) }
    var dialogItem by remember { mutableStateOf<CardsMenuItem?>(null) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SmallRoundIconButton(
            onClick = {
                onFabledClicked()
            },
            isChecked = fabledEnabled,
            icon = {
                Icon(
                    contentDescription = "Fabled",
                    imageVector = Icons.Default.AccountCircle,
                    tint = Color.White
                )
            }
        )

        Spacer(modifier = Modifier.width(8.dp))
        SmallRoundIconButton(
            onClick = {
                onLockClicked()
            },
            isChecked = positionMode,
            icon = {
                Icon(
                    contentDescription = "Lock",
                    imageVector = Icons.Default.Lock,
                    tint = Color.White
                )
            }
        )

        Spacer(modifier = Modifier.width(8.dp))
        SmallRoundIconButton(
            onClick = {
                isMenuExpanded = true
            },
            icon = {
                Icon(
                    contentDescription = "Cards",
                    imageVector = Icons.Default.DateRange,
                    tint = Color.White
                )
            }
        )

        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { isMenuExpanded = false }
        ) {
            menuItems.forEach { item ->
                DropdownMenuItem(
                    text = { Text(stringResource(item.titleId)) },
                    onClick = {
                        isMenuExpanded = false

                        if (item.showPickerDialog) {
                            dialogItem = item
                            isDialogVisible = true
                        } else {
                            item.onClick(null)
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))
        SmallRoundIconButton(
            onClick = onMemoClicked,
            icon = {
                Icon(
                    contentDescription = "memo",
                    imageVector = Icons.Default.Edit,
                    tint = Color.White
                )
            }
        )

        Spacer(modifier = Modifier.width(8.dp))
        SmallRoundIconButton(
            onClick = onEditClicked,
            icon = {
                Icon(
                    contentDescription = "edit",
                    imageVector = Icons.Default.Settings,
                    tint = Color.White
                )
            }
        )

        Spacer(modifier = Modifier.width(8.dp))
        SmallRoundIconButton(
            onClick = onAddPlayerClicked,
            icon = {
                Icon(
                    contentDescription = "add player",
                    imageVector = Icons.Default.Add,
                    tint = Color.White
                )
            }
        )

        Spacer(modifier = Modifier.width(8.dp))
        SmallRoundIconButton(
            onClick = onShareClicked,
            icon = {
                Icon(
                    contentDescription = "share",
                    imageVector = Icons.Default.Share,
                    tint = Color.White
                )
            }
        )

        if (isDialogVisible) {
            RolePickerDialog(
                availableRoles = sceneryRoles,
                onSelectRole = {
                    dialogItem?.onClick(it)
                    isDialogVisible = true
                    dialogItem = null
                },
                onDismiss = {
                    isDialogVisible = false
                    dialogItem = null
                }
            )
        }
    }
}

data class CardsMenuItem(
    val titleId: Int,
    val showPickerDialog: Boolean = false,
    val onClick: (Role?) -> Unit,
)