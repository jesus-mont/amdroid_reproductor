package com.example.jesusivan.exa3_reproductormusica_jellybeanteam;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicPlayer extends Service {
   public static MediaPlayer mpReproductor;

    public MusicPlayer() {
    }
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        int idCancion= intent.getExtras().getInt("Cancion");
        mpReproductor= MediaPlayer.create(getApplicationContext(),idCancion);
        if(!mpReproductor.isPlaying()){
            mpReproductor.start();

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mpReproductor.stop();
        mpReproductor.release();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }
}
