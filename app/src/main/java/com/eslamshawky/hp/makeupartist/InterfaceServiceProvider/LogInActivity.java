package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.SingleTon;
import com.eslamshawky.hp.makeupartist.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LogInActivity extends AppCompatActivity {
    public SharedPreferences preferences,sh;
    private String prefName = "report";
    SharedPreferences.Editor editor;
    private String sharedUserName,sharedPassWord;
    private EditText username, password;
    private Button LogIn;
    private TextView signUp;
    private CheckBox checkBox;
    private ProgressBar progressBar;
    private   String Url_log = "http://live-artists.com/admin/api/provider/login/2";
    private String UserName,PassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        sh= getSharedPreferences(prefName,MODE_PRIVATE);
        sharedUserName = sh.getString("username","UserName");
        sharedPassWord =  sh.getString("password","PassWord");
        username = (EditText) findViewById(R.id.edit_username);
        password = (EditText) findViewById(R.id.edit_password);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        checkBox = (CheckBox)findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    // show password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        signUp = (TextView) findViewById(R.id.text_register);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogInActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        LogIn = (Button) findViewById(R.id.button_login);
        LogIn.setOnClickListener(new View.OnClickListener() {
          @Override
       public void onClick(View v) {
        UserName = username.getText().toString();
        PassWord = password.getText().toString();
        password.setTransformationMethod(new PasswordTransformationMethod());

        if (isOnline()==false) {
            Toast.makeText(LogInActivity.this, "there is no internet connection..!", Toast.LENGTH_LONG).show();

        }
        else if (UserName.equals("") || PassWord.equals("")) {
            Toast.makeText(getApplicationContext(), "Empty username or Password...", Toast.LENGTH_LONG).show();
        }

        else {
          StringRequest objectRequest = new StringRequest(Request.Method.POST, Url_log, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.VISIBLE);
                try {
                //    System.out.println("response" + response);
                //    Toast.makeText(LogInActivity.this, response, Toast.LENGTH_SHORT).show();
                    JSONObject array = new JSONObject(response);
                    Intent intent = new Intent(LogInActivity.this, ServiceProvider.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LogInActivity.this, "erorr", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username.getText().toString());
                params.put("password", password.getText().toString());
                System.out.println("params=" + params.toString());
                return params;
            }
        };
        SingleTon.getInstance(getBaseContext()).addToRequestQueue(objectRequest);
    }
              preferences = getSharedPreferences(prefName, MODE_PRIVATE);
              editor = preferences.edit();
              editor.putString("username", username.getText().toString());
              editor.putString("password", password.getText().toString());
              editor.commit();
    }
    });
     }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}

