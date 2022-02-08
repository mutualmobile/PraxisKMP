import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.baseio.kmm.di.initSharedDependencies

@Composable
@Preview
fun App() {
    MaterialTheme {
        TrendingReposListScreen(TrendingReposVM())
    }
}

fun main() = application {
    initSharedDependencies()
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
