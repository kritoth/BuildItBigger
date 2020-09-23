package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tiansirk.jokedisplayer.JokeDisplayerActivity;

import static com.tiansirk.jokedisplayer.JokeDisplayerActivity.INTENT_KEY_JOKE;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.JokeButtonListener, EndpointsAsyncTask.AsyncRespone {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** This method is defined in the child fragment's interface to send data from it to here
     * and start the AsyncTask */
    @Override
    public void onJokeButtonClicked(){
        Log.d(TAG, "Button click received");
        loadJoke();
    }

    /* Starts the AsyncTask {@link EndpointsAsyncTask} */
    private void loadJoke(){
        if(isNetworkAvailable()) {
            Log.d(TAG, "Start AsyncTask");
            new EndpointsAsyncTask(this).execute();
        }
    }

    /* Checks if there is available network for connecting */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            Log.d(TAG, "There is available network");
            return true;
        }
        else {
            Log.d(TAG, "There is NO available network");
            return false;
        }
    }

    @Override
    public void onDataLoaded(String joke) {
        Log.d(TAG, "Data loaded from AsyncTask: " + joke);
        sendJoke(joke);
    }

    private void sendJoke(String joke){
        Intent activityIntent = new Intent(this, JokeDisplayerActivity.class);
        activityIntent.putExtra(INTENT_KEY_JOKE, joke);
        startActivity(activityIntent);
    }

}
