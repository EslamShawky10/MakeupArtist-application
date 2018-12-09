package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eslamshawky.hp.makeupartist.R;


public class NotificationsByServiceProvider extends Fragment {

    public NotificationsByServiceProvider() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_notifications, container, false);
        return view;
    }


}
