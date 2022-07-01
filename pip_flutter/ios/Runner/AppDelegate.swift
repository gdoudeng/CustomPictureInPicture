import UIKit
import Flutter

@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate {
    override func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        GeneratedPluginRegistrant.register(with: self)
        
        // 注册渠道
        let channel = FlutterMethodChannel(name: "pip", binaryMessenger: self.window?.rootViewController as! FlutterBinaryMessenger)
        channel.setMethodCallHandler { (call, res) in
            // 根据函数名做不同的处理
            switch(call.method) {
            case "gotoPipPage":
                self.gotoPipPage()
            case "dismissPipPage":
                self.dismissPipPage()
            default:
                res(nil)
            }
        }
        
        return super.application(application, didFinishLaunchingWithOptions: launchOptions)
    }

    let pipVC = PipViewController()
    
    private func gotoPipPage() {
        pipVC.modalPresentationStyle = .fullScreen
        window.rootViewController?.present(pipVC, animated: true, completion: nil)
    }

    private func dismissPipPage() {
        pipVC.dismiss(animated: true, completion: nil)
    }
    
}
