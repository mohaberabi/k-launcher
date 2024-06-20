expect class UrlLauncher {

    fun launchUrl(
        url: String,
        onError: (Throwable) -> Unit,
    )

    fun launchEmail(
        email: String,
        subject: String? = null,
        body: String? = null,
        onError: (Throwable) -> Unit,
    )

    fun launchPhone(
        phone: String,
        onError: (Throwable) -> Unit,
    )

    fun launchSms(
        phone: String,
        message: String?,
        onError: (Throwable) -> Unit,
    )
}