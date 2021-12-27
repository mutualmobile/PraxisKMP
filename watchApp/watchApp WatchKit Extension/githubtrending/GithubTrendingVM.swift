//
//  GithubTrendingVM.swift
//  iosApp
//
//  Created by Anmol Verma on 27/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared
import Combine
import KMPNativeCoroutinesCombine

class GithubTrendingVM : ObservableObject{
    
    @Published var repos : [UIRepo] = []
    
    private var trendingRepos = sharedComponent.provideGithubTrendingAPI()
    private var anyCancellable: AnyCancellable? = nil
    
    
    func fetchTrendingRepos(){
        anyCancellable?.cancel()
        anyCancellable = createPublisher(for: trendingRepos.getTrendingReposNative(query: "kotlin")).receive(on: DispatchQueue.main).sink(receiveCompletion: { completion in
            debugPrint(completion)
        }, receiveValue: { githubRepos in
            self.repos = githubRepos.map({ item in
                return UIRepo(author:item.author, name:item.name, avatar:item.avatar, url:item.url)
            })
        })
    }
    
    
}
