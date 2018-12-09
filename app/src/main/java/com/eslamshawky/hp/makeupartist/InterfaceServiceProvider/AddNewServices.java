package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.ServiceTypeModel;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.SingleTon;
import com.eslamshawky.hp.makeupartist.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AddNewServices extends Dialog implements AdapterView.OnItemSelectedListener{
EditText name,price;
Spinner spinner;
Button  buttonaddService;
int n;
String urlTypeOfService  = "http://live-artists.com/admin/api/get/servicetype";
String urlAddNewService = "http://live-artists.com/admin/api/add/service";
private ArrayList <ServiceTypeModel> serviceTypeModels;

  public AddNewServices(@NonNull Context context) {
        super(context);
    }
   @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_add_new_services);
    name = (EditText)findViewById(R.id.editText_nameservice);
    price = (EditText) findViewById(R.id.editText_priceservice);
    spinner = (Spinner)findViewById(R.id.spinner);
    buttonaddService = (Button)findViewById(R.id.button_ok);
    serviceTypeModels = new ArrayList<>();
    spinner.setOnItemSelectedListener(this);
    addTypeOfServeice();

    buttonaddService.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           addToMyServiceBybutton();

        }
    });
}

  private void addTypeOfServeice(){

    StringRequest stringRequest= new StringRequest(Request.Method.GET, urlTypeOfService, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                System.out.println("response="+response);
                JSONObject object = new JSONObject(response);
                JSONArray jsonArray = object.getJSONArray("serviceType");
                for (int i = 0; i < jsonArray.length(); i++) {
                ServiceTypeModel model = new ServiceTypeModel();
                    JSONObject hit = jsonArray.getJSONObject(i);
                    String Title = hit.getString("title");
                    String Id = String.valueOf(hit.getInt("id"));
                    model.setTitle(Title);
                    System.out.println("titl"+Title);
                    model.setId(Integer.parseInt(Id));
                    serviceTypeModels.add(model);
                    ArrayAdapter<ServiceTypeModel> dataAdapter = new ArrayAdapter<ServiceTypeModel>
                            (getContext(), android.R.layout.simple_spinner_item, serviceTypeModels);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(dataAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println("Error="+error.networkResponse.statusCode);
            System.out.println("Error="+error.getMessage());

        }
    });
     int socketTimeout = 30000;
     RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
     stringRequest.setRetryPolicy(policy);
     SingleTon.getInstance(getContext()).addToRequestQueue(stringRequest);

  }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
    n= serviceTypeModels.get(spinner.getSelectedItemPosition()).getId();
        System.out.println(serviceTypeModels.get(spinner.getSelectedItemPosition()).getId());
       // Toast.makeText(getContext(),"id="+n,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void addToMyServiceBybutton(){
     StringRequest stringRequestAdd = new StringRequest(Request.Method.POST, urlAddNewService, new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {
             System.out.println("thisparam="+response);
                   dismiss();
         }
     }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
             System.out.println("Error"+error.getMessage());
         }
     }
     )
     {
         @Override
         protected Map<String, String> getParams() {
             Map<String, String> params = new HashMap<>();
             params.put("name","asss");
             params.put("price","12");
             params.put("servicetype_id", String.valueOf(n));
             params.put("description","Eslam");
             params.put("provider_id","6");
             System.out.println("param="+params.toString());
             return params;
         }
     };
        SingleTon.getInstance(getContext()).addToRequestQueue(stringRequestAdd);
    }
}
