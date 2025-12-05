package ru.nyxsed.thetome.features.settings.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp

    GameScreenBackground(null)

    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    WindowInsets.safeDrawing
                        .only(WindowInsetsSides.Top)
                        .asPaddingValues()
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1.8f)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                RoleSelector(
                    availableRoles = state.selectedScenery?.roles,
                    selectedRoles = state.chosenRoles,
                    maxSelection = state.playerCount,
                    onRoleClick = { onRoleSelected(it) },
                    roleDistribution = state.roleDistribution,
                    isLandscape = isLandscape
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                SceneryDropdown(
                    items = listScenery,
                    selectedScenery = state.selectedScenery,
                    onSelected = { onSceneryChanged(it) },
                    isLandscape = isLandscape
                )
                Spacer(modifier = Modifier.height(32.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.text_players, state.chosenRoles.size, state.playerCount),
                        fontSize = 24.sp,
                        color = if (state.chosenRoles.size == state.playerCount) DarkPurple else Color.Gray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    PlayerCountSlider(
                        sliderPosition = state.playerCount.toFloat(),
                        maxPlayers = state.selectedScenery?.maxPlayers ?: 15,
                        onValueChange = { onPlayerCountChanged(it.toInt()) },
                        isLandscape = isLandscape
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    enabled = state.chosenRoles.size == state.playerCount,
                    onClick = { onStartGameClicked() },
                    colors = ButtonDefaults.buttonColors()
                        .copy(containerColor = DarkPurple, disabledContainerColor = DarkPurple.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = stringResource(R.string.text_start_game),
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
        }
    } else {
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
            SceneryDropdown(
                items = listScenery,
                selectedScenery = state.selectedScenery,
                onSelected = { onSceneryChanged(it) },
                isLandscape = isLandscape
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = stringResource(R.string.text_players, state.chosenRoles.size, state.playerCount),
                fontSize = 20.sp,
                color = if (state.chosenRoles.size == state.playerCount) DarkPurple else Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            PlayerCountSlider(
                sliderPosition = state.playerCount.toFloat(),
                maxPlayers = state.selectedScenery?.maxPlayers ?: 15,
                onValueChange = { onPlayerCountChanged(it.toInt()) },
                isLandscape = isLandscape
            )

            Spacer(modifier = Modifier.height(6.dp))

            RoleSelector(
                availableRoles = state.selectedScenery?.roles,
                selectedRoles = state.chosenRoles,
                maxSelection = state.playerCount,
                onRoleClick = { onRoleSelected(it) },
                roleDistribution = state.roleDistribution,
                isLandscape = isLandscape
            )

            Spacer(modifier = Modifier.height(6.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                enabled = state.chosenRoles.size == state.playerCount,
                onClick = { onStartGameClicked() },
                colors = ButtonDefaults.buttonColors()
                    .copy(containerColor = DarkPurple, disabledContainerColor = DarkPurple.copy(alpha = 0.5f))
            ) {
                Text(
                    text = stringResource(R.string.text_start_game),
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun PlayerCountSlider(
    maxPlayers: Int,
    sliderPosition: Float,
    onValueChange: (Float) -> Unit,
    isLandscape: Boolean
) {
    LaunchedEffect(maxPlayers) {
        if (sliderPosition > maxPlayers) {
            onValueChange(maxPlayers.toFloat())
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Slider(
            modifier = Modifier.width(if (isLandscape) 280.dp else 300.dp),
            value = sliderPosition,
            onValueChange = { onValueChange(it) },
            steps = 9,
            valueRange = 5f..maxPlayers.toFloat(),
            colors = SliderDefaults.colors()
                .copy(thumbColor = DarkPurple, activeTrackColor = DarkPurple, inactiveTrackColor = DarkPurple.copy(alpha = 0.5f))
        )
    }
}

@Composable
fun SceneryDropdown(
    items: List<Scenery>,
    selectedScenery: Scenery?,
    onSelected: (Scenery) -> Unit,
    isLandscape: Boolean
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.text_scenery),
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = if (isLandscape) 22.sp else 20.sp
        )

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
                            modifier = Modifier.size(32.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(Modifier.width(16.dp))
                    }

                    val title = selectedScenery?.sceneryNameRes?.let { stringResource(it) }
                        ?: stringResource(R.string.text_choose_scenery)
                    Text(
                        text = title,
                        fontSize = if (isLandscape) 18.sp else 16.sp
                    )
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.widthIn(min = 280.dp)
            ) {
                items.forEach { scenery ->
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(scenery.iconRes),
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp),
                                    tint = Color.Unspecified
                                )
                                Spacer(Modifier.width(16.dp))
                                Text(
                                    text = stringResource(scenery.sceneryNameRes),
                                    fontSize = 16.sp
                                )
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RoleSelector(
    availableRoles: List<Role>?,
    selectedRoles: List<Role>,
    maxSelection: Int,
    onRoleClick: (Role) -> Unit,
    roleDistribution: Map<RoleType, Int>,
    isLandscape: Boolean
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

    val itemsPerRow = if (isLandscape) 6 else 5
    val circleSize = if (isLandscape) 50.dp else 60.dp
    val horizontalSpacing = if (isLandscape) 8.dp else 8.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (isLandscape) 0.dp else 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoleType.entries.filterNot { it == RoleType.TRAVELLER || it == RoleType.FABLED }.forEach { roleType ->
            val selectedRolesCountByType = selectedRoles.filter { it.type == roleType }.size

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(roleType.getDisplayNameId()),
                        fontSize = if (isLandscape) 18.sp else 20.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "$selectedRolesCountByType/${roleDistribution[roleType]}",
                        fontSize = if (isLandscape) 18.sp else 20.sp,
                        color = if (selectedRolesCountByType == roleDistribution[roleType]) DarkPurple else Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(horizontalSpacing, Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    maxItemsInEachRow = itemsPerRow
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
                                size = circleSize,
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
        }
    }
}