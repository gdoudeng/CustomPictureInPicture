//
//  AppDelegate.m
//  pip_oc
//
//  Created by 无夜之星辰 on 2021/5/26.
//

#import "AppDelegate.h"

@interface AppDelegate ()

@end

@implementation AppDelegate {
    UIBackgroundTaskIdentifier *bgTask;
}


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    // Override point for customization after application launch.
    return YES;
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    UIApplication*    app = [UIApplication sharedApplication];

        bgTask = [app beginBackgroundTaskWithExpirationHandler:^{
            [app endBackgroundTask:bgTask];
            bgTask = UIBackgroundTaskInvalid;
        }];

        // Start the long-running task and return immediately.
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{

            // Do the work associated with the task.
            //[self startTimerAction];
            [app endBackgroundTask:bgTask];
            bgTask = UIBackgroundTaskInvalid;
        });
}


#pragma mark - UISceneSession lifecycle


- (UISceneConfiguration *)application:(UIApplication *)application configurationForConnectingSceneSession:(UISceneSession *)connectingSceneSession options:(UISceneConnectionOptions *)options {
    // Called when a new scene session is being created.
    // Use this method to select a configuration to create the new scene with.
    return [[UISceneConfiguration alloc] initWithName:@"Default Configuration" sessionRole:connectingSceneSession.role];
}


- (void)application:(UIApplication *)application didDiscardSceneSessions:(NSSet<UISceneSession *> *)sceneSessions {
    // Called when the user discards a scene session.
    // If any sessions were discarded while the application was not running, this will be called shortly after application:didFinishLaunchingWithOptions.
    // Use this method to release any resources that were specific to the discarded scenes, as they will not return.
}


@end
