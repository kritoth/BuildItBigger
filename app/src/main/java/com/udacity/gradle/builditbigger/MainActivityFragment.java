package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.tiansirk.jokedisplayer.JokeDisplayerActivity;
import com.tiansirk.joketeller.JokeTeller;
import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;

import static com.tiansirk.jokedisplayer.JokeDisplayerActivity.INTENT_KEY_JOKE;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private FragmentMainBinding binding;
    private TextView mJokeView;
    private TextView mButton;
    private AdView mAdView;

    /** The interface that receives onClick messages */
    public interface JokeButtonListener{
        void onJokeButtonClicked();
    }
    JokeButtonListener listener;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mJokeView = binding.instructionsTextView;
        mAdView = binding.adView;
        mButton = binding.buttonTellMeJoke;

        setupClickListener();
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device." -DEPRECATED
        // Use RequestConfiguration builder to add test devices
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        List<String> testDevices = new ArrayList<>();
        testDevices.add(AdRequest.DEVICE_ID_EMULATOR);
        RequestConfiguration requestConfiguration
                = new RequestConfiguration.Builder()
                .setTestDeviceIds(testDevices)
                .build();
        MobileAds.setRequestConfiguration(requestConfiguration);

        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    /** When this fragment is attached to its host activity, ie {@link MainActivity} the listener interface is connected
     * If not then an error exception is thrown to notify the developer.
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof JokeButtonListener){
            listener = (JokeButtonListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement JokeButtonListener");
        }
    }

    /** Sets JokeButtonListener to the Button to communicate with host Activity*/
    private void setupClickListener(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tellJoke();
                //sendJoke();
                listener.onJokeButtonClicked();
            }
        });
    }

    /** When this fragment is detached from the host, the listeners is set to null, to decouple. */
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

/*    @Override
    public void onDataLoaded(String joke) {
        sendJoke(joke);
    }

    private void sendJoke(String joke){
        Intent activityIntent = new Intent(getContext(), JokeDisplayerActivity.class);
        activityIntent.putExtra(INTENT_KEY_JOKE, joke);
        startActivity(activityIntent);
    }*/


/*
    private String packageJokes(){
        JokeTeller jokeTeller = new JokeTeller();
        return jokeTeller.getJoke();
    }

    private void tellJoke(){
        mJokeView.setText(packageJokes());
    }*/

}
