package ru.nyxsed.thetome.features.card.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.ItemType
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.presentation.components.BackHandlerInterceptor
import ru.nyxsed.thetome.core.presentation.components.CircleItem
import ru.nyxsed.thetome.core.presentation.components.GameScreenBackground
import ru.nyxsed.thetome.ui.theme.DarkPurple

@Composable
fun CardScreen(
    stringResourceId: Int,
    roles: List<Role?>?,
    haveButton: Boolean,
    onDoublePressBack: () -> Unit,
) {
    BackHandlerInterceptor(
        onDoublePressed = onDoublePressBack
    )

    CardContent(
        stringResourceId = stringResourceId,
        roles = roles,
        haveButton = haveButton,
        onButtonClicked = onDoublePressBack,
    )
}

@Composable
fun CardContent(
    stringResourceId: Int,
    roles: List<Role?>?,
    haveButton: Boolean,
    onButtonClicked: () -> Unit,
) {
    GameScreenBackground(null)
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (stringResourceId != 0) {
                val text = stringResource(stringResourceId)
                val isLong = text.length > 50
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = text,
                    softWrap = true,
                    fontSize = if (isLong) 32.sp else 50.sp,
                    lineHeight = if (isLong) 40.sp else 60.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            roles?.forEach { role ->
                val title = role?.roleName?.let { stringResource(it) } ?: ""
                CircleItem(
                    itemType = ItemType.PLAYER_CIRCLE,
                    modifier = Modifier.padding(8.dp),
                    centerIcon = role?.iconRes,
                    bottomText = title,
                    size = 150.dp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (haveButton) {
                Button(
                    modifier = Modifier
                        .padding(horizontal = 32.dp, vertical = 48.dp)
                        .fillMaxWidth()
                        .height(60.dp),
                    onClick = onButtonClicked,
                    colors = ButtonDefaults.buttonColors().copy(containerColor = DarkPurple)
                ) {
                    Text(
                        text = stringResource(R.string.text_ok),
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}