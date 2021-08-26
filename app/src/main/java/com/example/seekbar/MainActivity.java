package com.example.seekbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int current_pos,total_duration;
    MediaPlayer mediaPlayer;
    AppCompatSeekBar seekBar;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar= findViewById(R.id.seekbar);
        btn= findViewById(R.id.btn);

        mediaPlayer = new MediaPlayer();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playAudio();

               /* mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tera);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();

                setAudioProgress();*/

            }
        });


        ArrayList<String> arrayList=new ArrayList<>();

        arrayList.add("/storage/emulated/0/Voice Recordings/AUD_20200903162439.mp3");
        arrayList.add("/storage/emulated/0/Voice Recordings/AUD_20200903162439.mp3");


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        RecordingsAdapter adapter = new RecordingsAdapter(arrayList,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



    }



    //play audio file
    public void playAudio() {
        try  {
            mediaPlayer.reset();
            //set file path
            mediaPlayer.setDataSource(this, Uri.parse("/storage/emulated/0/Voice Recordings/AUD_20200903162439.mp3"));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setAudioProgress();
    }
    public void setAudioProgress() {
        //get the audio duration
        current_pos = mediaPlayer.getCurrentPosition();
        total_duration = mediaPlayer.getDuration();

        //display the audio duration
        seekBar.setMax((int) total_duration);
        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    current_pos = mediaPlayer.getCurrentPosition();
                    seekBar.setProgress((int) current_pos);
                    handler.postDelayed(this, 100);
                } catch (IllegalStateException ed){
                    ed.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }

}