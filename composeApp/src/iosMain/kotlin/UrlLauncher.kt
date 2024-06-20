import kotlinx.cinterop.BetaInteropApi
import platform.Foundation.NSCharacterSet
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.URLQueryAllowedCharacterSet
import platform.Foundation.create
import platform.Foundation.stringByAddingPercentEncodingWithAllowedCharacters
import platform.UIKit.UIApplication

actual class UrlLauncher {

    actual fun launchUrl(
        url: String,
        onError: (Throwable) -> Unit,
    ) {
        try {
            val nsUrl = NSURL.URLWithString(url)
            nsUrl?.let {
                UIApplication.sharedApplication().openURL(it)
            } ?: onError(Exception("Could not launch the url NSURL:Null"))
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
            val url = StringBuilder("mailto:$email")
            val queryParameters = mutableListOf<String>()
            subject?.let { queryParameters.add("subject=${it.encodeURL()}") }
            body?.let { queryParameters.add("body=${it.encodeURL()}") }
            if (queryParameters.isNotEmpty()) {
                url.append("?").append(queryParameters.joinToString("&"))
            }
            val nsUrl = NSURL.URLWithString(url.toString())
            nsUrl?.let {
                UIApplication.sharedApplication.openURL(it)
            } ?: onError(Exception("Could not launch email "))

        } catch (e: Throwable) {
            onError(e)
        }


    }

    actual fun launchPhone(
        phone: String,
        onError: (Throwable) -> Unit,
    ) {
        try {
            val nsUrl = NSURL.URLWithString("tel://$phone")
            nsUrl?.let {
                UIApplication.sharedApplication().openURL(it)
            } ?: onError(Exception("Could not launch the phone NSURL:Null"))
        } catch (e: Throwable) {
            onError(e)
        }
    }

    actual fun launchSms(
        phone: String,
        message: String?,
        onError: (Throwable) -> Unit,
    ) {
        try {
            val url = StringBuilder("sms:$phone")
            message?.let {
                url.append("?body=${it.encodeURL()}")
            }
            val nsUrl = NSURL.URLWithString(url.toString())
            nsUrl?.let {
                UIApplication.sharedApplication().openURL(it)
            } ?: onError(Exception("Could not launch the SMS NSURL:Null"))
        } catch (e: Throwable) {
            onError(e)
        }
    }

    @OptIn(BetaInteropApi::class)
    private fun String.encodeURL(): String {
        return NSString.create(string = this)
            .stringByAddingPercentEncodingWithAllowedCharacters(NSCharacterSet.URLQueryAllowedCharacterSet)
            ?: this
    }

}