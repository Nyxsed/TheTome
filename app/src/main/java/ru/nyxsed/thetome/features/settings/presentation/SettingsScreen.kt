package ru.nyxsed.thetome.features.settings.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.data.RoleTypeMapper.getDisplayNameId
import ru.nyxsed.thetome.core.domain.models.ItemType
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.domain.models.RoleType
import ru.nyxsed.thetome.core.domain.models.Scenery
import ru.nyxsed.thetome.core.presentation.components.CircleItem
import ru.nyxsed.thetome.core.presentation.components.GameScreenBackground
import ru.nyxsed.thetome.core.presentation.components.RoleInfoDialog
import ru.nyxsed.thetome.ui.theme.DarkPurple

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onStartGameClicked: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    SettingsContent(
        state = state,
        listScenery = viewModel.listScenery,
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
    listScenery: List<Scenery>,
    onPlayerCountChanged: (Int) -> Unit,
    onSceneryChanged: (Scenery) -> Unit,
    onRoleSelected: (Role) -> Unit,
    onStartGameClicked: () -> Unit,
) {
    GameScreenBackground(null)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                WindowInsets.safeDrawing
                    .only(WindowInsetsSides.Top)
                    .asPaddingValues()
            )
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PlayerCountSlider(
            sliderPosition = state.playerCount.toFloat(),
            maxPlayers = state.selectedScenery?.maxPlayers ?: 15,
            onValueChange = {
                onPlayerCountChanged(it.toInt())
            }
        )

        SceneryDropdown(
            items = listScenery,
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
            modifier = Modifier.padding(bottom = 30.dp),
            enabled = state.chosenRoles.size == state.playerCount,
            onClick = { onStartGameClicked() },
            colors = ButtonDefaults.buttonColors()
                .copy(containerColor = DarkPurple, disabledContainerColor = DarkPurple.copy(alpha = 0.5f))
        ) {
            Text(text = stringResource(R.string.text_start_game), color = Color.White)
        }
    }
}

@Composable
fun PlayerCountSlider(
    maxPlayers: Int,
    sliderPosition: Float,
    onValueChange: (Float) -> Unit,
) {
    LaunchedEffect(maxPlayers) {
        if (sliderPosition > maxPlayers) {
            onValueChange(maxPlayers.toFloat())
        }
    }
    Text(stringResource(R.string.text_player_count, sliderPosition.toInt()))
    Slider(
        modifier = Modifier.width(300.dp),
        value = sliderPosition,
        onValueChange = { onValueChange(it) },
        steps = 9,
        valueRange = 5f..maxPlayers.toFloat(),
        colors = SliderDefaults.colors()
            .copy(thumbColor = DarkPurple, activeTrackColor = DarkPurple, inactiveTrackColor = DarkPurple.copy(alpha = 0.5f))
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
            TextButton(
                colors = ButtonDefaults.textButtonColors().copy(contentColor = DarkPurple),
                onClick = { expanded = true }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    selectedScenery?.let { scenery ->
                        Icon(
                            painter = painterResource(scenery.iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(Modifier.width(8.dp))
                    }

                    val title = selectedScenery?.sceneryNameRes?.let { stringResource(it) }
                        ?: stringResource(R.string.text_choose_scenery)
                    Text(title)
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { scenery ->
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(scenery.iconRes),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Unspecified
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(stringResource(scenery.sceneryNameRes))
                            }
                        },
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
    var isRoleInfoDialogRaised by remember { mutableStateOf(false) }
    var roleInfoTarget by remember { mutableStateOf<Role?>(null) }
    if (isRoleInfoDialogRaised && roleInfoTarget != null) {
        RoleInfoDialog(
            role = roleInfoTarget!!,
            onDismiss = {
                isRoleInfoDialogRaised = false
                roleInfoTarget = null
            }
        )
    }

    RoleType.entries.filterNot { it == RoleType.TRAVELLER || it == RoleType.FABLED }.forEach { roleType ->
        val selectedRolesCountByType = selectedRoles.filter { it.type == roleType }.size
        Text(
            text = "${stringResource(roleType.getDisplayNameId())}: $selectedRolesCountByType / ${roleDistribution[roleType]}",
            modifier = Modifier.padding(top = 8.dp)
        )

        FlowRow(
            modifier = Modifier
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 4
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
                        isSelected = isSelected,
                        isEnabled = isEnabled,
                        haveGhostVote = false,
                        onClick = {
                            onRoleClick(role)
                        },
                        onLongClick = {
                            roleInfoTarget = role
                            isRoleInfoDialogRaised = true
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