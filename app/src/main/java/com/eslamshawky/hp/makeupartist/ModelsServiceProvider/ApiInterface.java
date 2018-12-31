package com.eslamshawky.hp.makeupartist.ModelsServiceProvider;

import com.eslamshawky.hp.makeupartist.InterfaceServiceProvider.EditDataProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("api/edit/provider/106/2")
     Call<List<EditProviderModel>> getDataProvider();


}
