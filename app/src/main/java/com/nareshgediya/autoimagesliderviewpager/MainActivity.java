package com.nareshgediya.autoimagesliderviewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewpager2);

        List<SlideItem> list = new ArrayList<>();
        list.add(new SlideItem(R.drawable.image1));
        list.add(new SlideItem(R.drawable.image2));
        list.add(new SlideItem(R.drawable.image3));
        list.add(new SlideItem(R.drawable.image4));
        list.add(new SlideItem(R.drawable.image3));

        viewPager2.setAdapter(new SliderItemAdapter(list,viewPager2));

        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);

        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull  View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.75f + r * 0.25f);

            }
        });



        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }
        });
    }

    private Runnable runnable = () -> viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
    @Override
    protected void onPause() {
        super.onPause();
    handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable,3000);
    }
}