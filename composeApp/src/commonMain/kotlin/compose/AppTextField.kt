package compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    label: String, onChanged: (String) -> Unit,
    onClick: (() -> Unit)? = null,
    value: String,
    hint: String = "",
    keyboardType: KeyboardType = KeyboardType.Text
) {

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        value = value,
        label = { Text(label) },
        trailingIcon = {
            onClick?.let {
                IconButton(
                    onClick = it,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = ""
                    )
                }
            }

        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        placeholder = { Text(hint) },
        shape = RoundedCornerShape(8.dp),
        onValueChange = onChanged
    )
}