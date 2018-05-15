package com.carsonskjerdal.dadjokes.Fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.carsonskjerdal.dadjokes.JokeFetcher;
import com.carsonskjerdal.dadjokes.R;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by Carson on 5/10/2018.
 * <p>
 * Feel free to use code just give credit please :)
 */
public class AboutFragment extends Fragment implements ScreenShotable, Button.OnClickListener {


    private View fragment_view;
    private Bitmap bitmap;
    private TextView text;


    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_random, container, false);
        //do search task and assign the joke to text view
        text = rootView.findViewById(R.id.textView);
        Button button = rootView.findViewById(R.id.jokeButton);
        button.setOnClickListener(this);
        /*
          When view is created, pre-load joke.
          Sometimes joke might be ready so we attempt to load joke right away.
          Eventually set a delay to wait for joke.
         */
        if (JokeFetcher.jokeText != null) {
            text.setText(JokeFetcher.jokeText);
            Log.e("Refresh", "Text should be set now properly");
        }
        //JokeFetcher.setupJoke();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.fragment_view = view.findViewById(R.id.container);
    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {

                Bitmap bitmap = Bitmap.createBitmap(fragment_view.getWidth(),
                        fragment_view.getHeight(), Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(bitmap);
                fragment_view.draw(canvas);
                AboutFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onClick(View view) {

        //do operation according to which modifier button is selected
        switch (view.getId()) {
            case R.id.jokeButton:
                //set text with current joke
                text.setText(JokeFetcher.jokeText);
                //call for new joke to set up next request
                JokeFetcher.setupJoke();
                break;

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is restarted.
        savedInstanceState.putString("MyJoke", (String) text.getText());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            String myString = savedInstanceState.getString("MyJoke");
            text.setText(myString);
            Log.e("Item", "" + myString);

        }
    }

}
