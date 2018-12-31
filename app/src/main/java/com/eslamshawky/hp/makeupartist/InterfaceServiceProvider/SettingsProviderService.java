package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.eslamshawky.hp.makeupartist.R;


public class SettingsProviderService extends Fragment {
    TextView share,back,editMyData;
    public SettingsProviderService() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);
        editMyData = view.findViewById(R.id.edit_dataProvider);
        share = view.findViewById(R.id.button_share);
        back = view.findViewById(R.id.button_back);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"تطبيق خبراء التجميل");
                intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/books/details/%D8%B9%D8%A7%D8%A6%D8%B6_%D8%A7%D9%84%D9%82%D8%B1%D9%86%D9%8A_%D8%AD%D8%AA%D9%89_%D8%AA%D9%83%D9%88%D9%86_%D8%A3%D8%B3%D8%B9%D8%AF_%D8%A7%D9%84%D9%86%D8%A7%D8%B3?id=com.eslamshawky.hp.azkar \n تطبيق اذكار متنوعة اكثر من رائع مقدم بطريقة سهلة وبسيطة ");
                startActivity(Intent.createChooser(intent,"إختر التطبيق"));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ServiceProvider.class);
                startActivity(intent);
            }
        });
        editMyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditDataProvider.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
