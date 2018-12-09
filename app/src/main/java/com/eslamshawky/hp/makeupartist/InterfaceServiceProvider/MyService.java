package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.eslamshawky.hp.makeupartist.AdaptersServiceProvider.RecyclerAdapterAddNewService;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.MyServiceModel;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.SingleTon;
import com.eslamshawky.hp.makeupartist.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MyService extends Fragment {

    Button addService;
    RecyclerView mRecyclerView;
    private RecyclerAdapterAddNewService recyclerAdapterAddNewService;
    private ArrayList<MyServiceModel> myServiceModels;
    String URLServiceByProvider = "http://live-artists.com/admin/api/show/service/6";
    public MyService() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_myservice, container, false);
        addService = view.findViewById(R.id.add_service);
        mRecyclerView = view.findViewById(R.id.recycler_show_services);
        myServiceModels = new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext(),
                LinearLayout.VERTICAL, true));
        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog1(view);
            }
        });
        addNewServiceByProvider();
        return view;
    }
    public void openDialog1(View view) {
       AddNewServices dialog = new AddNewServices(getActivity());
       dialog.show();
       dialog.getWindow().setLayout(1000,1000);
    }
    private void addNewServiceByProvider(){
        recyclerAdapterAddNewService = new RecyclerAdapterAddNewService(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLServiceByProvider, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    System.out.println("serviceee="+response);
                    JSONArray array = jsonObject.getJSONArray("services");
                    for (int i = 0 ; i<array.length();i++){
                        MyServiceModel model = new MyServiceModel();
                        JSONObject hits = array.getJSONObject(i);
                        String Name = hits.getString("name");
                        model.setName(Name);
                        String Price = hits.getString("price");
                        model.setPrice(Price);
                        String Type = hits.getJSONObject("servicetype").getString("title");
                        model.setType(Type);
                        myServiceModels.add(model);
                        recyclerAdapterAddNewService.setMyServiceModels(myServiceModels);
                        mRecyclerView.setAdapter(recyclerAdapterAddNewService);
                        recyclerAdapterAddNewService.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error="+error.networkResponse.statusCode);
                System.out.println("Error="+error.getMessage());            }
        });
        SingleTon.getInstance(getContext()).addToRequestQueue(stringRequest);

    }
}
