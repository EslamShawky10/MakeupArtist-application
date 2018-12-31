package com.eslamshawky.hp.makeupartist.AdaptersServiceProvider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.MyServiceModel;
import com.eslamshawky.hp.makeupartist.R;

import java.util.ArrayList;

public class RecyclerAdapterAddNewService extends RecyclerView.Adapter<RecyclerAdapterAddNewService.RecyclerViewHolderAddnewService> {
    private Context context;
    private ArrayList<MyServiceModel> myServiceModels;

    public RecyclerAdapterAddNewService(Context context, ArrayList<MyServiceModel> myServiceModels) {
        this.context = context;
        this.myServiceModels = myServiceModels;
    }

    public RecyclerAdapterAddNewService(Context context) {
        this.context = context;
    }

    public RecyclerAdapterAddNewService() {

    }

    public void setMyServiceModels(ArrayList<MyServiceModel> myServiceModels) {
        this.myServiceModels = myServiceModels;
    }

    @NonNull
    @Override
    public RecyclerViewHolderAddnewService onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.newservicename_price_type, parent, false);
        return new RecyclerViewHolderAddnewService(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderAddnewService viewHolder, int position) {
        MyServiceModel model = myServiceModels.get(position);
        String Name = model.getName();
        viewHolder.name.setText(Name);
        String Type = model.getType();
        viewHolder.type.setText(Type);
        String Price = model.getPrice();
        viewHolder.price.setText(Price);
    }

    @Override
    public int getItemCount() {
        return myServiceModels.size();
    }

    public class RecyclerViewHolderAddnewService extends RecyclerView.ViewHolder {
        TextView name, type, price;

        public RecyclerViewHolderAddnewService(View view) {
            super(view);
            name = view.findViewById(R.id.text_name);
            type = view.findViewById(R.id.text_type);
            price = view.findViewById(R.id.text_price);
        }
    }

}
