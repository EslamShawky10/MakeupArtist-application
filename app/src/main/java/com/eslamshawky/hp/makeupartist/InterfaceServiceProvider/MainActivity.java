package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eslamshawky.hp.makeupartist.InterfaceCustomerService.LoginCustomerServiceActivity;
import com.eslamshawky.hp.makeupartist.InterfaceCustomerService.RegistrationCustomerServiceActivity;
import com.eslamshawky.hp.makeupartist.R;

public class MainActivity extends AppCompatActivity {

    Button customer,service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customer = (Button)findViewById(R.id.button_customer);
        service = (Button)findViewById(R.id.button_service);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginCustomerServiceActivity.class);
                startActivity(intent);
            }
        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LogInActivity.class);
                startActivity(intent);
            }
        });
    }
}
