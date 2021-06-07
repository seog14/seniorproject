package com.example.ppapav3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import androidx.collection.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ppapav3.dto.AppResponse;

import org.json.JSONObject;

public class RQueueSingleton implements Runnable{
    private static RQueueSingleton instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context ctx;
    private patientMainPage patientMainPage;
    private doctorMainPage doctorMainPage;
    private int page;



// Instantiate the cache
    //   Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
    //    Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
    //   requestQueue = new RequestQueue(cache, network);

// Start the queue
    private Thread pollingThread;
    private RQueueSingleton(Context context) {
        ctx = context;


        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized RQueueSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new RQueueSingleton(context);
        }
        return instance;
    }



    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setPage(int page){
        this.page = page;
    }
    public void setPatientMainPage(patientMainPage activity) {
        this.patientMainPage = activity;
    }
    public void setDoctorMainPage(doctorMainPage activity){
        this.doctorMainPage = activity;
    }
    public void startPollingThread() {
        pollingThread = new Thread(this);
        pollingThread.start();
    }

    @Override
    public void run() {
        //
        while (true) {

            try {
                /*
                if doc --> call lambda for doctor tabl e
                if
                 */
                if(this.page == 1){
                    patientMainPage.pollCallBack();
                }
                else if (this.page == 0){
                    doctorMainPage.pollCallBack();


                }
                Thread.sleep(10000L);
                // reference to screen, exceute the screen

//                Intent intent = new Intent(createGood.this, doctorMainPage.class);
//                startActivity(intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}