package com.eslamshawky.hp.makeupartist.AdaptersServiceProvider;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.ImageModel;
import com.eslamshawky.hp.makeupartist.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapterImages extends RecyclerView.Adapter<RecyclerAdapterImages.RecyclerViewHolder> {
  private Context context;
  private ArrayList<ImageModel> imageModels ;

    public RecyclerAdapterImages(Context context, ArrayList<ImageModel> imageModels) {
        this.context = context;
        this.imageModels = imageModels;
    }
    public RecyclerAdapterImages(Context context){
    this.context=context;
    }

    public void setImageModels(ArrayList<ImageModel> imageModels) {
        this.imageModels = imageModels;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.image,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        ImageModel currentImage = imageModels.get(position);
        int id = currentImage.getId();
        String imageUrl=currentImage.getUrl();
       Picasso.get().load(imageUrl).placeholder(R.drawable.ic_launcher_foreground).resize(400,400).centerInside().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        Button button;
        public RecyclerViewHolder(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.image_adapter);
            button = (Button)view.findViewById(R.id.button_add_image);
        }
    }
}
