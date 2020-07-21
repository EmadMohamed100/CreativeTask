package com.crowdfunding.streamingvideos;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

/**
 * Created by Emad Mohamed Oun on 12/31/2019.
 * Rytalo
 * emad.3oon@gmail.com
 */
class MainActivity extends Activity implements SurfaceHolder.Callback {

    MediaPlayer mediaPlayer;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean pausing = false;;

    String stringPath = "/sdcard/samplevideo.3gp";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPlayVideo =(Button) findViewById (R.id.playvideoplayer);
        Button buttonPauseVideo =(Button) findViewById (R.id.pausevideoplayer);

        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = (SurfaceView) findViewById (R.id.surfaceview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFixedSize(176, 144);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mediaPlayer = new MediaPlayer ();

        buttonPlayVideo.setOnClickListener(new Button.OnClickListener (){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pausing = false;

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                }

                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDisplay(surfaceHolder);

                try {
                    try {
                        mediaPlayer.setDataSource(stringPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.prepare();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                mediaPlayer.start();


            }
        });

        buttonPauseVideo.setOnClickListener(new Button . OnClickListener (){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (pausing) {
                    pausing = false;
                    mediaPlayer.start();
                } else {
                    pausing = true;
                    mediaPlayer.pause();
                }
            }
        });

    }



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }
}
