package com.eslamshawky.hp.makeupartist.ModelsServiceProvider;

import com.eslamshawky.hp.makeupartist.InterfaceServiceProvider.EditDataProvider;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String UrlEditProvider = "http://live-artists.com/admin/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(UrlEditProvider)
             .addConverterFactory(GsonConverterFactory.create()).build();
          }
        return retrofit;
    }
}
