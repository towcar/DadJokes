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

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by Carson on 5/10/2018.
 * <p>
 * Feel free to use code just give credit please :)
 */
public class AboutFragment extends Fragment implements ScreenShotable {


    private View fragment_view;
    private Bitmap bitmap;
    Element adsElement = new Element();



    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return new AboutPage(this.getContext())
                .isRTL(false)
                //.setImage(R.mipmap.ic_launcher_main)
                .setDescription(getString(R.string.about))
                .addItem(new Element().setTitle("Version 1.1"))
                .addGroup("Connect with us")
                .addEmail("carson.skjerdal@gmail.com")
                .addWebsite("http://www.carsonskjerdal.com")
                .addPlayStore("https://play.google.com/store/apps/details?id=com.carsonskjerdal.dadjokes")
                .addGitHub("towcar")
                .create();
        //setContentView(aboutPage);
        //return inflater.inflate(R.layout.fragment_about, container, false);
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

    }

