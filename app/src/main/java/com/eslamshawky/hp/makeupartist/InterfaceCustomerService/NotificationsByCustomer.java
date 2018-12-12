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
 * {@link NotificationsByCustomer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotificationsByCustomer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationsByCustomer extends Fragment {

    public NotificationsByCustomer() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications_by_customer, container, false);
    }

}
