package com.app.dermcop.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.app.dermcop.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private Button btnNearYou,uvIndex,helpBot;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);



        btnNearYou = root.findViewById(R.id.btnNearU);
        uvIndex = root.findViewById(R.id.btnUVIndex);
        helpBot = root.findViewById(R.id.btnHelpBot);
        
        btnNearYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        uvIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),UVModel.class);
                startActivity(intent);
            }
        });


        helpBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Implementation still in progress", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}