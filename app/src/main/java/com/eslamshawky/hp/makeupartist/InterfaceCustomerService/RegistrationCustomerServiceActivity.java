package com.eslamshawky.hp.makeupartist.InterfaceCustomerService;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.eslamshawky.hp.makeupartist.InterfaceServiceProvider.LogInActivity;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.SingleTon;
import com.eslamshawky.hp.makeupartist.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationCustomerServiceActivity extends AppCompatActivity {

private EditText firstName,lastName,email,password,phoneNumber;
private Button register;
private String FirstName,LastName,Email,PassWord,PhoNeNumber;
private String UrlRegisterUser = "http://live-artists.com/admin/api/add/user/2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_customer_service);
        firstName = (EditText)findViewById(R.id.editTextfirstnameuser);
        lastName = (EditText)findViewById(R.id.editTextlastnameuser);
        email = (EditText)findViewById(R.id.editTextemailuser);
        password = (EditText)findViewById(R.id.editTextpassworduser);
        phoneNumber = (EditText)findViewById(R.id.editTextnumberphoneuser);
        register = (Button)findViewById(R.id.button_registeruser);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(validateDataRegistiration()){
                   regtrationUser();
               }
            }
        });
    }

    public void regtrationUser() {
        FirstName = firstName.getText().toString();
        LastName = lastName.getText().toString();
        Email = email.getText().toString();
        PassWord = password.getText().toString();
        PhoNeNumber = phoneNumber.getText().toString();
        if (isOnLine() == false) {
            Toast.makeText(RegistrationCustomerServiceActivity.this, "there is no internet connection..!", Toast.LENGTH_LONG).show();

        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlRegisterUser, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject array = new JSONObject(response);
                        Intent i = new Intent(RegistrationCustomerServiceActivity.this, YourServices.class);
                        startActivity(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegistrationCustomerServiceActivity.this, "erorr in response...", Toast.LENGTH_LONG).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("firstName", FirstName);
                    params.put("lastName", LastName);
                    params.put("email", Email);
                    params.put("phone", PhoNeNumber);
                    params.put("password", PassWord);
                    System.out.println("params=" + params.toString());
                    return params;
                }
            };

            SingleTon.getInstance(getBaseContext()).addToRequestQueue(stringRequest);
        }
    }
    private boolean isOnLine(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return  networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    private boolean validateDataRegistiration(){
        if (FirstName != null && !FirstName.isEmpty()) {

        } else {
            Toast.makeText(getBaseContext(), "empty your lastName...", Toast.LENGTH_LONG).show();
        }

        if (LastName != null && !LastName.isEmpty()) {

        } else {
            Toast.makeText(getBaseContext(), "empty your userName...", Toast.LENGTH_LONG).show();
        }

        if (Email != null && !Email.isEmpty() && Email.contains("@")
                && Email.indexOf(".", Email.indexOf("@") + 1) != -1) {
        } else {
            Toast.makeText(getBaseContext(), "empty your Email", Toast.LENGTH_LONG).show();
        }

        if (PassWord != null && !PassWord.isEmpty()) {

        } else {
            Toast.makeText(getBaseContext(), "empty your password", Toast.LENGTH_LONG).show();
        }

        if (PhoNeNumber != null && !PhoNeNumber.isEmpty()) {

        } else {
            Toast.makeText(getBaseContext(), "empty your Phone number", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
