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

    // With CommonFlow approach
    private func getCatFactWithCommonFlow() {
        let _ = catFactClient.getCatFactWithCommonFlow().subscribe(onCollect: { catFact in
            print(catFact as Any)
        })
    }

    // With SKIE approach
    private func getCatFactWithFlow() {
        let _ = catFactClient.getCatFactWithFlow().map { catFact in
            print(catFact)
        }
    }

    // Simple suspend function
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
