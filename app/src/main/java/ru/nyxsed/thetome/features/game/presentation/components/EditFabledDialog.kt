package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun EditFabledDialog(
    player: Player,
    onDismissRequest: () -> Unit,
    onShowRolePicker: (Player) -> Unit,
    onShowCardClicked: (Role) -> Unit,
) {
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
                        onClick = { onShowCardClicked(player.role) }
                    ) {
                        Text(stringResource(R.string.text_show_card))
                    }
                }
            }
        }
    }
}
