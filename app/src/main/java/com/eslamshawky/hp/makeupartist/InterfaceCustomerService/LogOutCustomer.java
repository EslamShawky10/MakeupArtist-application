package com.eslamshawky.hp.makeupartist.InterfaceCustomerService;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eslamshawky.hp.makeupartist.R;


public class LogOutCustomer extends Fragment {

    public LogOutCustomer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_out2, container, false);
    }
}
