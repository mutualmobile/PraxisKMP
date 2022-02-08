import com.baseio.kmm.domain.model.GithubReposItem
import com.baseio.kmm.features.trending.GithubTrendingDataModel
import kotlinx.coroutines.*
import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.display
import kotlinx.css.flexDirection
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.*
import styled.css
import styled.styledDiv

external interface TrendingProps : Props

val TrendingUI = fc<TrendingProps> {
  var trendingRepos: List<GithubReposItem> by useState(emptyList())
  var message: String by useState("")
  var state by useState<GithubTrendingDataModel.DataState>()
  var search by useState("")

  val dataModel = GithubTrendingDataModel(onDataState = { stateNew ->
    state = stateNew
    when (stateNew) {
      is GithubTrendingDataModel.LoadingState -> {
        message = "Loading..."
      }
      is GithubTrendingDataModel.SuccessState -> {
        trendingRepos = stateNew.trendingList
        message = "Found repos..."
      }
      GithubTrendingDataModel.Complete -> {
        message = "Completed loading!"
      }
      GithubTrendingDataModel.EmptyState -> {
        message = "Emty state"
      }
      is GithubTrendingDataModel.ErrorState -> {
        message = stateNew.throwable.message ?: "Error"
      }
    }
  })

  useEffectOnce {
    MainScope().launch {
      setupDriver()
      message = "Driver created";
      dataModel.activate()
      message = "DataModel activated";
    }
  }

  h1 {
    +"Trending Kotlin Repositories"
  }
  h1 {
    +"Status :"
    +message
  }

  styledDiv{
    css {
      display = Display.flex
      flexDirection = FlexDirection.column
    }

    div{
      input {
        attrs{
          placeholder = "Search by language..."
          value = search
          onChangeFunction = {
            val target = it.target as HTMLInputElement
            search = target.value
          }
        }
      }

      button {
        +"Search Now"

        attrs {
          onClick = {
            dataModel.filterRecords(search)
          }
        }
      }
    }


  }

  h1{

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
          p {
            +repo.url.toString()
          }
        }
      }

    }
  }

}

