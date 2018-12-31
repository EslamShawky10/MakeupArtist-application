package com.eslamshawky.hp.makeupartist.InterfaceCustomerService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eslamshawky.hp.makeupartist.R;

public class SettingsCustomer extends Fragment {
private TextView editData;
    public SettingsCustomer() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_settings_customer, container, false);
        editData = view.findViewById(R.id.text_edit_mydata);
        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditMyData.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
