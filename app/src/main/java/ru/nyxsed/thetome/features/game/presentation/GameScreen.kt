package ru.nyxsed.thetome.features.game.presentation

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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

    BackHandlerInterceptor(
        onDoublePressed = onDoublePressBack
    )
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
        context = context,
        onEditGame = onEditGameClicked,
        onCard = onCardClicked,
        onEditNotes = {
            viewModel.editNotes(it)
        },
        onChangeAliveStatus = {
            viewModel.changeAliveStatus(it)
        },
        onChangeGhostVoteStatus = {
            viewModel.onChangeGhostVoteStatus(it)
        },
        onRenamePlayer = { player, name ->
            viewModel.renamePlayer(player, name)
        },
        onChangeRole = { player, role ->
            viewModel.changeRole(player, role)
        },
        onChangeBluffs = {
            viewModel.changeDemonBluffs(it)
        },
        onMoveToPreviousAction = {
            viewModel.moveToPreviousAction()
        },
        onMoveToNextAction = {
            viewModel.moveToNextAction()
        },
        onAddToken = { player, token ->
            viewModel.addToken(player, token)
        },
        onDeleteToken = { player, tokenIndex ->
            viewModel.deleteToken(player, tokenIndex)
        },
        onAddPlayer = {
            viewModel.addPlayer()
        },
        onDeletePlayer = {
            viewModel.deletePlayer(it)
        },
        onReorderPlayers = {
            viewModel.updatePlayers(it)
        }
    )
}

@Composable
fun GameContent(
    state: GameState,
    qrBitmap: Bitmap?,
    context: Context,
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
) {
    var isEditDialogRaised by remember { mutableStateOf(false) }
    var isMemoDialogRaised by remember { mutableStateOf(false) }
    var isPickRoleDialogRaised by remember { mutableStateOf(false) }
    var isShareDialogRaised by remember { mutableStateOf(false) }
    var isAddPlayerDialogRaised by remember { mutableStateOf(false) }

    var isFabledEnabled by remember { mutableStateOf(false) }

    var targetDeletePlayer by remember { mutableStateOf<Player?>(null) }
    var targetEditPlayer by remember { mutableStateOf<Player?>(null) }
    var targetFabledPlayer by remember { mutableStateOf<Player?>(null) }
    var targetTokenPlayer by remember { mutableStateOf<Player?>(null) }

    GameScreenBackground(state.currentPhase)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                WindowInsets.safeDrawing
                    .only(WindowInsetsSides.Top)
                    .asPaddingValues()
            )
    ) {
        TopButtonsRow(
            sceneryRoles = state.scenery?.roles,
            fabledEnabled = isFabledEnabled,
            onFabledClicked = {
                isFabledEnabled = !isFabledEnabled
            },
            onEditClicked = {
                isEditDialogRaised = true
            },
            onMemoClicked = {
                isMemoDialogRaised = true
            },
            onShareClicked = {
                isShareDialogRaised = true
            },
            onAddPlayerClicked = {
                isAddPlayerDialogRaised = true
            },
            menuItems = listOf(
                CardsMenuItem(stringResource(R.string.menu_demon)) {
                    onCard(R.string.menu_demon, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_minions)) {
                    onCard(R.string.menu_minions, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_not_in_play)) {
                    onCard(R.string.menu_not_in_play, state.demonBluffs)
                },
                CardsMenuItem(stringResource(R.string.menu_use_ability)) {
                    onCard(R.string.menu_use_ability, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_make_choice)) {
                    onCard(R.string.menu_make_choice, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_nominated_today)) {
                    onCard(R.string.menu_nominated_today, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_voted_today)) {
                    onCard(R.string.menu_voted_today, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_char_selected_you), showPickerDialog = true) { role ->
                    onCard(R.string.menu_char_selected_you, listOf(role))
                },
                CardsMenuItem(stringResource(R.string.menu_show_role), showPickerDialog = true) { role ->
                    onCard(role?.ability!!, listOf(role))
                },
            )
        )

        Spacer(Modifier.height(20.dp))
        if (!state.players.isNullOrEmpty()) {
            PlayersWheel(
                players = state.players,
                fabledEnabled = isFabledEnabled,
                fabled = state.fabled,
                onClick = { player ->
                    targetEditPlayer = player
                },
                onFabledClick = { fabled ->
                    targetFabledPlayer = fabled
                },
                onTokenLongClick = { player, tokenIndex ->
                    onDeleteToken(player, tokenIndex)
                },
                onOrderChanged = {
                    onReorderPlayers(it)
                },
            )
        }

        Spacer(Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            if (state.players?.size!! > 6) {
                DemonBluff(
                    demonBluffRoles = state.demonBluffs,
                    availableRoles = state.availableBluffRoles,
                    onChangeBluffs = {
                        onChangeBluffs(it)
                    },
                )
            }
            KillParticipation(
                roleDistribution = state.roleDistribution,
                players = state.players,
                dayNumber = state.currentDay,
            )
        }

        Spacer(Modifier.height(8.dp))
        Reminder(
            modifier = Modifier.weight(1f),
            action = state.currentAction,
            onBeforeClicked = { onMoveToPreviousAction() },
            onAfterClicked = { onMoveToNextAction() },
        )

        if (targetEditPlayer != null && !isPickRoleDialogRaised) {
            EditPlayerDialog(
                player = targetEditPlayer!!,
                playerCount = state.players?.size ?: 0,
                onDismissRequest = {
                    targetEditPlayer = null
                },
                onChangeName = { player, name ->
                    onRenamePlayer(player, name)
                    targetEditPlayer = null
                },
                onChangeAliveStatus = {
                    onChangeAliveStatus(it)
                    targetEditPlayer = null
                },
                onChangeGhostVote = {
                    onChangeGhostVoteStatus(it)
                    targetEditPlayer = null
                },
                onShowRolePicker = {
                    isPickRoleDialogRaised = true
                },
                onShowCardClicked = { role ->
                    onCard(role.ability, listOf(role))
                },
                onChooseTokenClicked = {
                    targetTokenPlayer = it
                    targetEditPlayer = null
                },
                onDeletePlayerClicked = {
                    targetDeletePlayer = it
                }
            )
        }

        if (targetFabledPlayer != null) {
            EditFabledDialog(
                player = targetFabledPlayer!!,
                onDismissRequest = {
                    targetFabledPlayer = null
                },
                onShowRolePicker = {
                    isPickRoleDialogRaised = true
                },
                onShowCardClicked = { role ->
                    onCard(role.ability, listOf(role))
                },
                onChooseTokenClicked = {
                    targetTokenPlayer = it
                    targetFabledPlayer = null
                }
            )
        }

        val takenRoles = state.players?.mapNotNull { it.role } ?: emptyList()
        val availableRoles = state.chosenRoles?.filter { role ->
            role !in takenRoles || targetEditPlayer?.role == role
        } ?: emptyList()

        if (targetEditPlayer != null && isPickRoleDialogRaised) {
            RolePickerDialog(
                availableRoles = availableRoles,
                sceneryRoles = state.scenery?.roles,
                onSelectRole = { role ->
                    onChangeRole(targetEditPlayer!!, role)
                    targetEditPlayer = null
                    isPickRoleDialogRaised = false
                },
                onDismiss = {
                    targetEditPlayer = null
                    isPickRoleDialogRaised = false
                }
            )
        }

        if (targetFabledPlayer != null && isPickRoleDialogRaised) {
            RolePickerDialog(
                changeFabled = true,
                onSelectRole = { role ->
                    onChangeRole(targetFabledPlayer!!, role)
                    targetFabledPlayer = null
                    isPickRoleDialogRaised = false
                },
                onDismiss = {
                    targetFabledPlayer = null
                    isPickRoleDialogRaised = false
                }
            )
        }


        if (isEditDialogRaised) {
            EditGameDialog(
                onConfirm = {
                    onEditGame()
                },
                onDismiss = {
                    isEditDialogRaised = false
                }
            )
        }

        if (isMemoDialogRaised) {
            EditNotesDialog(
                onDismiss = {
                    isMemoDialogRaised = false
                },
                notes = state.notes,
                onNotesChange = { onEditNotes(it) }
            )
        }

        if (isAddPlayerDialogRaised) {
            AddPlayerDialog(
                onYesClicked = {
                    onAddPlayer()
                    isAddPlayerDialogRaised = false
                },
                onDismiss = {
                    isAddPlayerDialogRaised = false
                },
            )
        }

        if (targetDeletePlayer != null) {
            DeletePlayerDialog(
                player = targetDeletePlayer,
                onYesClicked = {
                    onDeletePlayer(it)
                    targetDeletePlayer = null
                    targetEditPlayer = null
                },
                onDismiss = {
                    targetDeletePlayer = null
                },
            )
        }

        if (targetTokenPlayer != null) {
            TokenPickerDialog(
                target = targetTokenPlayer,
                chosenRoles = state.chosenRoles,
                players = state.players,
                fabled = state.fabled,
                sceneryTokens = state.scenery?.sceneryTokens!!,
                onPickToken = { token ->
                    targetTokenPlayer?.let {
                        onAddToken(targetTokenPlayer!!, token)
                    }
                    targetTokenPlayer = null
                },
                onDismiss = { targetTokenPlayer = null }
            )
        }

        if (isShareDialogRaised) {
            ShareDialog(
                titleRes = state.scenery?.sceneryNameRes,
                qrBitmap = qrBitmap,
                onUrlClicked = {
                    val baseUrl = context.getString(R.string.url_wiki)
                    val sceneryName = state.scenery?.let { context.getString(state.scenery.sceneryNameRes) }?.replace(" ", "_")
                    val intent = Intent(Intent.ACTION_VIEW, "${baseUrl}$sceneryName".toUri())
                    context.startActivity(intent)
                    isShareDialogRaised = false
                },
                onDismiss = { isShareDialogRaised = false }
            )
        }
    }
}