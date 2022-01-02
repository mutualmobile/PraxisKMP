import com.baseio.kmm.domain.model.GithubReposItem
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import react.*
import react.dom.*

external interface TrendingProps : Props

val scope = MainScope()

val TrendingUI = fc<TrendingProps> {
  var trendingRepos: List<GithubReposItem> by useState(emptyList())
  var exception: String by useState("")
  useEffectOnce {
    scope.launch {
      withContext(Dispatchers.Default) {
        setupDriver()
        useCasesComponent.provideGetLocalReposUseCase().perform(null).collectLatest {
          withContext(Dispatchers.Main) {
            trendingRepos = it
          }
        }
      }
    }
  }

  useEffectOnce {
    scope.launch {
      withContext(Dispatchers.Default) {
        val trendingReposLocal =
          useCasesComponent.provideFetchTrendingReposUseCase().perform("kotlin")
        try {
          setupDriver()
          useCasesComponent.provideSaveTrendingReposUseCase().perform(trendingReposLocal)
        } catch (ex: Exception) {
          ex.printStackTrace()
          exception = ex.message ?: ""
        }
      }
    }
  }

  h1 {
    +"Trending Kotlin Repositories"
    +exception
  }
  if (exception.isNotEmpty()) {
    h1 {
      +"Error:"
      +exception
    }
  }

  div {
    for (repo in trendingRepos) {
      img(src = repo.avatar, classes = "img") {
        this.attrs.height = "100"
        this.attrs.width = "100"
      }
      div {
        p {
          key = repo.url
          +"${repo.name}: ${repo.author}"
        }
        a {
          attrs {
            href = repo.url.toString()
            target = repo.url.toString()
          }
          p{
            +repo.url.toString()
          }
        }
      }

    }
  }

}

