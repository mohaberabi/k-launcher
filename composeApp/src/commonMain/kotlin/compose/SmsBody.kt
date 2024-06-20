package compose

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

data class SmsData(
    val phone: String,
    val message: String? = null
)

@Composable
fun SmsBody(
    modifier: Modifier = Modifier,
    onLaunch: (SmsData) -> Unit
) {

    var phone by remember { mutableStateOf("") }
    var sms by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier.scrollable(
            rememberScrollState(), Orientation.Vertical,
        )
    ) {


        AppTextField(
            label = "Phone",
            onChanged = { phone = it },
            hint = "011111",
            value = phone,
            keyboardType = KeyboardType.Phone
        )
        AppTextField(
            label = "Message",
            onChanged = { sms = it },
            hint = "I love kotlin let's write some kotlin code ",
            value = sms ?: "",
            keyboardType = KeyboardType.Text
        )

        AppButton(
            enabled = phone.isNotEmpty(),
            onClick = {
                onLaunch(SmsData(message = sms, phone = phone))
            },
            label = "Send SMS",
        )
    }
}