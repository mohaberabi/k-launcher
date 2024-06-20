import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import compose.EmailBody
import compose.LauncherBody
import compose.SmsBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import urllaunchercmp.composeapp.generated.resources.Res
import urllaunchercmp.composeapp.generated.resources.compose_multiplatform


enum class LaunchType {
    SMS,
    URL,
    PHONE,
    EMAIL,

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(
    urlLauncher: UrlLauncher,
) {


    var type by remember { mutableStateOf(LaunchType.URL) }
    var expandDropDown by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val hostState = remember {
        SnackbarHostState()
    }
    MaterialTheme {

        Scaffold(

            topBar = {
                TopAppBar(

                    title = { Text("Compose multiplatform url launcher ") },
                    actions = {
                        DropdownMenu(
                            expanded = expandDropDown,
                            onDismissRequest = { expandDropDown = false }
                        ) {

                            LaunchType.entries.forEach { item ->
                                DropdownMenuItem(
                                    onClick = {
                                        type = item
                                        expandDropDown = false
                                    },
                                    enabled = true,
                                    text = {
                                        Text(item.name)
                                    }
                                )
                            }
                        }

                    }
                )

            },
            snackbarHost = {
                SnackbarHost(hostState)
            }
        ) { padding ->


            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(12.dp)
                    .scrollable(
                        rememberScrollState(),
                        Orientation.Vertical
                    )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        type.name, modifier = Modifier
                            .weight(0.8f)
                    )
                    IconButton(onClick = {
                        expandDropDown = true
                    }) {
                        Icon(
                            Icons.Default.Menu, null,
                        )
                    }
                }
                when (type) {
                    LaunchType.SMS -> SmsBody(
                        onLaunch = { data ->
                            urlLauncher.launchSms(
                                message = data.message,
                                phone = data.phone,
                                onError = {
                                    showError(
                                        scope = scope,
                                        hostState = hostState,
                                        error = it
                                    )
                                }
                            )

                        },
                    )

                    LaunchType.URL -> LauncherBody(
                        label = "Launch Url",
                        onLaunch = {
                            urlLauncher.launchUrl(
                                url = it,
                                onError = { error ->
                                    showError(
                                        scope = scope,
                                        hostState = hostState,
                                        error = error
                                    )
                                },
                            )
                        },
                        hint = "https:google.com",
                        buttonLabel = "Launch URL"
                    )

                    LaunchType.PHONE -> LauncherBody(
                        label = "Phone Number",

                        onLaunch = {
                            urlLauncher.launchPhone(
                                phone = it,
                                onError = { error ->
                                    showError(
                                        scope = scope,
                                        hostState = hostState,
                                        error = error
                                    )
                                },
                            )
                        },
                        hint = "010000000",
                        buttonLabel = "Call Phone "
                    )

                    LaunchType.EMAIL -> EmailBody(
                        onLaunch = { data ->
                            urlLauncher.launchEmail(
                                email = data.email,
                                subject = data.subject,
                                body = data.body,
                                onError = {
                                    showError(
                                        scope = scope,
                                        hostState = hostState,
                                        error = it
                                    )
                                }
                            )

                        },
                    )
                }


            }
        }

    }
}

fun showError(
    scope: CoroutineScope,
    error: Throwable,
    hostState: SnackbarHostState
) {
    scope.launch {
        hostState.showSnackbar(error.message ?: "Unknown Error")
    }
}

