package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.eslamshawky.hp.makeupartist.AdaptersServiceProvider.RecyclerAdapterImages;
import com.eslamshawky.hp.makeupartist.AdaptersServiceProvider.RecyclerAdapterViedos;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.ImageModel;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.SingleTon;
import com.eslamshawky.hp.makeupartist.ModelsServiceProvider.VideoModel;
import com.eslamshawky.hp.makeupartist.R;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class Profile extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2;
    private Button addImage, addViedo;
    private RecyclerView mRecyclerView, mRecyclerViewViedo;
    private RecyclerAdapterImages mRecyclerAdapterImages;
    private ArrayList<ImageModel> imageModels;
    private RecyclerAdapterViedos mRecyclerAdapterViedos;
    private ArrayList<VideoModel> videoModels;
    String URL = "http://live-artists.com/admin/api/show/images/23";
    String URLVideo = "http://live-artists.com/admin/api/show/videos/52";
    final int ADD_PICTURE = 22131;
    String UrlAddImage = "http://live-artists.com/admin/api/add/images";
    File file;
    private static final int ADD_VIEDO = 300;
    String UrlAddViedo = "http://live-artists.com/admin/api/add/videos";

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        imageModels = new ArrayList<>();
        videoModels = new ArrayList<>();

        mRecyclerView = view.findViewById(R.id.recycler_image);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext(), LinearLayout.HORIZONTAL, true));

        mRecyclerViewViedo = view.findViewById(R.id.recycler_viedo);
        mRecyclerViewViedo.setHasFixedSize(true);
        mRecyclerViewViedo.setLayoutManager(new LinearLayoutManager(inflater.getContext(), LinearLayout.HORIZONTAL, true));
        addViedo = view.findViewById(R.id.button_add_viedo);
        addImage = view.findViewById(R.id.button_add_image);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), ADD_PICTURE);

            }
        });

        addViedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("viedo/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Viedo"), ADD_VIEDO);
            }
        });

        parseJeson();
        parseJesonViedo();
        return view;
    }

    private void parseJeson() {
        mRecyclerAdapterImages = new RecyclerAdapterImages(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject object = new JSONObject(response);
                    JSONObject imagesob = object.getJSONObject("images");
                    JSONArray jsonArray = imagesob.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        ImageModel imageModel = new ImageModel();
                        JSONObject hit = jsonArray.getJSONObject(i);
                        String imageUrl = hit.getString("url");
                        imageModel.setUrl("http://live-artists.com/admin" + imageUrl);
                        imageModels.add(imageModel);
                        mRecyclerAdapterImages.setImageModels(imageModels);
                        mRecyclerView.setAdapter(mRecyclerAdapterImages);
                        mRecyclerAdapterImages.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        SingleTon.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void parseJesonViedo() {
        mRecyclerAdapterViedos = new RecyclerAdapterViedos(getActivity());
        StringRequest request = new StringRequest(Request.Method.GET, URLVideo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("videos");
                    JSONArray array = jsonObject1.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        VideoModel videoModel = new VideoModel();
                        JSONObject hits = array.getJSONObject(i);
                        String viedoUrl = hits.getString("url");
                        videoModel.setUrlVideo("http://live-artists.com/admin/" + viedoUrl);
                        videoModels.add(videoModel);
                        mRecyclerAdapterViedos.setVideoModels(videoModels);
                        mRecyclerViewViedo.setAdapter(mRecyclerAdapterViedos);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        SingleTon.getInstance(getContext()).addToRequestQueue(request);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.READ_CONTACTS)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_CONTACTS},
                            MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                // Permission already granted ... This is where you can continue your further business logic...
                }
            if (requestCode == ADD_PICTURE) {
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), uri);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                    String time = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
                    String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + "MyFirstApp/";
                    File pathf = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES);

                    if (!pathf.exists()) {
                        if (!pathf.mkdirs()) {

                            Toast.makeText(getActivity(), "faild create", Toast.LENGTH_SHORT).show();
                        }
                        pathf.mkdirs();
                    }
                    String fullName = path + "mylog" + "IMG_" + time + ".jpg";
                    file = new File(Objects.requireNonNull(FilePath.getPath(getActivity(), uri)));
                //  file = new File(Environment.getExternalStorageDirectory(0).getAbsolutePath() + "/" + "liveartist" + "IMG_" + time + ".jpg");
                    System.out.println("file=" + file);
                    addImageFastNetwork(file);
                    addImage(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == ADD_VIEDO) {
                Uri uri1 = data.getData();
            }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    public void addImage(File file) {
        Ion.with(getContext()).load(UrlAddImage)
                .setMultipartFile("url", file)
                .setMultipartParameter("provider_id", "23")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        System.out.println("rrrr=" + result);
                        System.out.println("exception=" + e.getMessage());
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }

    public void addImageFastNetwork(File file) {
        System.out.println("filepath=" + file);
        AndroidNetworking.upload(UrlAddImage)
                .addMultipartFile("url", file)
                .addMultipartParameter("provider_id", "23")
                .setPriority(Priority.HIGH)
                .build().setUploadProgressListener(new UploadProgressListener() {
            @Override
            public void onProgress(long bytesUploaded, long totalBytes) {

            }
        }).getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                // do anything with response
                System.out.println("respo" + response);
                imageModels.clear();
                parseJeson();
            }

            @Override
            public void onError(ANError error) {
                // handle error
                System.out.println("errorfile=" + error.getErrorDetail());
                System.out.println("errorfilebod=" + error.getErrorBody());
                System.out.println("errorcode=" + error.getErrorCode());

            }
        });
    }
}
