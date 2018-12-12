package com.eslamshawky.hp.makeupartist.InterfaceCustomerService;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eslamshawky.hp.makeupartist.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsCustomer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsCustomer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsCustomer extends Fragment {

    public SettingsCustomer() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings_customer, container, false);
    }

}
