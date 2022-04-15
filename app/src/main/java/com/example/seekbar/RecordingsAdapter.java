package com.example.seekbar;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//hello
public class RecordingsAdapter extends RecyclerView.Adapter<RecordingsAdapter.ViewHolder>{

    String TAG = this.getClass().getSimpleName();
    List<String> recordinglist;
    Context context;
    MediaPlayer mediaPlayer;

    int current_pos,total_duration;

    public RecordingsAdapter(List<String> recordinglist, Context context) {
        this.recordinglist = recordinglist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        holder.textView.setText(recordinglist.get(position));

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog);
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

                dialog.show();

                AppCompatSeekBar seekBar=  dialog.findViewById(R.id.seekbardialog);

                playAudio(seekBar, Uri.parse(recordinglist.get(position)));

            }
        });
    }

    @Override
    public int getItemCount() {
//        Log.e(TAG, "getItemCount: "+recordinglist.size() );
        return recordinglist==null?0:recordinglist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView =  itemView.findViewById(R.id.tv);
            }
    }






    //play audio file
    public void playAudio(final AppCompatSeekBar seekBar,Uri uri) {
        mediaPlayer = new MediaPlayer();
        try  {
            mediaPlayer.reset();
            //set file path
            mediaPlayer.setDataSource(context, uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setAudioProgress(seekBar);
    }

    public void setAudioProgress(final AppCompatSeekBar seekBar) {
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
        handler.postDelayed(runnable, 100);
    }
}
