package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.eslamshawky.hp.makeupartist.AdaptersServiceProvider.RecyclerAdapterDisplayOrder;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.OrdersModel;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.SingleTon;
import com.eslamshawky.hp.makeupartist.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class Orders extends Fragment implements RecyclerAdapterDisplayOrder.OnItemClickListener {

    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String TOTALPRICE = "totalprice";
    public static final String SERVICES = "services";
    private RecyclerView mRecyclerView;
    private RecyclerAdapterDisplayOrder mRecyclerAdapterDisplayOrder;
    private ArrayList<OrdersModel> ordersModels;
    private String URLallOrders = "http://live-artists.com/admin/api/get/orders";

    public Orders() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_orders, container, false);
        mRecyclerView = view.findViewById(R.id.RecyclerListOrder);
        ordersModels = new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext(),LinearLayout.VERTICAL,true));
        addNewServiceByProvider();
        return view;
    }
    private void addNewServiceByProvider(){
        mRecyclerAdapterDisplayOrder = new RecyclerAdapterDisplayOrder(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLallOrders, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("orders");
                    for (int i = 0 ; i<array.length();i++){
                        OrdersModel model = new OrdersModel();
                        JSONObject hits = array.getJSONObject(i);
                        String Name = hits.getString("services");
                        model.setName(Name);
                        String Date = hits.getString("created_at");
                        model.setDate(Date);
                        String TotalePrice = hits.getString("total");
                        model.setTotalPrice(TotalePrice);
                        String Address = hits.getString("address");
                        model.setAddress(Address);
                        String ListOfServies = hits.getString("services");
                        model.setListOfServices(ListOfServies);
                        ordersModels.add(model);
                        mRecyclerAdapterDisplayOrder.setOrdersModels(ordersModels);
                        mRecyclerView.setAdapter(mRecyclerAdapterDisplayOrder);
                        mRecyclerAdapterDisplayOrder.setOnItemClickLestener(Orders.this);
                        mRecyclerAdapterDisplayOrder.notifyDataSetChanged();

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

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(),DetailsOrders.class);
        OrdersModel clickedItem = ordersModels.get(position);
        intent.putExtra(NAME,clickedItem.getName());
        intent.putExtra(ADDRESS,clickedItem.getAddress());
        intent.putExtra(TOTALPRICE,clickedItem.getTotalPrice());
        intent.putExtra(SERVICES,clickedItem.getListOfServices());
        startActivity(intent);
    }
}
