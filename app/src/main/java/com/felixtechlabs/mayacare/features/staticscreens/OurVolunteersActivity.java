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
 * Created by ftl on 7/7/17.
 */

public class OurVolunteersActivity extends MCBaseActivity {

    RecyclerView volunteerRecyclerView;

    OurVolunteersAdapter volunteerAdapter;
    private List<OurVolunteersDataModel> volunteerList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Our Volunteers");
        enableHome();

        volunteerRecyclerView = (RecyclerView) findViewById(R.id.rev_our_volunteer);


        volunteerList = volunteerList();


        volunteerAdapter = new OurVolunteersAdapter(volunteerList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(OurVolunteersActivity.this, 2);
        volunteerRecyclerView.setLayoutManager(gridLayoutManager);
        volunteerRecyclerView.setAdapter(volunteerAdapter);

    }


    public List<OurVolunteersDataModel> volunteerList() {

        List<OurVolunteersDataModel> volunteerDataModels = new ArrayList<>();

        OurVolunteersDataModel volunteerDataModel = new OurVolunteersDataModel();
        volunteerDataModel.setVolunteerProfilePhoto(R.drawable.volunteer_supriya);
        volunteerDataModel.setVolunteerName("Mrs Supiya dutt");
        volunteerDataModel.setVolunteerDesignation("New Dilhi & NCR");

        volunteerDataModels.add(volunteerDataModel);


        volunteerDataModel = new OurVolunteersDataModel();
        volunteerDataModel.setVolunteerProfilePhoto(R.drawable.volunteer_karthik);
        volunteerDataModel.setVolunteerName("Karthik Ganapati");
        volunteerDataModel.setVolunteerDesignation("Chennai");

        volunteerDataModels.add(volunteerDataModel);


        volunteerDataModel = new OurVolunteersDataModel();
        volunteerDataModel.setVolunteerProfilePhoto(R.drawable.volunteer_swapna);
        volunteerDataModel.setVolunteerName("Sapana navathe");
        volunteerDataModel.setVolunteerDesignation("New Dilhi & NCR");

        volunteerDataModels.add(volunteerDataModel);


        volunteerDataModel = new OurVolunteersDataModel();
        volunteerDataModel.setVolunteerProfilePhoto(R.drawable.volunteer_sudhatai);
        volunteerDataModel.setVolunteerName("Sudha Ghokhale");
        volunteerDataModel.setVolunteerDesignation("New Dilhi & NCR");

        volunteerDataModels.add(volunteerDataModel);


        return volunteerDataModels;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_our_volunteers;
    }

    @Override
    public boolean isToolbarPresent() {
        return true;
    }
}
