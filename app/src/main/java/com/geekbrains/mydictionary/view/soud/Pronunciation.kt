package com.geekbrains.mydictionary.view.soud

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.util.Log
import java.io.IOException

class Pronunciation (private val context: Context): OnPreparedListener {
    private val mediaPlayer = MediaPlayer()
    fun playUrl(url: String) {

        try {
//            mediaPlayer.setAudioAttributes(AudioAttributes.Builder()
//                .setUsage(AudioAttributes.USAGE_MEDIA)
//                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
//                .build())
//            mediaPlayer.setDataSource(context, Uri.parse("https:$url"))
//            mediaPlayer.prepare()
//            mediaPlayer.setVolume(100f, 100f)
//            mediaPlayer.start()
            mediaPlayer.setDataSource(context, Uri.parse("https:$url"))
            mediaPlayer.setOnPreparedListener(this)
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener {
                it.start()
            }
            mediaPlayer.start()
        } catch (e: IOException) {
            Log.e("SOUND", "prepare() failed")
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }
}