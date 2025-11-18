package ru.nyxsed.thetome.features.card.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.presentation.components.CircleItem

@Composable
fun CardScreen(stringResourceId: Int, roles: List<Role?>?) {
    CardContent(stringResourceId = stringResourceId, roles = roles)
}

@Composable
fun CardContent(stringResourceId: Int, roles: List<Role?>?) {
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
                val title = role?.roleName?.let { stringResource(it) } ?: "—"
                CircleItem(
                    modifier = Modifier.padding(8.dp),
                    bottomText = title,
                    size = 200.dp,
                    backgroundColor = Color.DarkGray
                )
            }
        }
    }
}