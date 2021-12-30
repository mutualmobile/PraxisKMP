//
//  macOSApp.swift
//  macOS
//
//  Created by Anmol Verma on 27/12/21.
//

import SwiftUI
import shared

let sharedComponent = SharedComponent()
let useCasesComponent = UseCasesComponent()

@main
struct macOSApp: App {
    init() {
        KoinKt.doInitSharedDependencies()
    }
    
    var body: some Scene {
        WindowGroup {
            GithubTrendingScreen()
        }
    }
}
