package com.example.seekbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String TAG = this.getClass().getSimpleName();
    int current_pos,total_duration;
    MediaPlayer mediaPlayer;
    AppCompatSeekBar seekBar;
    Button btn;

    private RewardedAd mRewardedAd;


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG+"111", "onPause: " );
        //onResume();

        Log.e(TAG, "onPause: seeeeekbar" );
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG+"111", "onResume: " );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG+"111", "onStart: " );

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG+"111", "onRestart: " );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG+"111", "onStop: " );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG+"111", "onDestroy: " );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG+"111", "onCreate: " );

        seekBar= findViewById(R.id.seekbar);
        btn= findViewById(R.id.btn);


        ///switch

        //Switch switcher = findViewById(R.id.switcher);
        //switcher.setTrackDrawable(new SwitchTrack(this,"Left","Right"));

        palindrom();

        mediaPlayer = new MediaPlayer();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new RuntimeException("Test Crash"); // Force a crash

               // playAudio();

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


        for (int j = 0; j < 5; j++) {
            new NewAsync().execute();
            Log.e(TAG, "onCreate: forloop "+j );
        }



        /*=============================reward video=======================*/

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull @org.jetbrains.annotations.NotNull InitializationStatus initializationStatus) {

            }
        });

       /* AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback(){

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.e(TAG, loadAdError.getMessage());
                        mRewardedAd = null;
                    }


                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Log.e(TAG, "Ad was loaded.");

                        if (mRewardedAd != null) {
                            Activity activityContext = MainActivity.this;
                            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {


                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    // Handle the reward.
                                    Log.e(TAG, "The user earned the reward.");
                                    int rewardAmount = rewardItem.getAmount();
                                    String rewardType = rewardItem.getType();
                                }

                            });
                        } else {
                            Log.e(TAG, "The rewarded ad wasn't ready yet.");
                        }

                    }
                });
*/

       




        /*=============================reward video=======================*/



        String date = "14 mar 2018";
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        Date testDate = null;
        try {
            testDate = sdf.parse(date);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String newFormat = formatter.format(testDate);
        Log.e(TAG, "onCreate: new format "+newFormat );

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


    class NewAsync extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "onPreExecute: " );
        }

        @Override
        protected String doInBackground(String... strings) {

            for (int i = 0; i < 5 ; i++) {
                Log.e(TAG, "doInBackground: "+i );

            }
            return null;
        }
    }


    public void palindrom(){

        int a = 102;
        int b , c=0;
        int temp = a;
        while (a>0) {
            b = a % 10;
            c = (c*10)+b;

            a = a/10;
        }


        Log.e(TAG, "palindrom: cc "+c );




    }





}