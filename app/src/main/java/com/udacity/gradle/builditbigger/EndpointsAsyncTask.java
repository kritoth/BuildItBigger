package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    public static final String TAG = EndpointsAsyncTask.class.getSimpleName();

    public interface AsyncRespone { void onDataLoaded(String joke);}
    private AsyncRespone delegate = null;

    private static MyApi myApiService = null;

    public EndpointsAsyncTask(AsyncRespone context) {
        this.delegate = (AsyncRespone) context;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(new NetHttpTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    //.setApplicationName(context.getString(R.string.app_name))
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    //.setRootUrl("http://192.168.43.157:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.onDataLoaded(result);
    }
}