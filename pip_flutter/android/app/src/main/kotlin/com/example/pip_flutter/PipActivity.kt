package com.example.pip_flutter

import android.app.Activity
import android.app.PictureInPictureParams
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pip_flutter.Adapter
import com.example.pip_flutter.R
import java.util.*

class PipActivity : Activity() {
    private var mBuilder: PictureInPictureParams.Builder? = null
    var recyclerView: RecyclerView? = null
    var btn: Button? = null
    var timer: Timer? = null
    var postion: Int = 0
    private val textList = arrayListOf(
        "放松再慢慢升空", "被窝里做个梦", "外面的噪音太多", "我想要翻过山峰", "没人能打扰我",
        "戴上耳机再脑海里面寻宝", "再见里我的年少轻狂和莽撞", "再见那个夏天为你快的心跳", "造一座我的王国",
        "关闭所有的讯号", "背后风凉话的全听好", "成为大家的焦点", "我的徽章意见反馈", "我要投稿",
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pip)

        // 返回按钮
        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        btn = findViewById(R.id.btn)
        recyclerView = findViewById(R.id.recycle)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = Adapter(textList)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
            && getPackageManager().hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)
        ) {
            mBuilder = PictureInPictureParams.Builder()
        }
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    val linearManager = recyclerView!!.layoutManager as LinearLayoutManager?
                    //最后一个可见view的位置
                    val mLastVisibleItemPosition = linearManager!!.findLastVisibleItemPosition()

                    if (mLastVisibleItemPosition == textList.size - 1) {
                        recyclerView!!.smoothScrollToPosition(0)
                    } else {
                        recyclerView!!.smoothScrollToPosition(mLastVisibleItemPosition + 1)
                    }
                    Log.v("可见的最后一个", mLastVisibleItemPosition.toString())
                }

            }
        }, 2000, 1000)

        btn!!.setOnClickListener {
            if (mBuilder != null) {
                var rational = Rational(3, 2)
                mBuilder!!.setAspectRatio(rational).build();
                enterPictureInPictureMode(mBuilder!!.build())
            } else {
                Toast.makeText(this, "不能开启画中画", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroy() {
        Log.v("画中画", "onDestroy")
        super.onDestroy()
        if (timer != null) {
            timer!!.cancel()
        }
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration?
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        if (isInPictureInPictureMode) {
            Log.v("画中画", "on")
            btn!!.visibility = GONE
        } else {
            Log.v("画中画", "off")
            btn!!.visibility = VISIBLE
        }
    }

}