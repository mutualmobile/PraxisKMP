//
//  macOSApp.swift
//  macOS
//
//  Created by Anmol Verma on 27/12/21.
//

import SwiftUI
import shared

let sharedComponent = IosComponent()

@main
struct macOSApp: App {
    init() {
        KoinKt.doInitIosDependencies()
    }
    
    var body: some Scene {
        WindowGroup {
            GithubTrendingScreen()
        }
    }
}
