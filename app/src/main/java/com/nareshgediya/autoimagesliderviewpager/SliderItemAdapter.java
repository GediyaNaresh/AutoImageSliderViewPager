package com.nareshgediya.autoimagesliderviewpager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SliderItemAdapter extends  RecyclerView.Adapter<SliderItemAdapter.MyViewHolder>{

    List<SlideItem> list;
    ViewPager2 viewPager2;

    public SliderItemAdapter(List<SlideItem> list, ViewPager2 viewPager2) {
        this.list = list;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  SliderItemAdapter.MyViewHolder holder, int position) {
        holder.setSlider(list.get(position));
        if (position == list.size()-2){
            viewPager2.post(holder.runnable);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        RoundedImageView roundedImageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            roundedImageView = itemView.findViewById(R.id.slideimage);
        }
        void setSlider(SlideItem sliderItem){
            roundedImageView.setImageResource(sliderItem.getImage());
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                list.addAll(list);
                notifyDataSetChanged();
            }
        };
    }
}
