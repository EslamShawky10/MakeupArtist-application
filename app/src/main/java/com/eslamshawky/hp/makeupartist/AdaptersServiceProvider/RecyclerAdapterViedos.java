package com.eslamshawky.hp.makeupartist.AdaptersServiceProvider;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.VideoModel;
import com.eslamshawky.hp.makeupartist.R;

import java.util.ArrayList;

public class RecyclerAdapterViedos extends RecyclerView.Adapter<RecyclerAdapterViedos.RecyclerViewHolder> {

    private Context context;
    private ArrayList<VideoModel> videoModels;

    public RecyclerAdapterViedos(Context context, ArrayList<VideoModel> videoModels) {
        this.context = context;
        this.videoModels = videoModels;
    }

    public RecyclerAdapterViedos(Context context) {
        this.context = context;
    }
       public void setVideoModels(ArrayList<VideoModel> videoModels){
        this.videoModels=videoModels;
        }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.viedo,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder viewHolder, int i) {

        VideoModel currentVideo = videoModels.get(i);
        int id = currentVideo.getIdVideo();
        String url = currentVideo.getUrlVideo();
        System.out.println("urlvideo="+url);
        Uri uri = Uri.parse(url);
        MediaController mediaController = new MediaController(context);
        mediaController.setAnchorView(viewHolder.videoView);
        mediaController.setMediaPlayer(viewHolder.videoView);
        viewHolder.videoView.setMediaController(mediaController);
        viewHolder.videoView.setVideoURI(uri);
        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewHolder.videoView.start();
                viewHolder.videoView.requestFocus();
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoModels.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
         VideoView videoView;
         ImageButton imageButton;
        public RecyclerViewHolder(View view) {
            super(view);
            videoView = (VideoView) view.findViewById(R.id.video_adapter);
            imageButton = (ImageButton) view.findViewById(R.id.image_button);
        }
    }
}
