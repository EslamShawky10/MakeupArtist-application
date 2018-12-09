package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eslamshawky.hp.makeupartist.R;

public class DetailsOrders extends Fragment {
 TextView name,phoneNumber,address,totalPrice,services;
 TextView back;

    public DetailsOrders() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_details_orders, container, false);
        name = view.findViewById(R.id.textView_name_details);
        phoneNumber = view.findViewById(R.id.textView_phone_details);
        address = view.findViewById(R.id.textView_address_details);
        totalPrice = view.findViewById(R.id.textView_price_details);
        services = view.findViewById(R.id.textView_service_details);
        back = view.findViewById(R.id.textView_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Orders.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
