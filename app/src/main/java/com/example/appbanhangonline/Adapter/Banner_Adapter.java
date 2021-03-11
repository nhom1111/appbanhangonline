package com.example.appbanhangonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.appbanhangonline.Model.Banner;
import com.example.appbanhangonline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Banner_Adapter extends PagerAdapter {
    Context context;
    ArrayList<Banner> arraylistBanner;

    public Banner_Adapter(Context context, ArrayList<Banner> arraylistBanner) {
        this.context = context;
        this.arraylistBanner = arraylistBanner;
    }

    @Override
    public int getCount() {
        return arraylistBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.dong_banner,null);
        //anh xa

        ImageView   imgbackgroundbanner     = view.findViewById(R.id.imageviewbackgroundbanner);
        TextView    txtmotabanner              = view.findViewById(R.id.textViewmotabanner);

        Picasso.with(context).load(arraylistBanner.get(position).getAnhQC()).into(imgbackgroundbanner);
        txtmotabanner.setText(arraylistBanner.get(position).getMoTa());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
