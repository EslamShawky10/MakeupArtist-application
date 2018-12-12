package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.eslamshawky.hp.makeupartist.R;
import static com.eslamshawky.hp.makeupartist.InterfaceServiceProvider.Orders.ADDRESS;
import static com.eslamshawky.hp.makeupartist.InterfaceServiceProvider.Orders.NAME;
import static com.eslamshawky.hp.makeupartist.InterfaceServiceProvider.Orders.SERVICES;
import static com.eslamshawky.hp.makeupartist.InterfaceServiceProvider.Orders.TOTALPRICE;

public class DetailsOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_orders);
        Intent intent = getIntent();
        String name = intent.getStringExtra(NAME);
        String address = intent.getStringExtra(ADDRESS);
        String totalprice = intent.getStringExtra(TOTALPRICE);
        String services = intent.getStringExtra(SERVICES);
        TextView textViewName = findViewById(R.id.textView_name_details);
        TextView textViewAddress = findViewById(R.id.textView_address_details);
        TextView textViewTotalePrice = findViewById(R.id.textView_totalprice_details);
        TextView textViewServices = findViewById(R.id.textView_listofordersdetails);
        textViewName.setText(name);
        textViewAddress.setText(address);
        textViewTotalePrice.setText(totalprice);
        textViewServices.setText(services);

    }
}
