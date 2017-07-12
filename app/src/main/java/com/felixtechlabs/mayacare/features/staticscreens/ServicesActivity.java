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

import butterknife.BindView;

/**
 * Created by ftl on 10/7/17.
 */

public class ServicesActivity extends MCBaseActivity {

    @BindView(R.id.rev_services)
    RecyclerView rvServices;

    ServicesAdapter mServicesAdapter;

    private ArrayList<ServicesDataModel> mServicesList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.title_services));
        enableHome();
        initRecyclerViews();
    }

    private void initRecyclerViews() {

        mServicesList = new ArrayList<>();
        mServicesAdapter = new ServicesAdapter(mServicesList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ServicesActivity.this, 2);
        rvServices.setLayoutManager(gridLayoutManager);
        rvServices.setAdapter(mServicesAdapter);

        setServicesData();

    }

    private void setServicesData() {
        mServicesList.clear();
        ServicesDataModel servicesDataModel = new ServicesDataModel();
        servicesDataModel.setServicesPhoto(R.drawable.services_img);
        servicesDataModel.setServicesName("Bank");
        mServicesList.add(servicesDataModel);

        servicesDataModel = new ServicesDataModel();
        servicesDataModel.setServicesPhoto(R.drawable.services_img);
        servicesDataModel.setServicesName("Domestic");
        mServicesList.add(servicesDataModel);

        servicesDataModel = new ServicesDataModel();
        servicesDataModel.setServicesPhoto(R.drawable.services_img);
        servicesDataModel.setServicesName("IT");
        mServicesList.add(servicesDataModel);

        servicesDataModel = new ServicesDataModel();
        servicesDataModel.setServicesPhoto(R.drawable.services_img);
        servicesDataModel.setServicesName("Medical");
        mServicesList.add(servicesDataModel);

        servicesDataModel = new ServicesDataModel();
        servicesDataModel.setServicesPhoto(R.drawable.services_img);
        servicesDataModel.setServicesName("Aide");
        mServicesList.add(servicesDataModel);

        servicesDataModel = new ServicesDataModel();
        servicesDataModel.setServicesPhoto(R.drawable.services_img);
        servicesDataModel.setServicesName("Utility");
        mServicesList.add(servicesDataModel);

        mServicesAdapter.notifyDataSetChanged();
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