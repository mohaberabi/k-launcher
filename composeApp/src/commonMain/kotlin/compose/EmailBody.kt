package compose

import UrlLauncher
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


data class EmailData(
    val email: String,
    val subject: String? = null,
    val body: String? = null,
)

@Composable
fun EmailBody(
    modifier: Modifier = Modifier,
    onLaunch: (EmailData) -> Unit
) {

    var email by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf<String?>(null) }
    var body by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier.scrollable(
            rememberScrollState(), Orientation.Vertical,
        )
    ) {


        AppTextField(
            label = "Email",
            onChanged = { email = it },
            hint = "i.love@kotlin.com",
            value = email,
            keyboardType = KeyboardType.Email
        )
        AppTextField(
            label = "Subject",
            onChanged = { subject = it },
            value = subject ?: "",
            keyboardType = KeyboardType.Email
        )
        AppTextField(
            label = "Body",
            onChanged = { body = it },
            value = body ?: "",
            keyboardType = KeyboardType.Email
        )
        AppButton(
            enabled = email.isNotEmpty(),
            onClick = {
                onLaunch(EmailData(email = email, subject = subject, body = body))
            },
            label = "Send Email",
        )
    }
}