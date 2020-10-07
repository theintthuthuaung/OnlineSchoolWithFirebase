package com.example.onlineschoolwithfirebase

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.RecyclerView
import tcking.github.com.giraffeplayer2.VideoView

class Adapter (data:ArrayList<Video> , var context: Context) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    var data:ArrayList<Video>

    init {
        this.data = data
    }



    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

        var title:TextView
        var desc:TextView
        var video:VideoView
        var download:Button

        init {
            title = itemView.findViewById(R.id.lesson_title)
            desc = itemView.findViewById(R.id.lesson_desc)
            video = itemView.findViewById(R.id.video_item)
            download = itemView.findViewById(R.id.download_btn)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var layout:View
        layout = LayoutInflater.from(parent.context).inflate(R.layout.video_item , parent , false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var itemData = data[position]
        holder.title.text = itemData.title
        holder.desc.text = itemData.desc
        holder.video.setVideoPath(itemData.videoURL)
        holder.download.setOnClickListener {

            var download = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            var videoURI = Uri.parse(itemData.videoURL)
            var getVideo = DownloadManager.Request(videoURI)
            getVideo.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            download.enqueue(getVideo)
        }
    }
}