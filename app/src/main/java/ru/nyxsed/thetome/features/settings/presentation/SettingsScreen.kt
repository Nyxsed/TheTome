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
import ru.nyxsed.thetome.core.presentation.components.CustomVerticalScrollbar
import ru.nyxsed.thetome.core.presentation.components.GameScreenBackground
import ru.nyxsed.thetome.core.presentation.components.RoleInfoDialog
import ru.nyxsed.thetome.features.game.presentation.components.SmallRoundIconButton
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
        },
        onRandomClicked = { viewModel.randomRoleSelection() },
        onAllowMultipleChanged = { viewModel.setAllowMultipleRoles(it) }
    )
}

@Composable
fun SettingsContent(
    state: SettingsState,
    listScenery: List<Scenery>,
    onPlayerCountChanged: (Int) -> Unit,
    onSceneryChanged: (Scenery) -> Unit,
    onRoleSelected: (Pair<Pair<Role, Int>, Boolean>) -> Unit,
    onStartGameClicked: () -> Unit,
    onRandomClicked: () -> Unit,
    onAllowMultipleChanged: (Boolean) -> Unit,
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
            Box(
                modifier = Modifier
                    .weight(1.8f)
                    .fillMaxHeight()
                    .heightIn(max = 600.dp)
            ) {
                val scrollState = rememberScrollState()
                val needsScrollbar = scrollState.maxValue > 0

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .verticalScroll(scrollState)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .padding(end = if (needsScrollbar) 8.dp else 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    RoleSelector(
                        availableRoles = state.selectedScenery?.roles,
                        selectedRoles = state.chosenRoles,
                        maxSelection = state.playerCount,
                        onRoleClick = { onRoleSelected(it) },
                        roleDistribution = state.roleDistribution,
                        isLandscape = isLandscape,
                        allowMultipleRoles = state.allowMultipleRoles
                    )
                }

                if (needsScrollbar) {
                    CustomVerticalScrollbar(
                        scrollState = scrollState,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight()
                            .width(8.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .heightIn(max = 600.dp)
            ) {
                val scrollState = rememberScrollState()
                val needsScrollbar = scrollState.maxValue > 0

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .verticalScroll(scrollState)
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                        .padding(end = if (needsScrollbar) 8.dp else 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    SceneryDropdown(
                        items = listScenery,
                        selectedScenery = state.selectedScenery,
                        onSelected = { onSceneryChanged(it) },
                        isLandscape = isLandscape
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    MultipleRolesSwitch(
                        allowMultipleRoles = state.allowMultipleRoles,
                        onAllowMultipleChanged = onAllowMultipleChanged,
                        isLandscape = isLandscape
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.text_players, state.chosenRoles.size, state.playerCount),
                            fontSize = 24.sp,
                            color = if (state.chosenRoles.size == state.playerCount) DarkPurple else Color.Gray,
                        )

                        PlayerCountSlider(
                            sliderPosition = state.playerCount.toFloat(),
                            maxPlayers = state.selectedScenery?.maxPlayers ?: 15,
                            isLandscape = isLandscape,
                            onValueChange = { onPlayerCountChanged(it.toInt()) },
                            onRandomClicked = onRandomClicked
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

                if (needsScrollbar) {
                    CustomVerticalScrollbar(
                        scrollState = scrollState,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight()
                            .width(8.dp)
                    )
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    WindowInsets.safeDrawing
                        .only(WindowInsetsSides.Top)
                        .asPaddingValues()
                )
                .heightIn(max = 800.dp)
        ) {
            val scrollState = rememberScrollState()
            val needsScrollbar = scrollState.maxValue > 0

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(end = if (needsScrollbar) 8.dp else 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SceneryDropdown(
                    items = listScenery,
                    selectedScenery = state.selectedScenery,
                    onSelected = { onSceneryChanged(it) },
                    isLandscape = isLandscape
                )

                Spacer(modifier = Modifier.height(6.dp))

                MultipleRolesSwitch(
                    allowMultipleRoles = state.allowMultipleRoles,
                    onAllowMultipleChanged = onAllowMultipleChanged,
                    isLandscape = isLandscape
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = stringResource(R.string.text_players, state.chosenRoles.size, state.playerCount),
                    fontSize = 20.sp,
                    color = if (state.chosenRoles.size == state.playerCount) DarkPurple else Color.Gray,
                )

                Spacer(modifier = Modifier.height(6.dp))

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PlayerCountSlider(
                        sliderPosition = state.playerCount.toFloat(),
                        maxPlayers = state.selectedScenery?.maxPlayers ?: 15,
                        isLandscape = isLandscape,
                        onValueChange = { onPlayerCountChanged(it.toInt()) },
                        onRandomClicked = onRandomClicked
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                RoleSelector(
                    availableRoles = state.selectedScenery?.roles,
                    selectedRoles = state.chosenRoles,
                    maxSelection = state.playerCount,
                    onRoleClick = { onRoleSelected(it) },
                    roleDistribution = state.roleDistribution,
                    isLandscape = isLandscape,
                    allowMultipleRoles = state.allowMultipleRoles
                )

                Spacer(modifier = Modifier.height(6.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
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

            if (needsScrollbar) {
                CustomVerticalScrollbar(
                    scrollState = scrollState,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxHeight()
                        .width(8.dp)
                )
            }
        }
    }
}

@Composable
fun MultipleRolesSwitch(
    allowMultipleRoles: Boolean,
    onAllowMultipleChanged: (Boolean) -> Unit,
    isLandscape: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (isLandscape) 0.dp else 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.text_allow_multiple_roles),
            fontSize = if (isLandscape) 18.sp else 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(end = 12.dp)
        )
        Switch(
            checked = allowMultipleRoles,
            onCheckedChange = onAllowMultipleChanged,
            colors = SwitchDefaults.colors(
                checkedThumbColor = DarkPurple,
                checkedTrackColor = DarkPurple.copy(alpha = 0.5f),
                uncheckedThumbColor = Color.Gray,
                uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f)
            )
        )
    }
}

@Composable
fun PlayerCountSlider(
    maxPlayers: Int,
    sliderPosition: Float,
    isLandscape: Boolean,
    onValueChange: (Float) -> Unit,
    onRandomClicked: () -> Unit
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
        Row {
            Slider(
                modifier = Modifier.width(if (isLandscape) 230.dp else 260.dp),
                value = sliderPosition,
                onValueChange = { onValueChange(it) },
                steps = 9,
                valueRange = 5f..maxPlayers.toFloat(),
                colors = SliderDefaults.colors()
                    .copy(thumbColor = DarkPurple, activeTrackColor = DarkPurple, inactiveTrackColor = DarkPurple.copy(alpha = 0.5f))
            )

            Spacer(modifier = Modifier.width(6.dp))

            SmallRoundIconButton(
                onClick = onRandomClicked,
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        contentDescription = "Random",
                        painter = painterResource(R.drawable.ic_dice),
                        tint = Color.White
                    )
                }
            )
        }
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
            modifier = Modifier.padding(bottom = 6.dp),
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
    onRoleClick: (Pair<Pair<Role, Int>, Boolean>) -> Unit,
    roleDistribution: Map<RoleType, Int>,
    isLandscape: Boolean,
    allowMultipleRoles: Boolean
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
            // Считаем количество выбранных ролей по типу
            val selectedRolesByType = selectedRoles.filter { it.type == roleType }
            val selectedRolesCountByType = selectedRolesByType.size
            val maxForType = roleDistribution[roleType] ?: 0

            // Базовые уникальные роли из сценария
            val baseRoles = availableRoles
                ?.filter { role -> role.type == roleType }
                ?.distinctBy { it } ?: emptyList()

            // Для каждой базовой роли строим список отображаемых копий
            val displayRoles = buildList<Pair<Role, Int>> {
                baseRoles.forEach { role ->
                    // Считаем, сколько раз эта роль выбрана
                    val selectedCount = selectedRolesByType.count { it == role }

                    // Всегда показываем оригинал
                    add(role to 1)

                    if (allowMultipleRoles && selectedCount > 0) {
                        // Показываем выбранные копии
                        for (copyNum in 2..selectedCount) {
                            add(role to copyNum)
                        }

                        // Показываем следующую копию
                        if (selectedRoles.size < maxSelection) {
                            add(role to (selectedCount + 1))
                        }
                    }
                }
            }

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
                        text = "$selectedRolesCountByType/$maxForType",
                        fontSize = if (isLandscape) 18.sp else 20.sp,
                        color = if (selectedRolesCountByType == maxForType) DarkPurple else Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(horizontalSpacing, Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    maxItemsInEachRow = itemsPerRow
                ) {
                    displayRoles.forEach { (role, copyNum) ->
                        // Копия выбрана если ее номер <= количеству выбранных копий
                        val selectedCount = selectedRolesByType.count { it == role }
                        val isSelected = copyNum <= selectedCount

                        // Определяем, на какую именно копию кликаем
                        val isEnabled = if (allowMultipleRoles) {
                            true
                        } else {
                            isSelected || (selectedRolesCountByType < maxForType && selectedRoles.size < maxSelection)
                        }

                        CircleItem(
                            itemType = ItemType.PLAYER_CIRCLE,
                            size = circleSize,
                            centerIcon = role.iconRes,
                            bottomText = if (allowMultipleRoles && copyNum > 1) {
                                "${stringResource(role.roleName)} ($copyNum)"
                            } else {
                                stringResource(role.roleName)
                            },
                            isSelected = isSelected,
                            isEnabled = isEnabled,
                            haveGhostVote = false,
                            onClick = {
                                onRoleClick(role to copyNum to isSelected)
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