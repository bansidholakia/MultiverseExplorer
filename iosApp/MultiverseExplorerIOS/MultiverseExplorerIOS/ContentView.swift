//
//  ContentView.swift
//  MultiverseExplorerIOS
//
//  Created by Bansi Dholakiya on 2026-10-07.
//

import SwiftUI
import Shared

struct ContentView: View {
    
        @State
        private var characterName =
            "Loading..."

        @State
        private var status =
            ""
    
    private let sdk =
            IosMultiverseSdk()
    
    var body: some View {
        VStack(
                    spacing: 16
                ) {

                    Text(
                        characterName
                    )
                    .font(
                        .title
                    )

                    Text(
                        status
                    )
                    .foregroundStyle(
                        .secondary
                    )
                }
                .padding()
                .onAppear {
                    loadCharacter()
                }
    }
    
    private func loadCharacter() {

            sdk.getCharacter(
                id: 1
            ) { character, error in

                DispatchQueue.main.async {

                    if let character {

                        characterName =
                            character.name

                        status =
                            "\(character.status) • \(character.species)"

                    } else {

                        characterName =
                            "Unable to load character"

                        status =
                            error?.localizedDescription
                            ?? "Unknown error"
                    }
                }
            }
        }
}

#Preview {
    ContentView()
}
