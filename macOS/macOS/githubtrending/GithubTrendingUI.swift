import SwiftUI
import shared


struct GithubTrendingScreen: View {
    
    @ObservedObject var ghVM = GithubTrendingVM()

	var body: some View {
        NavigationView{
            List{
                ForEach(ghVM.repos){ repo in
                    RepoItem(repo:repo)
                }
            }.onAppear {
                ghVM.fetchTrendingRepos()
            }.navigationTitle("Trending Kotlin Repos")
        }
	}
}

struct RepoItem : View{
    
    let repo : UIRepo
    @Environment(\.openURL) var openURL

    var body: some View{
        HStack{
     
            
            VStack{
                Text(repo.name ?? "NA").fontWeight(.bold).frame(maxWidth:.infinity,alignment: Alignment.topLeading)
                Text(repo.author ?? "NA").frame(maxWidth:.infinity,alignment: Alignment.topLeading)
                Button {
                    openURL(URL(string: repo.url ?? "no url")!)
                } label: {
                    Text(repo.url ?? "no url").frame(maxWidth:.infinity,alignment: Alignment.topLeading)
                }

            }
        }
    }
}
