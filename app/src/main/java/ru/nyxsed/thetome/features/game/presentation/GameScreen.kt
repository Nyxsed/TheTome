package ru.nyxsed.thetome.features.game.presentation

import android.content.Intent
import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.domain.models.Token
import ru.nyxsed.thetome.core.presentation.components.BackHandlerInterceptor
import ru.nyxsed.thetome.core.presentation.components.GameScreenBackground
import ru.nyxsed.thetome.features.game.presentation.components.*

@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel(),
    onEditGameClicked: () -> Unit,
    onCardClicked: (Int, List<Role?>?) -> Unit,
    onDoublePressBack: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    var qrBitmap by remember { mutableStateOf<Bitmap?>(null) }

    BackHandlerInterceptor(onDoublePressed = onDoublePressBack)

    val context = LocalContext.current
    LaunchedEffect(state.scenery, state.fabled) {
        state.scenery?.let {
            val baseUrl = context.getString(R.string.url_pocket_grimoire)
            val sceneryName = state.scenery?.let { context.getString(it.sceneryNameRes) }

            val allRoles = buildList {
                state.scenery?.roles?.let { addAll(it) }
                state.fabled?.role?.let { add(it) }
            }

            val roles = allRoles.joinToString(separator = ",") { role -> context.getString(role.roleName) }
            val finalLink = "${baseUrl}characters=$roles&name=$sceneryName".replace(" ", "")
            qrBitmap = viewModel.generateQr(finalLink)
        }
    }

    GameContent(
        state = state,
        qrBitmap = qrBitmap,
        onEditGame = onEditGameClicked,
        onCard = onCardClicked,
        onEditNotes = { viewModel.editNotes(it) },
        onChangeAliveStatus = { viewModel.changeAliveStatus(it) },
        onChangeGhostVoteStatus = { viewModel.onChangeGhostVoteStatus(it) },
        onRenamePlayer = { player, name -> viewModel.renamePlayer(player, name) },
        onChangeRole = { player, role -> viewModel.changeRole(player, role) },
        onChangeBluffs = { viewModel.changeDemonBluffs(it) },
        onMoveToPreviousAction = { viewModel.moveToPreviousAction() },
        onMoveToNextAction = { viewModel.moveToNextAction() },
        onAddToken = { player, token -> viewModel.addToken(player, token) },
        onDeleteToken = { player, tokenIndex -> viewModel.deleteToken(player, tokenIndex) },
        onAddPlayer = { viewModel.addPlayer() },
        onDeletePlayer = { viewModel.deletePlayer(it) },
        onReorderPlayers = { viewModel.updatePlayers(it) },
        onPositionChanged = { player, x, y -> viewModel.playerPositionChange(player, x, y) },
        onPositionModeChanged = { viewModel.changePositionMode()}
    )
}

@Composable
fun GameContent(
    state: GameState,
    qrBitmap: Bitmap?,
    onEditGame: () -> Unit,
    onEditNotes: (String) -> Unit,
    onCard: (Int, List<Role?>?) -> Unit,
    onChangeAliveStatus: (Player) -> Unit,
    onChangeGhostVoteStatus: (Player) -> Unit,
    onRenamePlayer: (Player, String) -> Unit,
    onChangeRole: (Player, Role?) -> Unit,
    onChangeBluffs: (List<Role?>) -> Unit,
    onMoveToPreviousAction: () -> Unit,
    onMoveToNextAction: () -> Unit,
    onAddToken: (Player, Token) -> Unit,
    onDeleteToken: (Player, Int) -> Unit,
    onAddPlayer: () -> Unit,
    onDeletePlayer: (Player) -> Unit,
    onReorderPlayers: (List<Player>) -> Unit,
    onPositionChanged: (Player, Float, Float) -> Unit,
    onPositionModeChanged: () -> Unit,
) {
    val dialogState = rememberDialogState()

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp

    GameScreenBackground(state.currentPhase)

    if (isLandscape) {
        LandscapeLayout(
            state = state,
            dialogState = dialogState,
            onCard = onCard,
            onChangeBluffs = onChangeBluffs,
            onMoveToPreviousAction = onMoveToPreviousAction,
            onMoveToNextAction = onMoveToNextAction,
            onReorderPlayers = onReorderPlayers,
            onDeleteToken = onDeleteToken,
            onPositionChanged = onPositionChanged,
            onPositionModeChanged = onPositionModeChanged,
        )
    } else {
        PortraitLayout(
            state = state,
            dialogState = dialogState,
            onCard = onCard,
            onChangeBluffs = onChangeBluffs,
            onMoveToPreviousAction = onMoveToPreviousAction,
            onMoveToNextAction = onMoveToNextAction,
            onReorderPlayers = onReorderPlayers,
            onDeleteToken = onDeleteToken,
            onPositionChanged = onPositionChanged,
            onPositionModeChanged = onPositionModeChanged
        )
    }

    DialogsHandler(
        state = state,
        qrBitmap = qrBitmap,
        dialogState = dialogState,
        onEditGame = onEditGame,
        onEditNotes = onEditNotes,
        onCard = onCard,
        onChangeAliveStatus = onChangeAliveStatus,
        onChangeGhostVoteStatus = onChangeGhostVoteStatus,
        onRenamePlayer = onRenamePlayer,
        onChangeRole = onChangeRole,
        onAddToken = onAddToken,
        onDeletePlayer = onDeletePlayer,
        onAddPlayer = onAddPlayer
    )
}

@Stable
class DialogState {
    var isEditDialogRaised by mutableStateOf(false)
    var isMemoDialogRaised by mutableStateOf(false)
    var isPickRoleDialogRaised by mutableStateOf(false)
    var isShareDialogRaised by mutableStateOf(false)
    var isAddPlayerDialogRaised by mutableStateOf(false)
    var isFabledEnabled by mutableStateOf(false)
    var targetDeletePlayer by mutableStateOf<Player?>(null)
    var targetEditPlayer by mutableStateOf<Player?>(null)
    var targetFabledPlayer by mutableStateOf<Player?>(null)
    var targetTokenPlayer by mutableStateOf<Player?>(null)
}

@Composable
fun rememberDialogState(): DialogState {
    return remember { DialogState() }
}


@Composable
private fun LandscapeLayout(
    state: GameState,
    dialogState: DialogState,
    onCard: (Int, List<Role?>?) -> Unit,
    onChangeBluffs: (List<Role?>) -> Unit,
    onMoveToPreviousAction: () -> Unit,
    onMoveToNextAction: () -> Unit,
    onReorderPlayers: (List<Player>) -> Unit,
    onDeleteToken: (Player, Int) -> Unit,
    onPositionChanged: (Player, Float, Float) -> Unit,
    onPositionModeChanged: () -> Unit,
) {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(8.dp),
            contentAlignment = Alignment.Center,
        ) {
            PlayersWheelSection(state, dialogState, onReorderPlayers, onDeleteToken, onPositionChanged)
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            TopButtonsSection(state, dialogState, onCard, onPositionModeChanged)
            Spacer(Modifier.height(16.dp))
            MiddleSection(state, onChangeBluffs, isLandscape = true)
            Spacer(Modifier.height(16.dp))
            ReminderSection(modifier = Modifier.weight(1f), state, onMoveToPreviousAction, onMoveToNextAction)
        }
    }
}

@Composable
private fun PortraitLayout(
    state: GameState,
    dialogState: DialogState,
    onCard: (Int, List<Role?>?) -> Unit,
    onChangeBluffs: (List<Role?>) -> Unit,
    onMoveToPreviousAction: () -> Unit,
    onMoveToNextAction: () -> Unit,
    onReorderPlayers: (List<Player>) -> Unit,
    onDeleteToken: (Player, Int) -> Unit,
    onPositionChanged: (Player, Float, Float) -> Unit,
    onPositionModeChanged: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                WindowInsets.safeDrawing
                    .only(WindowInsetsSides.Top)
                    .asPaddingValues()
            )
    ) {
        TopButtonsSection(state, dialogState, onCard, onPositionModeChanged)
        Spacer(Modifier.height(20.dp))
        PlayersWheelSection(state, dialogState, onReorderPlayers, onDeleteToken, onPositionChanged)
        Spacer(Modifier.height(20.dp))
        MiddleSection(state, onChangeBluffs, isLandscape = false)
        Spacer(Modifier.height(8.dp))
        ReminderSection(modifier = Modifier.weight(1f), state, onMoveToPreviousAction, onMoveToNextAction)
    }
}

@Composable
private fun TopButtonsSection(
    state: GameState,
    dialogState: DialogState,
    onCard: (Int, List<Role?>?) -> Unit,
    onPositionModeChanged: () -> Unit,
) {
    val menuItems = rememberMenuItems(state, onCard)

    TopButtonsRow(
        sceneryRoles = state.scenery?.roles,
        fabledEnabled = dialogState.isFabledEnabled,
        positionMode = state.freePosition,
        onFabledClicked = { dialogState.isFabledEnabled = !dialogState.isFabledEnabled },
        onEditClicked = { dialogState.isEditDialogRaised = true },
        onMemoClicked = { dialogState.isMemoDialogRaised = true },
        onShareClicked = { dialogState.isShareDialogRaised = true },
        onAddPlayerClicked = { dialogState.isAddPlayerDialogRaised = true },
        onLockClicked = onPositionModeChanged,
        menuItems = menuItems,
    )
}

@Composable
private fun rememberMenuItems(
    state: GameState,
    onCard: (Int, List<Role?>?) -> Unit,
): List<CardsMenuItem> {
    return remember(state.demonBluffs) {
        listOf(
            CardsMenuItem(R.string.menu_demon) {
                onCard(R.string.menu_demon, emptyList())
            },
            CardsMenuItem(R.string.menu_minions) {
                onCard(R.string.menu_minions, emptyList())
            },
            CardsMenuItem(R.string.menu_not_in_play) {
                onCard(R.string.menu_not_in_play, state.demonBluffs)
            },
            CardsMenuItem(R.string.menu_use_ability) {
                onCard(R.string.menu_use_ability, emptyList())
            },
            CardsMenuItem(R.string.menu_make_choice) {
                onCard(R.string.menu_make_choice, emptyList())
            },
            CardsMenuItem(R.string.menu_nominated_today) {
                onCard(R.string.menu_nominated_today, emptyList())
            },
            CardsMenuItem(R.string.menu_voted_today) {
                onCard(R.string.menu_voted_today, emptyList())
            },
            CardsMenuItem(R.string.menu_char_selected_you, showPickerDialog = true) { role ->
                onCard(R.string.menu_char_selected_you, listOf(role))
            },
            CardsMenuItem(R.string.menu_show_role, showPickerDialog = true) { role ->
                onCard(role?.ability!!, listOf(role))
            },
        )
    }
}

@Composable
private fun PlayersWheelSection(
    state: GameState,
    dialogState: DialogState,
    onReorderPlayers: (List<Player>) -> Unit,
    onDeleteToken: (Player, Int) -> Unit,
    onPositionChanged: (Player, Float, Float) -> Unit,
) {
    if (!state.players.isNullOrEmpty()) {
        PlayersWheel(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            players = state.players,
            fabledEnabled = dialogState.isFabledEnabled,
            fabled = state.fabled,
            freePositionMode = state.freePosition,
            onClick = { dialogState.targetEditPlayer = it },
            onFabledClick = { dialogState.targetFabledPlayer = it },
            onTokenLongClick = onDeleteToken,
            onOrderChanged = onReorderPlayers,
            onPositionChanged = onPositionChanged,
        )
    }
}

@Composable
private fun MiddleSection(
    state: GameState,
    onChangeBluffs: (List<Role?>) -> Unit,
    isLandscape: Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        if (state.players?.size!! > 6) {
            DemonBluff(
                demonBluffRoles = state.demonBluffs,
                availableRoles = state.availableBluffRoles,
                onChangeBluffs = onChangeBluffs,
            )
            if (isLandscape) Spacer(Modifier.width(8.dp))
        }
        KillParticipation(
            roleDistribution = state.roleDistribution,
            players = state.players,
            dayNumber = state.currentDay,
            currentPhase = state.currentPhase,
        )
    }
}

@Composable
private fun ReminderSection(
    modifier: Modifier,
    state: GameState,
    onMoveToPreviousAction: () -> Unit,
    onMoveToNextAction: () -> Unit,
) {
    Reminder(
        modifier = modifier,
        action = state.currentAction,
        actions = state.actions,
        currentPhase = state.currentPhase,
        onBeforeClicked = onMoveToPreviousAction,
        onAfterClicked = onMoveToNextAction,
    )
}

@Composable
private fun DialogsHandler(
    state: GameState,
    qrBitmap: Bitmap?,
    dialogState: DialogState,
    onEditGame: () -> Unit,
    onEditNotes: (String) -> Unit,
    onCard: (Int, List<Role?>?) -> Unit,
    onChangeAliveStatus: (Player) -> Unit,
    onChangeGhostVoteStatus: (Player) -> Unit,
    onRenamePlayer: (Player, String) -> Unit,
    onChangeRole: (Player, Role?) -> Unit,
    onAddToken: (Player, Token) -> Unit,
    onDeletePlayer: (Player) -> Unit,
    onAddPlayer: () -> Unit,
) {
    val context = LocalContext.current
    val takenRoles = state.players?.mapNotNull { it.role } ?: emptyList()
    val availableRoles = state.chosenRoles?.filter { role ->
        role !in takenRoles || dialogState.targetEditPlayer?.role == role
    } ?: emptyList()

    if (dialogState.targetEditPlayer != null && !dialogState.isPickRoleDialogRaised) {
        EditPlayerDialog(
            player = dialogState.targetEditPlayer!!,
            playerCount = state.players?.size ?: 0,
            onDismissRequest = { dialogState.targetEditPlayer = null },
            onChangeName = { player, name ->
                onRenamePlayer(player, name)
                dialogState.targetEditPlayer = null
            },
            onChangeAliveStatus = {
                onChangeAliveStatus(it)
                dialogState.targetEditPlayer = null
            },
            onChangeGhostVote = {
                onChangeGhostVoteStatus(it)
                dialogState.targetEditPlayer = null
            },
            onShowRolePicker = { dialogState.isPickRoleDialogRaised = true },
            onShowCardClicked = { role -> onCard(role.ability, listOf(role)) },
            onChooseTokenClicked = {
                dialogState.targetTokenPlayer = it
                dialogState.targetEditPlayer = null
            },
            onDeletePlayerClicked = { dialogState.targetDeletePlayer = it }
        )
    }

    if (dialogState.targetFabledPlayer != null) {
        EditFabledDialog(
            player = dialogState.targetFabledPlayer!!,
            onDismissRequest = { dialogState.targetFabledPlayer = null },
            onShowRolePicker = { dialogState.isPickRoleDialogRaised = true },
            onShowCardClicked = { role -> onCard(role.ability, listOf(role)) },
            onChooseTokenClicked = {
                dialogState.targetTokenPlayer = it
                dialogState.targetFabledPlayer = null
            }
        )
    }

    if (dialogState.targetEditPlayer != null && dialogState.isPickRoleDialogRaised) {
        RolePickerDialog(
            availableRoles = availableRoles,
            sceneryRoles = state.scenery?.roles,
            onSelectRole = { role ->
                onChangeRole(dialogState.targetEditPlayer!!, role)
                dialogState.targetEditPlayer = null
                dialogState.isPickRoleDialogRaised = false
            },
            onDismiss = {
                dialogState.targetEditPlayer = null
                dialogState.isPickRoleDialogRaised = false
            }
        )
    }

    if (dialogState.targetFabledPlayer != null && dialogState.isPickRoleDialogRaised) {
        RolePickerDialog(
            changeFabled = true,
            onSelectRole = { role ->
                onChangeRole(dialogState.targetFabledPlayer!!, role)
                dialogState.targetFabledPlayer = null
                dialogState.isPickRoleDialogRaised = false
            },
            onDismiss = {
                dialogState.targetFabledPlayer = null
                dialogState.isPickRoleDialogRaised = false
            }
        )
    }

    if (dialogState.isEditDialogRaised) {
        EditGameDialog(
            onConfirm = onEditGame,
            onDismiss = { dialogState.isEditDialogRaised = false }
        )
    }

    if (dialogState.isMemoDialogRaised) {
        EditNotesDialog(
            onDismiss = { dialogState.isMemoDialogRaised = false },
            notes = state.notes,
            onNotesChange = onEditNotes
        )
    }

    if (dialogState.isAddPlayerDialogRaised) {
        AddPlayerDialog(
            onYesClicked = {
                onAddPlayer()
                dialogState.isAddPlayerDialogRaised = false
            },
            onDismiss = { dialogState.isAddPlayerDialogRaised = false }
        )
    }

    if (dialogState.targetDeletePlayer != null) {
        DeletePlayerDialog(
            player = dialogState.targetDeletePlayer,
            onYesClicked = {
                onDeletePlayer(it)
                dialogState.targetDeletePlayer = null
                dialogState.targetEditPlayer = null
            },
            onDismiss = { dialogState.targetDeletePlayer = null }
        )
    }

    if (dialogState.targetTokenPlayer != null) {
        TokenPickerDialog(
            target = dialogState.targetTokenPlayer,
            chosenRoles = state.chosenRoles,
            players = state.players,
            fabled = state.fabled,
            onPickToken = { token ->
                dialogState.targetTokenPlayer?.let {
                    onAddToken(dialogState.targetTokenPlayer!!, token)
                }
                dialogState.targetTokenPlayer = null
            },
            onDismiss = { dialogState.targetTokenPlayer = null }
        )
    }

    if (dialogState.isShareDialogRaised) {
        ShareDialog(
            titleRes = state.scenery?.sceneryNameRes,
            qrBitmap = qrBitmap,
            onUrlClicked = {
                val baseUrl = context.getString(R.string.url_wiki)
                val sceneryName = state.scenery?.let { context.getString(state.scenery.sceneryNameRes) }
                    ?.replace(" ", "_")
                val intent = Intent(Intent.ACTION_VIEW, "${baseUrl}$sceneryName".toUri())
                context.startActivity(intent)
                dialogState.isShareDialogRaised = false
            },
            onDismiss = { dialogState.isShareDialogRaised = false }
        )
    }
}