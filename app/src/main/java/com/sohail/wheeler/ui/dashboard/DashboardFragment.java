package com.sohail.wheeler.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.sohail.wheeler.CheckOrderActivity;
import com.sohail.wheeler.MainActivity;
import com.sohail.wheeler.MapActivity;
import com.sohail.wheeler.R;
import com.sohail.wheeler.WebviewActivity;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    LinearLayout ly_profile, ly_map, ly_web, ly_check;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // binding
        ly_profile = root.findViewById(R.id.layoutProfile);
        ly_map = root.findViewById(R.id.layout_map);
        ly_web = root.findViewById(R.id.layout_webview);
        ly_check = root.findViewById(R.id.layout_checknpw);

        ly_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        ly_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });

        ly_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WebviewActivity.class);
                startActivity(intent);
            }
        });

        ly_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CheckOrderActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}