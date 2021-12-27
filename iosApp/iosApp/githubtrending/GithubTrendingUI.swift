import SwiftUI
import shared

struct GithubTrendingScreen: View {
    
    @ObservedObject var ghVM = GithubTrendingVM()
    
	var body: some View {
        NavigationView{
            List{
                ForEach(ghVM.repos){ repo in
                    VStack{
                        Text(repo.name ?? "NA").fontWeight(.bold).frame(maxWidth:.infinity,alignment: Alignment.topLeading)
                        Text(repo.author ?? "NA").frame(maxWidth:.infinity,alignment: Alignment.topLeading)
                        Link("Visit Github Repo", destination: URL(string: repo.url ?? "no url")!).frame(maxWidth:.infinity,alignment: Alignment.topLeading)

                    }
                 
                }
            }.onAppear {
                ghVM.fetchTrendingRepos()
            }.navigationTitle("Trending Kotlin Repos")
        }
	}
}
