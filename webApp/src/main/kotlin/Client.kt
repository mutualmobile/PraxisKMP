import com.baseio.kmm.di.IosComponent
import com.baseio.kmm.di.initIosDependencies
import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

val sharedComponent = IosComponent()
fun main() {
    window.onload = {
        initIosDependencies()
        val rootDiv = document.getElementById("root")
        render(rootDiv!!) {
            child(TrendingUI)
        }
    }
}
