import com.baseio.kmm.db.DriverFactory
import com.baseio.kmm.di.SharedComponent
import com.baseio.kmm.di.UseCasesComponent
import com.baseio.kmm.di.initJSDependencies
import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

val sharedComponent = SharedComponent()
val useCasesComponent = UseCasesComponent()

fun main() {
  initJSDependencies()
  window.onload = { _ ->
    MainScope().launch {
      setupDriver()
    }
    val rootDiv = document.getElementById("root")
    render(rootDiv!!) {
      child(TrendingUI)
    }
  }
}

suspend fun setupDriver() {
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
