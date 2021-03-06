package com.eslamshawky.hp.makeupartist.AdaptersServiceProvider;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.eslamshawky.hp.makeupartist.InterfaceServiceProvider.DetailsOrders;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.OrdersModel;
import com.eslamshawky.hp.makeupartist.R;

import java.util.ArrayList;

public class RecyclerAdapterDisplayOrder extends RecyclerView.Adapter <RecyclerAdapterDisplayOrder.RecyclerDisplayViewHolder> {
    private Context context;
    ArrayList<OrdersModel> ordersModels;
    private OnItemClickListener mListner;

    public interface  OnItemClickListener{
        void onItemClick(int position );
        }
        public void setOnItemClickLestener(OnItemClickListener listener){
        mListner=listener;
        }
      public RecyclerAdapterDisplayOrder(Context context, ArrayList<OrdersModel> ordersModels) {
        this.context = context;
        this.ordersModels = ordersModels;
    }

    public RecyclerAdapterDisplayOrder(Context context) {
        this.context = context;
    }

    public RecyclerAdapterDisplayOrder() {
    }

    public void setOrdersModels(ArrayList<OrdersModel> ordersModels) {
        this.ordersModels = ordersModels;
    }

    @NonNull
    @Override
    public RecyclerDisplayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.displayorders,viewGroup,false);
        return new RecyclerDisplayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerDisplayViewHolder viewHolder, int position) {
        OrdersModel model = ordersModels.get(position);
             String Name = model.getName();
             String Date = model.getDate();
             String ToTale = model.getTotalPrice();
             viewHolder.name.setText(Name);
             viewHolder.date.setText(Date);
             viewHolder.totalPrice.setText(ToTale);

    }

    @Override
    public int getItemCount() {
        return ordersModels.size();
    }

    public class RecyclerDisplayViewHolder extends RecyclerView.ViewHolder{
        Button showDetails;
        TextView name,date,totalPrice;
        public RecyclerDisplayViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textNameOrder);
            date = itemView.findViewById(R.id.textDate);
            totalPrice = itemView.findViewById(R.id.textTotalPrice);
            showDetails = itemView.findViewById(R.id.button_lastOrders);
            showDetails.setOnClickListener(new View.OnClickListener() {
               @Override
          public void onClick(View v) {
           if(mListner !=null ) {
               int position = getAdapterPosition();
               if(position !=RecyclerView.NO_POSITION){
                   mListner.onItemClick(position);
               }
           }
         }
     });
        }
    }
}
