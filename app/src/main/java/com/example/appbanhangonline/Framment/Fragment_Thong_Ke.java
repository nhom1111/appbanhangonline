package com.example.appbanhangonline.Framment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appbanhangonline.R;

public class Fragment_Thong_Ke  extends Fragment {
    View view;
    RelativeLayout layoutdongthu,layoutchitieu;
    TextView txtdoanhthu,txtchitieu;
    ListView lvdonhang;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thong_ke,container,false);
        return view;
    }
}
