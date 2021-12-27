import SwiftUI
import shared
import SDWebImageSwiftUI


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
            WebImage(url: URL(string:repo.avatar ?? ""), isAnimating: .constant(false))
                                   .resizable()
                                   .indicator(.activity)
                                   .transition(.fade(duration: 0.5))
                                   .scaledToFill()
                                   .frame(width: CGFloat(80), height: CGFloat(80), alignment: .center)
            
            
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
