package com.example.onlineschoolwithfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        var videoOne = "https://firebasestorage.googleapis.com/v0/b/onlineschoolwithfirebase.appspot.com/o/1.%20Watch%20the%20Videos%20in%20HD%20Quality.mp4?alt=media&token=16c5e4b0-cfde-47de-ab42-a8e37699623c"
        var videoTwo = "https://firebasestorage.googleapis.com/v0/b/onlineschoolwithfirebase.appspot.com/o/2.%20Welcome%20Message.mp4?alt=media&token=9fa35555-1c0f-4ca8-b9f6-7d42f492e65d"
        var videoThree = "https://firebasestorage.googleapis.com/v0/b/onlineschoolwithfirebase.appspot.com/o/1.%20Watch%20the%20Videos%20in%20HD%20Quality.mp4?alt=media&token=16c5e4b0-cfde-47de-ab42-a8e37699623c"



        var items = ArrayList<Video>()
        items.add(Video("Android Git and Github with Android Studio" , "Android development with zero to hero." , videoOne))
        items.add(Video("Android Git and Github with Android Studio" , "Android development with zero to hero." , videoTwo))
        items.add(Video("Android Git and Github with Android Studio" , "Android development with zero to hero." , videoThree))

        var list = findViewById<RecyclerView>(R.id.video_list)

        var adapter = Adapter(items , applicationContext)

        list.layoutManager = GridLayoutManager(applicationContext , 1)
        list.adapter = adapter


    }


}