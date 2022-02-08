import com.baseio.kmm.db.DriverFactory
import com.baseio.kmm.di.SharedComponent
import com.baseio.kmm.di.UseCasesComponent
import com.baseio.kmm.di.initSqlDelightExperimentalDependencies
import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

val sharedComponent = SharedComponent()
val useCasesComponent = UseCasesComponent()

fun main() {
  initSqlDelightExperimentalDependencies()
  window.onload = { _ ->
    val rootDiv = document.getElementById("root")
    render(rootDiv!!) {
      child(TrendingUI)
    }
  }
}

suspend fun setupDriver() {
  sharedComponent.provideGithubTrendingLocal().driver?.let {} ?: run {
    setupDriverInternal()
  }

}

private suspend fun setupDriverInternal() {
  try {
    val driver = DriverFactory().createDriverBlocking()
    val trendingLocal = sharedComponent.provideGithubTrendingLocal()
    trendingLocal.driver = driver
  } catch (ex: Exception) {
    console.log(ex.message)
    console.log("Exception happened here.")
    console.log(ex)
  }
}
