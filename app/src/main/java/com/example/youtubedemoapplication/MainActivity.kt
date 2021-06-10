package com.example.youtubedemoapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {
    companion object {
        var IS_VIDEO_PLAYING: Boolean = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getLifecycle().addObserver(youtube_player_view);
        runvideo()
    }

    private fun runvideo() {

      youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youtube_player_view.enterFullScreen()

                val videoId = "v_T3wW616Lc"


                videoId?.let { youTubePlayer.loadVideo(videoId, 0f) }

            }

            override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                when (state) {
                    PlayerConstants.PlayerState.BUFFERING -> {
                        IS_VIDEO_PLAYING = true
                    }
                    PlayerConstants.PlayerState.PLAYING -> {
                        IS_VIDEO_PLAYING = true
                    }
                    PlayerConstants.PlayerState.PAUSED -> {
                        IS_VIDEO_PLAYING = false
                    }
                    PlayerConstants.PlayerState.ENDED -> {
                        IS_VIDEO_PLAYING = false
                    }
                    PlayerConstants.PlayerState.VIDEO_CUED -> {
                        IS_VIDEO_PLAYING = false
                    }
                    PlayerConstants.PlayerState.UNSTARTED -> {
                        IS_VIDEO_PLAYING = false
                    }
                    PlayerConstants.PlayerState.UNKNOWN -> {
                        IS_VIDEO_PLAYING = false
                    }
                }
            }

            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                IS_VIDEO_PLAYING = false
            }
        })
    }


}