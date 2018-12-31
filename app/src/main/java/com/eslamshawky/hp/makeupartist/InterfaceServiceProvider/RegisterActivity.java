package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.eslamshawky.hp.makeupartist.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    private EditText fullName, userName, password, email, phoneNumber, address, tradeName, about;
    private Button register;
    private ImageView ivGallary;
    String url = "http://live-artists.com/admin/api/add/provider/1";
    String FULLname, USERname, PASSWord, EMAil, PHONEnumber, ADDRess, TRADEname, ABOut;
    private GoogleApiClient mGoogleApiClient;
    private int PLACE_PICKER_REQUEST = 1;
    final int SELECT_PICTURE = 22131;
    private String selectedImagePath;
    File file;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private final String TAG = this.getClass().getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullName = (EditText) findViewById(R.id.edit_fullname);
        userName = (EditText) findViewById(R.id.edit_user_reg);
        password = (EditText) findViewById(R.id.edit_password_reg);
        email = (EditText) findViewById(R.id.edit_email);
        phoneNumber = (EditText) findViewById(R.id.edit_phone);
        address = (EditText) findViewById(R.id.edit_address);
        tradeName = (EditText) findViewById(R.id.edit_tradename);
        about = (EditText) findViewById(R.id.edit_about);
        register = (Button) findViewById(R.id.button_register);
        ivGallary = (ImageView) findViewById(R.id.imageGallery);
        ivGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
               .enableAutoManage(this,this)
                .build();


        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(RegisterActivity.this), PLACE_PICKER_REQUEST);

                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {

                    e.printStackTrace();

                };

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                network();
            }
        });
    }

    public void network() {
        FULLname = fullName.getText().toString();
        USERname = userName.getText().toString();
        PASSWord = password.getText().toString();
        EMAil = email.getText().toString();
        PHONEnumber = phoneNumber.getText().toString();
        ADDRess = address.getText().toString();
        TRADEname = tradeName.getText().toString();
        ABOut = about.getText().toString();
        validateInputs();
        Ion.with(RegisterActivity.this)
                .load("http://live-artists.com/admin/api/add/provider/1")
                .setMultipartParameter("fullName", fullName.getText().toString())
                .setMultipartParameter("username", userName.getText().toString())
                .setMultipartParameter("password", password.getText().toString())
                .setMultipartParameter("email", email.getText().toString())
                .setMultipartParameter("phone", phoneNumber.getText().toString())
                .setMultipartParameter("address", address.getText().toString())
                .setMultipartParameter("tradeName", tradeName.getText().toString())
                .setMultipartParameter("about", about.getText().toString())
                .setMultipartFile("image", file)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result != null) {
                            Intent intent = new Intent(RegisterActivity.this, ServiceProvider.class);
                            startActivity(intent);
                            Toast.makeText(RegisterActivity.this, "True..." + result, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error in Register=" + e.getMessage(), Toast.LENGTH_LONG).show();
                            System.out.println("error="+e.getMessage());

                        }
                    }
                });

    }
    private boolean validateInputs() {
        // if (ivGallary !=null && !ivGallary.)
        if (FULLname != null && !FULLname.isEmpty()) {

        } else {
            Toast.makeText(getBaseContext(), "error in fullname", Toast.LENGTH_LONG).show();
        }

        if (USERname != null && !USERname.isEmpty()) {

        } else {
            Toast.makeText(getBaseContext(), "error in username", Toast.LENGTH_LONG).show();
        }
        if (PASSWord != null && !PASSWord.isEmpty()) {

        } else {
            Toast.makeText(getBaseContext(), "error in password", Toast.LENGTH_LONG).show();
        }
        if (EMAil != null && !EMAil.isEmpty() && EMAil.contains("@")
                && EMAil.indexOf(".", EMAil.indexOf("@") + 1) != -1) {
        } else {
            Toast.makeText(getBaseContext(), "error in Email", Toast.LENGTH_LONG).show();
        }
        if (PHONEnumber != null && !PHONEnumber.isEmpty()) {

        } else {
            Toast.makeText(getBaseContext(), "error in Phone number", Toast.LENGTH_LONG).show();
        }
        if (ADDRess != null && !ADDRess.isEmpty()) {

        } else {
            Toast.makeText(getBaseContext(), "error in Address", Toast.LENGTH_LONG).show();
        }
        if (TRADEname != null && !TRADEname.isEmpty()) {

        } else {
            Toast.makeText(getBaseContext(), "error in Trade name", Toast.LENGTH_LONG).show();
        }
        if (ABOut != null && !ABOut.isEmpty()) {

        } else {
            Toast.makeText(getBaseContext(), "error in About", Toast.LENGTH_LONG).show();
        }
        return true;
    }




    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PLACE_PICKER_REQUEST) {
                Place place = PlacePicker.getPlace(this, data);
                StringBuilder stBuilder = new StringBuilder();
                String placename = String.format("%s", place.getName());
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                String Address = String.format("%s", place.getAddress());
                stBuilder.append("Name: ");
                stBuilder.append(placename);
                stBuilder.append("\n");
                stBuilder.append("Latitude: ");
                stBuilder.append(latitude);
                stBuilder.append("\n");
                stBuilder.append("Logitude: ");
                stBuilder.append(longitude);
                stBuilder.append("\n");
                stBuilder.append("Address: ");
                stBuilder.append(Address);
                address.setText(Address);

            } else if (requestCode == SELECT_PICTURE) {

                Uri uri=data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


    }
}

