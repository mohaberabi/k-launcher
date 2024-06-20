package compose

import UrlLauncher
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun LauncherBody(
    modifier: Modifier = Modifier,
    onLaunch: (String) -> Unit,
    label: String,
    hint: String = "",
    keyBoardType: KeyboardType = KeyboardType.Text,
    buttonLabel: String = "",
) {

    var value by remember { mutableStateOf("") }


    Column(modifier = modifier) {
        AppTextField(
            label = label,
            keyboardType = keyBoardType,
            hint = hint,
            onChanged = { value = it },
            value = value,
        )
        AppButton(
            enabled = value.isNotEmpty(),
            onClick = { onLaunch(value) },
            label = buttonLabel,
        )
    }
}