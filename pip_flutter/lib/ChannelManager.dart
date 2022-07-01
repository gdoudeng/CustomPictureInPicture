// Author: 无夜之星辰
// DateTime: 2022/06/17 08:23

import 'package:flutter/services.dart';

class ChannelManager {
  static const MethodChannel _channel = MethodChannel('pip');

  /// 跳转到画中画页面
  static Future<void> gotoPipPage() async {
    return await _channel.invokeMethod('gotoPipPage');
  }

  /// 退出画中画页面
  static Future<void> dismissPipPage() async {
    return await _channel.invokeMethod('dismissPipPage');
  }
}
