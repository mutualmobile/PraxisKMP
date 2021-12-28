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
    
    private var fetchUseCase = useCasesComponent.provideFetchTrendingReposUseCase()
    private var saveUseCase = useCasesComponent.provideSaveTrendingReposUseCase()
    private var readUseCase = useCasesComponent.provideGetLocalReposUseCase()
    
    private var anyCancellable: AnyCancellable? = nil
    private var localReadCancellable:AnyCancellable? = nil
    
    init() {
        readLocalTrendingRepos()
    }
    
    
    func fetchTrendingRepos(){
        anyCancellable?.cancel()
        anyCancellable = createPublisher(for: fetchUseCase.performNative(input: "kotlin")).receive(on: DispatchQueue.main).sink(receiveCompletion: { completion in
            debugPrint(completion)
        }, receiveValue: { githubRepos in
            self.saveTrendingRepos(githubRepos:githubRepos as! [GithubReposItem])
        })
    }
    
    func readLocalTrendingRepos(){
        localReadCancellable?.cancel()
        localReadCancellable = createPublisher(for: sharedComponent.provideGithubTrendingLocal().getAllNative()).receive(on: DispatchQueue.main).sink(receiveCompletion: { completion in
            debugPrint(completion)
        }, receiveValue: { [weak self] value in
            self?.repos = value.map({ item in
                return UIRepo(author:item.author, name:item.name, avatar:item.avatar, url:item.url)
            })
        })
        
    }
    
    func saveTrendingRepos(githubRepos:[GithubReposItem]){
        anyCancellable?.cancel()
        anyCancellable = createPublisher(for: saveUseCase.performNative(input: githubRepos)).receive(on: DispatchQueue.main).sink(receiveCompletion: { completion in
            debugPrint(completion)
        }, receiveValue: { githubRepos in
            
        })
    }
    
    
    
    
}
