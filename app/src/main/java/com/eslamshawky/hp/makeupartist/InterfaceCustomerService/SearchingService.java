package com.eslamshawky.hp.makeupartist.InterfaceCustomerService;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.eslamshawky.hp.makeupartist.InterfaceServiceProvider.RegisterActivity;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.ServiceTypeModel;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.SingleTon;
import com.eslamshawky.hp.makeupartist.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.koushikdutta.ion.builder.Builders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class SearchingService extends Fragment implements GoogleApiClient.OnConnectionFailedListener, AdapterView.OnItemSelectedListener {
    private EditText selectPlace,serviceName,time ,date;
    private Spinner spinner;
    private Button search;
    private GoogleApiClient mGoogleApiClient;
    private int PLACE_PICKER_REQUEST = 1;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;
    int day,month,year;
    int n;
    String url = "http://live-artists.com/admin/api/search/service/providers";
    String urlTypeOfService = "http://live-artists.com/admin/api/get/servicetype";
    private ArrayList<ServiceTypeModel> serviceTypeModels;
    String name;

    public SearchingService() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_serarching, container, false);
        selectPlace = view.findViewById(R.id.selectePlace);
        spinner = view.findViewById(R.id.spinner);
        serviceName = view.findViewById(R.id.nameService);
        date =view.findViewById(R.id.date);
        time = view.findViewById(R.id.time);
        search = view.findViewById(R.id.searchProvider);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTypeOfServeice();
                name  = serviceName.getText().toString();
                //provider_Id = search.getText().toString();
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response)
                            {
                                try {
                                    JSONArray array = new JSONArray(response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {

                                error.printStackTrace();
                                error.getMessage(); // when error come i will log it
                            }
                        }
                )
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("service_name", name);
                     //   params.put("servicetype_id",provider_Id);
                        return params;
                    }
                };
                SingleTon.getInstance(getContext()).addToRequestQueue(stringRequest);

            }
        });
        serviceTypeModels = new ArrayList<>();
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

       addTypeOfServeice();


       date.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.N)
           @Override
           public void onClick(View v) {
               if (v == date) {

                   // Get Current Date
                   final Calendar c = Calendar.getInstance();
                   year = c.get(Calendar.YEAR);
                   month = c.get(Calendar.MONTH);
                   day = c.get(Calendar.DAY_OF_MONTH);


                   DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                           new DatePickerDialog.OnDateSetListener() {

                               @Override
                               public void onDateSet(DatePicker view, int year,
                                                     int monthOfYear, int dayOfMonth) {

                                   date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                               }
                           }, year, month, day);
                   datePickerDialog.show();
               }
           }
       });
       time.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.N)
           @Override
           public void onClick(View v) {
               calendar = Calendar.getInstance();
               currentHour = calendar.get(Calendar.HOUR_OF_DAY);
               currentMinute = calendar.get(Calendar.MINUTE);
               timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                       if (hourOfDay >= 12) {
                           amPm = "PM";
                       } else {
                           amPm = "AM";
                       }
                       time.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                   }
               }, currentHour, currentMinute, false);

               timePickerDialog.show();
           }
       });


        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(getActivity(),this)
                .build();

       selectPlace.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
               try {
                   startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);

               } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {

                   e.printStackTrace();

               };

           }
       });
       return view;
    }
    @SuppressLint("NewApi")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PLACE_PICKER_REQUEST) {
                Place place = PlacePicker.getPlace(getContext(), data);
                StringBuilder stBuilder = new StringBuilder();
                String placename = String.format("%s", place.getName());
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                String Address = String.format("%s", place.getAddress());
                stBuilder.append("Name: ");
                stBuilder.append(placename);
                stBuilder.append("\n");
                stBuilder.append("Latitude: ");
                stBuilder.append(latitude);
                stBuilder.append("\n");
                stBuilder.append("Logitude: ");
                stBuilder.append(longitude);
                stBuilder.append("\n");
                stBuilder.append("Address: ");
                stBuilder.append(Address);
                selectPlace.setText(Address);

            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void addTypeOfServeice() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlTypeOfService, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("response=" + response);
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("serviceType");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        ServiceTypeModel model = new ServiceTypeModel();
                        JSONObject hit = jsonArray.getJSONObject(i);
                        String Title = hit.getString("title");
                        String Id = String.valueOf(hit.getInt("id"));
                        model.setTitle(Title);
                        System.out.println("titl" + Title);
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
                System.out.println("Error=" + error.networkResponse.statusCode);
                System.out.println("Error=" + error.getMessage());

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
        n = serviceTypeModels.get(spinner.getSelectedItemPosition()).getId();
        System.out.println(serviceTypeModels.get(spinner.getSelectedItemPosition()).getId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
