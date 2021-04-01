package com.sohail.wheeler.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sohail.wheeler.R;
import com.sohail.wheeler.adapter.SpecialViewAdapter;
import com.sohail.wheeler.adapter.VehicleAdapter;
import com.sohail.wheeler.api.VehicleAPI;
import com.sohail.wheeler.modal.ExploreViewModal;
import com.sohail.wheeler.modal.SpecialViewModal;
import com.sohail.wheeler.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    View view;
    private RecyclerView recycler_offer, recycler_explore;
    private HomeViewModel homeViewModel;
    List<ExploreViewModal> products = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recycler_offer = root.findViewById(R.id.offersView);
        recycler_explore = root.findViewById(R.id.exploreView);
        getSpecialView();
        getExploreVehicle();
        return root;
    }


    public void getSpecialView() {
        VehicleAPI retrofitProductAPI = Url.getInstance().create(VehicleAPI.class);
        Call<List<SpecialViewModal>> ProductsCall = retrofitProductAPI.getSpecialVehicle();
        ProductsCall.enqueue(new Callback<List<SpecialViewModal>>() {
            @Override
            public void onResponse(Call<List<SpecialViewModal>> call, Response<List<SpecialViewModal>> response) {
                SpecialViewAdapter recyclerviewAdapter = new SpecialViewAdapter(response.body(), getActivity());

                // elevation design
                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false);
                recycler_offer.setLayoutManager(horizontalLayoutManager);
                recycler_offer.setHasFixedSize(true);
                recycler_offer.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SpecialViewModal>> call, Throwable t) {

            }
        });

    }

    public void getExploreVehicle() {
        VehicleAPI retrofitProductAPI = Url.getInstance().create(VehicleAPI.class);
        Call<List<ExploreViewModal>> ProductsCall = retrofitProductAPI.getallVehicle();
        ProductsCall.enqueue(new Callback<List<ExploreViewModal>>() {
            @Override
            public void onResponse(Call<List<ExploreViewModal>> call, Response<List<ExploreViewModal>> response) {
                VehicleAdapter recyclerviewAdapter = new VehicleAdapter(response.body(), getActivity());
                RecyclerView.LayoutManager mlayoutManager = new GridLayoutManager(getActivity(), 1);
                recycler_explore.setLayoutManager(mlayoutManager);
                recycler_explore.setHasFixedSize(true);
                recycler_explore.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ExploreViewModal>> call, Throwable t) {

            }
        });

    }
}
