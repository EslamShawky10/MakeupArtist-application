package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.ApiClient;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.ApiInterface;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.EditProviderModel;
import com.eslamshawky.hp.makeupartist.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDataProvider extends AppCompatActivity {

    private EditText fullName,email,password,tradeName,address,about;
    private ImageView selecteImage;
    private Button save;
    private ApiInterface apiInterface;
    private List<EditProviderModel> editProviderModels;
    private String fullname,eMail,passWord, tradename, Address,About;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_provider);
        fullName = (EditText)findViewById(R.id.editFullNameProvider);
        email = (EditText)findViewById(R.id.editEmailProvider);
        password = (EditText)findViewById(R.id.editPasswordProvider);
        tradeName = (EditText)findViewById(R.id.editTradeNameProvider);
        address = (EditText)findViewById(R.id.editAddressProvider);
        about = (EditText)findViewById(R.id.editAboutProvider);
        selecteImage = (ImageView)findViewById(R.id.editImageProvider);
        save = (Button)findViewById(R.id.save_Data_Provider);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullname = fullName.getText().toString();
                eMail = email.getText().toString();
                passWord = password.getText().toString();
                tradename = tradeName.getText().toString();
                Address = address.getText().toString();
                About = about.getText().toString();

                editData(fullname,eMail,passWord,tradename,Address,About);
            }
        });
    }

    private void editData(String fullname, final String email, String password,
                          String tradename, String address, String about){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<EditProviderModel>> call = apiInterface.getDataProvider();
        EditProviderModel model = new EditProviderModel();
        model.setFullName(fullname);
        model.setEmail(email);
        model.setPassword(password);
        model.setTradeName(tradename);
        model.setAddress(address);
        model.setAbout(about);
        call.enqueue(new Callback<List<EditProviderModel>>() {
            @Override
            public void onResponse(Call<List<EditProviderModel>> call, Response<List<EditProviderModel>> response) {
                editProviderModels = response.body();
            }

            @Override
            public void onFailure(Call<List<EditProviderModel>> call, Throwable t) {

            }
        });
    }
}
