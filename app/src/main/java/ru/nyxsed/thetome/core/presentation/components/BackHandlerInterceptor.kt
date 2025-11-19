package ru.nyxsed.thetome.core.presentation.components

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import ru.nyxsed.thetome.R

@Composable
fun BackHandlerInterceptor(
    onDoublePressed: () -> Unit,
) {
    val context = LocalContext.current
    var lastBackPressed by remember { mutableStateOf(0L) }
    val toastText = stringResource(R.string.text_press_again)
    val toast = remember { Toast.makeText(context, toastText, Toast.LENGTH_SHORT) }

    BackHandler(enabled = true) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastBackPressed < 2000) {
            toast.cancel()
            onDoublePressed()
        } else {
            lastBackPressed = currentTime
            toast.show()
        }
    }
}