package com.example.appbanhangonline.Framment;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appbanhangonline.Adapter.Banner_Adapter;
import com.example.appbanhangonline.Model.Banner;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.Server.APIServer;
import com.example.appbanhangonline.Server.Dataserver;
import com.example.appbanhangonline.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    Banner_Adapter banner_adapter;
    Runnable runnable;
    Handler handler;
    int currnetItem=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);
        AnhXa();
            if(MainActivity.banners.size()==0) {
                GetData();
            }else {
                banner_adapter = new Banner_Adapter(getActivity(), MainActivity.banners);
                viewPager.setAdapter(banner_adapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currnetItem = viewPager.getCurrentItem();
                        currnetItem++;
                        if (currnetItem == viewPager.getAdapter().getCount()) {
                            currnetItem = 0;
                        }
                        viewPager.setCurrentItem(currnetItem, true);
                        handler.postDelayed(runnable, 5500);
                    }
                };
                handler.postDelayed(runnable, 5500);
            }
        return view;
    }

    private void GetData() {
        Dataserver dataserver =APIServer.getServer();
        Call<List<Banner>> callback =dataserver.GetDataBanner();
        callback.enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {

                MainActivity.banners = (ArrayList<Banner>) response.body();

                banner_adapter = new Banner_Adapter(getActivity(),MainActivity.banners);
                viewPager.setAdapter(banner_adapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currnetItem = viewPager.getCurrentItem();
                        currnetItem++;
                        if(currnetItem==viewPager.getAdapter().getCount()) {
                            currnetItem = 0;
                        }
                        viewPager.setCurrentItem(currnetItem,true);
                        handler.postDelayed(runnable,5500);
                    }
                };
                handler.postDelayed(runnable,5500);
            }
            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {

            }
        });

    }

    private void AnhXa(){
        viewPager       =view.findViewById(R.id.viewpagerfragmentbanner);
        circleIndicator =view.findViewById(R.id.circleIndiacatorfragmentbanner);
    }

}
