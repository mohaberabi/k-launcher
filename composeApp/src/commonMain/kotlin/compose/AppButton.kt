package compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String = "",
    enabled: Boolean = true
) {


    Button(
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        onClick = onClick

    ) {
        Text(label)
    }
}

