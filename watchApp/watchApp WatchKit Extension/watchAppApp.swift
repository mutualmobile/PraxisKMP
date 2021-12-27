//
//  watchAppApp.swift
//  watchApp WatchKit Extension
//
//  Created by Anmol Verma on 27/12/21.
//

import SwiftUI
import shared

let sharedComponent = IosComponent()

@main
struct watchAppApp: App {
    init() {
        KoinKt.doInitIosDependencies()
    }
    
    @SceneBuilder var body: some Scene {
        WindowGroup {
            NavigationView {
                GithubTrendingScreen()
            }
        }
        

        WKNotificationScene(controller: NotificationController.self, category: "myCategory")
    }
}
