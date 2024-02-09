import SwiftUI
import shared

struct ContentView: View {
    let catFactClient = CatFactKtorClient(httpClient: HttpClientFactory().create())

    @State var catFact: String = ""

    var body: some View {
        Text(catFact)
            .task {
                do {
                    catFact = try await getCatFactWithSuspend().fact
                }
                catch {
                    print(error)
                }
            }
    }

    private func getCatFactWithCommonFlow() {
        let catFact = catFactClient.getCatFactWithCommonFlow()
        catFact.subscribe(onCollect: { catFact in

        })
    }

    // With SKIE
    private func getCatFactWithFlow() {
        let catFact = catFactClient.getCatFactWithFlow()
    }

    @MainActor
    private func getCatFactWithSuspend() async throws -> CatFact {
        
        let catFact = try await catFactClient.getCatFactWithSuspend()

        return catFact
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
