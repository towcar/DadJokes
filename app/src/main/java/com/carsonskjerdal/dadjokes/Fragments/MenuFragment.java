package com.carsonskjerdal.dadjokes.Fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.carsonskjerdal.dadjokes.R;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by Carson on 5/10/2018.
 * <p>
 * Feel free to use code just give credit please :)
 */
public class MenuFragment extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";
    public static final String RANDOM = "Random";
    public static final String SEARCH = "Search";
    public static final String SMALL = "Small";
    public static final String MASTER = "Master";
    public static final String FAVORITE = "Favorite";
    public static final String ABOUT = "About";

    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;

    public static MenuFragment newInstance(int resId) {
        MenuFragment menuFragment = new MenuFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        menuFragment.setArguments(bundle);
        return menuFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mImageView = rootView.findViewById(R.id.image_content);
        mImageView.setClickable(true);
        mImageView.setFocusable(true);
        mImageView.setImageResource(res);
        return rootView;
    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                MenuFragment.this.bitmap = bitmap;
            }
        };

        thread.start();
    }

    //@Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

