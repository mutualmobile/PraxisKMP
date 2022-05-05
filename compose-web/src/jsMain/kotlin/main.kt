import com.baseio.kmm.di.initSqlDelightExperimentalDependencies
import org.jetbrains.compose.web.renderComposable

// invoke using `./gradlew :compose-web:jsBrowserDevelopmentRun`
fun main() {
    initSqlDelightExperimentalDependencies()
    renderComposable(rootElementId = "root") {
        TrendingReposListScreen(TrendingReposVM())
    }
}
