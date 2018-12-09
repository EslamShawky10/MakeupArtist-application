package com.eslamshawky.hp.makeupartist.AdaptersServiceProvider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class RecyclerAdapterDetailsOrder extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DetailsOrderVieHolder extends RecyclerView.ViewHolder {

        public DetailsOrderVieHolder(View view) {
            super(view);
        }
    }
}
