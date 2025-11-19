package ru.nyxsed.thetome.features.settings.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.ItemType
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.domain.models.RoleType
import ru.nyxsed.thetome.core.domain.models.Scenery
import ru.nyxsed.thetome.core.presentation.components.CircleItem

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onStartGameClicked: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    SettingsContent(
        state = state,
        onPlayerCountChanged = { viewModel.changePlayerCount(it) },
        onSceneryChanged = { viewModel.changeScenery(it) },
        onRoleSelected = { viewModel.toggleRoleSelection(it) },
        onStartGameClicked = {
            viewModel.saveGameState()
            onStartGameClicked()
        }
    )
}

@Composable
fun SettingsContent(
    state: SettingsState,
    onPlayerCountChanged: (Int) -> Unit,
    onSceneryChanged: (Scenery) -> Unit,
    onRoleSelected: (Role) -> Unit,
    onStartGameClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PlayerCountSlider(
            sliderPosition = state.playerCount.toFloat(),
            onValueChange = {
                onPlayerCountChanged(it.toInt())
            }
        )

        SceneryDropdown(
            items = Scenery.all,
            selectedScenery = state.selectedScenery,
            onSelected = { onSceneryChanged(it) },
        )

        RoleSelector(
            availableRoles = state.selectedScenery?.roles,
            selectedRoles = state.chosenRoles,
            maxSelection = state.playerCount,
            onRoleClick = { onRoleSelected(it) },
            roleDistribution = state.roleDistribution,
        )

        Button(
            enabled = state.chosenRoles.size == state.playerCount,
            onClick = { onStartGameClicked() }
        ) {
            Text(stringResource(R.string.text_start_game))
        }
    }
}

@Composable
fun PlayerCountSlider(
    sliderPosition: Float,
    onValueChange: (Float) -> Unit,
) {
    Text(stringResource(R.string.text_player_count, sliderPosition.toInt()))
    Slider(
        modifier = Modifier.width(300.dp),
        value = sliderPosition,
        onValueChange = { onValueChange(it) },
        steps = 9,
        valueRange = 5f..15f,
    )
}

@Composable
fun SceneryDropdown(
    items: List<Scenery>,
    selectedScenery: Scenery?,
    onSelected: (Scenery) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(stringResource(R.string.text_scenery))
        Box {
            TextButton(onClick = { expanded = true }) {
                val title = selectedScenery?.sceneryNameRes?.let { stringResource(it) }
                    ?: stringResource(R.string.text_choose_scenery)
                Text(title)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { scenery ->
                    DropdownMenuItem(
                        text = { Text(stringResource(scenery.sceneryNameRes)) },
                        onClick = {
                            onSelected(scenery)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun RoleSelector(
    availableRoles: List<Role>?,
    selectedRoles: List<Role>,
    maxSelection: Int,
    onRoleClick: (Role) -> Unit,
    roleDistribution: Map<RoleType, Int>,
) {
    RoleType.entries.forEach { roleType ->
        val selectedRolesCountByType = selectedRoles.filter { it.type == roleType }.size
        Text(
            text = "${stringResource(roleType.resId)}: $selectedRolesCountByType / ${roleDistribution[roleType]}",
            modifier = Modifier.padding(top = 8.dp)
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            availableRoles
                ?.filter { role ->
                    role.type == roleType
                }
                ?.forEach { role ->
                    val isSelected = selectedRoles.contains(role)
                    val isEnabled = isSelected || selectedRoles.size < maxSelection

                    CircleItem(
                        itemType = ItemType.PLAYER_CIRCLE,
                        size = 70.dp,
                        centerIcon = role.iconRes,
                        bottomText = stringResource(role.roleName),
                        isClickableEnabled = isEnabled,
                        isSelected = isSelected,
                        isEnabled = isEnabled,
                        haveGhostVote = false,
                        onClick = {
                            onRoleClick(role)
                        }
                    )

                }
        }
    }

    Text(
        text = stringResource(R.string.text_players, selectedRoles.size, maxSelection),
        modifier = Modifier.padding(top = 8.dp)
    )
}