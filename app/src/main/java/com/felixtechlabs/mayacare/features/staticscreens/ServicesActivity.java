package com.felixtechlabs.mayacare.features.staticscreens;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.features.base.MCBaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ftl on 10/7/17.
 */

public class ServicesActivity extends MCBaseActivity {

    RecyclerView servicesRecyclerView;
    ServicesAdapter servicesAdapter;
    private List<ServicesDataModel> servicesAdapterList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Services");
        enableHome();


        servicesRecyclerView = (RecyclerView) findViewById(R.id.rev_services);
        servicesAdapterList = servicesAdapterList();

        servicesAdapter = new ServicesAdapter(servicesAdapterList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ServicesActivity.this, 2);
        servicesRecyclerView.setLayoutManager(gridLayoutManager);
        servicesRecyclerView.setAdapter(servicesAdapter);



    }

    private List<ServicesDataModel> servicesAdapterList() {

        List<ServicesDataModel> servicesDataModels = new ArrayList<>();

        ServicesDataModel servicesDataModel = new ServicesDataModel();
        servicesDataModel.setServicesPhoto(R.drawable.services_img);
        servicesDataModel.setServicesName("Bank");
        servicesDataModels.add(servicesDataModel);

        servicesDataModel = new ServicesDataModel();
        servicesDataModel.setServicesPhoto(R.drawable.services_img);
        servicesDataModel.setServicesName("Domestic");
        servicesDataModels.add(servicesDataModel);

        servicesDataModel = new ServicesDataModel();
        servicesDataModel.setServicesPhoto(R.drawable.services_img);
        servicesDataModel.setServicesName("IT");
        servicesDataModels.add(servicesDataModel);

        servicesDataModel = new ServicesDataModel();
        servicesDataModel.setServicesPhoto(R.drawable.services_img);
        servicesDataModel.setServicesName("Medical");
        servicesDataModels.add(servicesDataModel);

        servicesDataModel = new ServicesDataModel();
        servicesDataModel.setServicesPhoto(R.drawable.services_img);
        servicesDataModel.setServicesName("Aide");
        servicesDataModels.add(servicesDataModel);

        servicesDataModel = new ServicesDataModel();
        servicesDataModel.setServicesPhoto(R.drawable.services_img);
        servicesDataModel.setServicesName("Utility");
        servicesDataModels.add(servicesDataModel);


        return servicesDataModels;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_servises;
    }

    @Override
    public boolean isToolbarPresent() {
        return true;
    }
}
