package com.eslamshawky.hp.makeupartist.ModelsServiceProvider;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingleTon {
    private static SingleTon mSingleTon;
    private RequestQueue mRequestQueue;
    private  static Context mContext;

    private SingleTon (Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();
    }
    public RequestQueue getRequestQueue(){

        if(mRequestQueue==null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }
    public static synchronized SingleTon getInstance (Context context){
        if (mSingleTon == null){
            mSingleTon=new SingleTon(context);
        }
        return mSingleTon;
    }
    public  <T> void addToRequestQueue (Request<T> request){
        mRequestQueue.add(request);
    }
}
