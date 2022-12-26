import SwiftUI
import common

@UIApplicationMain
class AppDelegate: NSObject, UIApplicationDelegate {
    var window: UIWindow?
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        let controller = AvoidDispose(RootViewControllersKt.RootViewController())
        controller.view.backgroundColor = .white
        let window = UIWindow(frame: UIScreen.main.bounds)
        window.backgroundColor = .white
        window.rootViewController = controller
        window.makeKeyAndVisible()
        self.window = window

        SharedDI.init().initializeWithParams(
            databaseDriver: DatabaseDriverFactory().createDriver(),
            sharedPreferences: Kmm_preferencesSharedPreferences()
            )
        
        return true
    }
}
