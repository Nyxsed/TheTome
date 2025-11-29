package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.ItemType
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.presentation.components.CircleItem

@Composable
fun EditPlayerDialog(
    player: Player,
    playerCount: Int,
    onDismissRequest: () -> Unit,
    onChangeName: (Player, String) -> Unit,
    onChangeAliveStatus: (Player) -> Unit,
    onChangeGhostVote: (Player) -> Unit,
    onShowRolePicker: (Player) -> Unit,
    onShowCardClicked: (Role) -> Unit,
    onChooseTokenClicked: (Player) -> Unit,
    onDeletePlayerClicked: (Player) -> Unit,
) {
    var newName by remember { mutableStateOf(player.name ?: "") }

    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            val role = player.role
            val roleName = if (role != null) stringResource(role.roleName) else null
            val roleAbility = if (role != null) stringResource(role.ability) else null
            val killLabel =
                if (player.isAlive) stringResource(R.string.text_kill_player) else stringResource(R.string.text_revive_player)
            val ghostVoteLabel =
                if (player.haveGhostVote) stringResource(R.string.text_spend_ghost_vote) else stringResource(R.string.text_return_ghost_vote)

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (!roleName.isNullOrEmpty() && !roleAbility.isNullOrEmpty()) {
                    CircleItem(
                        size = 80.dp,
                        itemType = ItemType.PLAYER_CIRCLE,
                        bottomText = roleName,
                        centerIcon = role?.iconRes
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(roleAbility)
                }
                Spacer(Modifier.height(8.dp))
                TextField(
                    value = newName,
                    onValueChange = { newName = it },
                    label = { Text(stringResource(R.string.text_name)) }
                )
                Spacer(Modifier.height(8.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        onChangeName(player, newName)
                    }
                ) {
                    Text(stringResource(R.string.text_rename))
                }

                Spacer(Modifier.height(8.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { onShowRolePicker(player) }
                ) {
                    Text(stringResource(R.string.text_change_role))
                }

                if (role != null) {
                    Spacer(Modifier.height(8.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            onChangeAliveStatus(player)
                        }
                    ) {
                        Text(killLabel)
                    }


                    if (!player.isAlive) {
                        Spacer(Modifier.height(8.dp))
                        Button(
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = { onChangeGhostVote(player) }
                        ) {
                            Text(ghostVoteLabel)
                        }
                    }

                    Spacer(Modifier.height(8.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { onShowCardClicked(player.role) }
                    ) {
                        Text(stringResource(R.string.text_show_card))
                    }

                    Spacer(Modifier.height(8.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { onChooseTokenClicked(player) }
                    ) {
                        Text(stringResource(R.string.text_choose_token))
                    }

                }
                if (playerCount > 5) {
                    Spacer(Modifier.height(8.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { onDeletePlayerClicked(player) }
                    ) {
                        Text(stringResource(R.string.text_delete_player))
                    }
                }
            }
        }
    }
}
