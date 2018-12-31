package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eslamshawky.hp.makeupartist.InterfaceCustomerService.LoginCustomerServiceActivity;
import com.eslamshawky.hp.makeupartist.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button customer,service;
    Button changeLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLocale();
        ActionBar bar=getSupportActionBar();
        bar.setTitle(getResources().getString(R.string.app_name));
        changeLanguage = (Button) findViewById(R.id.changeLanguase);
        customer = (Button)findViewById(R.id.button_customer);
        service = (Button)findViewById(R.id.button_service);
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shwoChangeLanguageDialog();
            }
        });
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
    private void shwoChangeLanguageDialog() {
        final String [] itemLanguage = {"العربية","English"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("choose language...");
        builder.setSingleChoiceItems(itemLanguage, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    setLocale("ar");
                    recreate();
                }
                else if (which == 1){
                    setLocale("en");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void setLocale(String ar) {
        Locale myLocale = new Locale(ar);
        Locale.setDefault(myLocale);
        Configuration configuration = new Configuration();
        configuration.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("settings",MODE_PRIVATE).edit();
        editor.putString("my_lang",ar);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences sharedPreferences = getSharedPreferences("settings",Activity.MODE_PRIVATE);
        String language = sharedPreferences.getString("my_lang","");
        setLocale(language);
    }
}
