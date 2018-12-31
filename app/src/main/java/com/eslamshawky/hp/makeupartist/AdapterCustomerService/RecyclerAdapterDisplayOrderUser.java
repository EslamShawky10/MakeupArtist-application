package com.eslamshawky.hp.makeupartist.AdapterCustomerService;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.eslamshawky.hp.makeupartist.AdaptersServiceProvider.RecyclerAdapterDisplayOrder;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.OrdersModel;
import com.eslamshawky.hp.makeupartist.R;

import java.util.ArrayList;

public class RecyclerAdapterDisplayOrderUser extends RecyclerView.Adapter <RecyclerAdapterDisplayOrderUser.RecyclerDisplayViewHolder> {
    private Context context;
    ArrayList<OrdersModel> ordersModels;
    private RecyclerAdapterDisplayOrder.OnItemClickListener mListner;


    public RecyclerAdapterDisplayOrderUser(Context context, ArrayList<OrdersModel> ordersModels) {
        this.context = context;
        this.ordersModels = ordersModels;
    }

    public RecyclerAdapterDisplayOrderUser(Context context) {
        this.context = context;
    }

    public RecyclerAdapterDisplayOrderUser() {
    }

    public void setOrdersModels(ArrayList<OrdersModel> ordersModels) {
        this.ordersModels = ordersModels;
    }

    @NonNull
    @Override
    public RecyclerAdapterDisplayOrderUser.RecyclerDisplayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.displayorderuser,viewGroup,false);
        return new RecyclerAdapterDisplayOrderUser.RecyclerDisplayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterDisplayOrderUser.RecyclerDisplayViewHolder viewHolder, int position) {
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
        TextView name,date,totalPrice;
        public RecyclerDisplayViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textNameOrder);
            date = itemView.findViewById(R.id.textDate);
            totalPrice = itemView.findViewById(R.id.textTotalPrice);

        }
    }
}
