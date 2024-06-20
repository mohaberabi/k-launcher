import android.content.Context
import android.content.Intent
import android.net.Uri

actual class UrlLauncher(
    private val context: Context,
) {


    actual fun launchUrl(
        url: String,
        onError: (Throwable) -> Unit,
    ) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: Throwable) {
            onError(e)
        }


    }

    actual fun launchSms(phone: String, message: String?, onError: (Throwable) -> Unit) {
        try {
            val uri = Uri.parse("smsto:$phone")
            val intent = Intent(Intent.ACTION_SENDTO, uri).apply {
                message?.let { putExtra("sms_body", it) }
            }
            context.startActivity(intent)
        } catch (e: Throwable) {
            onError(e)
        }
    }


    actual fun launchEmail(
        email: String,
        subject: String?,
        body: String?,
        onError: (Throwable) -> Unit,
    ) {
        try {
            val uri = Uri.parse("mailto:$email").buildUpon()
            subject?.let { uri.appendQueryParameter("subject", it) }
            body?.let { uri.appendQueryParameter("body", it) }
            val intent = Intent(Intent.ACTION_SENDTO, uri.build())
            context.startActivity(intent)
        } catch (e: Throwable) {
            onError(e)
        }

    }

    actual fun launchPhone(
        phone: String,
        onError: (Throwable) -> Unit,
    ) {
        try {
            val intent = Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:$phone")
            )
            context.startActivity(intent)
        } catch (e: Throwable) {
            onError(e)
        }
    }

}