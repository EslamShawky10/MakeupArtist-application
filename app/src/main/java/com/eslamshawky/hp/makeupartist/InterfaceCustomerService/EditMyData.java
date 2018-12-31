package com.eslamshawky.hp.makeupartist.InterfaceCustomerService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.SingleTon;
import com.eslamshawky.hp.makeupartist.R;

import java.util.HashMap;
import java.util.Map;

public class EditMyData extends AppCompatActivity {
private EditText firstname,lastname,email,password;
private TextView saveData;
private String UrlEditUser = "http://live-artists.com/admin/api/edit/user/1/2";
private String FirstName,LastName,Email,PassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        firstname = (EditText)findViewById(R.id.editText_changeFirstName);
        lastname = (EditText)findViewById(R.id.editText_changeLastName);
        email = (EditText)findViewById(R.id.editText_changeEmail);
        password = (EditText)findViewById(R.id.editText_change_password);
        saveData = (TextView)findViewById(R.id.textView_saveData);

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  editUser();

            }
        });
    }
      public void editUser() {
          FirstName = firstname.getText().toString();
          LastName = lastname.getText().toString();
          Email = email.getText().toString();
          PassWord = password.getText().toString();

          StringRequest request = new StringRequest(Request.Method.POST, UrlEditUser, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                  System.out.println("aaa="+response);
                  Intent intent = new Intent(EditMyData.this,SearchingService.class);
                  startActivity(intent);
              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                  System.out.println("Error="+error.networkResponse.statusCode);
                  System.out.println("Error="+error.getMessage());
              }
          }){
              @Override
              protected Map<String, String> getParams() {
                  Map<String,String> params = new HashMap<>();
                  params.put("firstName",firstname.getText().toString());
                  params.put("lastName",lastname.getText().toString());
                  params.put("email",email.getText().toString());
                  params.put("password",password.getText().toString());
                  System.out.println("params=" + params.toString());
                  return params;
              }
          };
          SingleTon.getInstance(getBaseContext()).addToRequestQueue(request);
    }
    private boolean validateDataUser(){
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

        return true;
    }
}
