package com.example.pip_flutter

import android.content.Intent
import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity() {
    private val CHANNEL = "pip"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger,CHANNEL).setMethodCallHandler(
            MethodChannel.MethodCallHandler { call, result ->
                run {
                    if (call.method.contentEquals("gotoPipPage")) {
                        Log.d("TAG", "gotoPipPage: ")
                        startActivity(Intent(this@MainActivity, PipActivity::class.java))
                    }
                }
            }
        )
    }
}
