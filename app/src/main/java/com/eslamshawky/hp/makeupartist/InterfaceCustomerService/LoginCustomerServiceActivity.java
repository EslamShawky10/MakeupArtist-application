package com.eslamshawky.hp.makeupartist.InterfaceCustomerService;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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

public class LoginCustomerServiceActivity extends AppCompatActivity {
   EditText phoneNumber,password;
   CheckBox checkBox;
   Button logIn;
   TextView registerationUser;
   String UrlLoginUser = "http://live-artists.com/admin/api/user/login/2";
   String PhoNeNumber,PassWord;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer_service);
        phoneNumber = (EditText)findViewById(R.id.edittextphoneuser);
        password = (EditText)findViewById(R.id.editpassworduser);
        logIn = (Button)findViewById(R.id.button_loginuser);
        registerationUser = (TextView)findViewById(R.id.text_registeruser);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataLogIn();
            }
        });
        registerationUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (LoginCustomerServiceActivity.this,RegistrationCustomerServiceActivity.class);
                startActivity(i);
            }
        });
    }
    private void dataLogIn (){
        PassWord = password.getText().toString();
        PhoNeNumber = phoneNumber.getText().toString();

         if (isOnLine()==false) {
            Toast.makeText(LoginCustomerServiceActivity.this, "there is no internet connection..!", Toast.LENGTH_LONG).show();

        }
        else if (PhoNeNumber.equals("") || PassWord.equals("")) {
            Toast.makeText(getApplicationContext(), "Empty phoneNumber or Password...", Toast.LENGTH_LONG).show();
        }
        else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlLoginUser, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        int message_id = jsonObject.getInt("message_id");
                        Intent i = new Intent(LoginCustomerServiceActivity.this, YourServices.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"Login Successful!"+response,Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginCustomerServiceActivity.this, "erorr in response...", Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
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

}
