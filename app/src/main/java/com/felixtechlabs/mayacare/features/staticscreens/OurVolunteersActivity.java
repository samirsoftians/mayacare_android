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
 * Created by ftl on 7/7/17.
 */

public class OurVolunteersActivity extends MCBaseActivity {

    @BindView(R.id.rev_our_volunteer)
    RecyclerView rvVolunteer;

    OurVolunteersAdapter mOurVolunteerAdapter;

    private ArrayList<OurVolunteersDataModel> mOurVolunteerList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.title_volunteers));
        enableHome();
        initRecyclerViews();
    }

    private void initRecyclerViews() {
        mOurVolunteerList = new ArrayList<>();
        mOurVolunteerAdapter = new OurVolunteersAdapter(mOurVolunteerList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(OurVolunteersActivity.this, 2);
        rvVolunteer.setLayoutManager(gridLayoutManager);
        rvVolunteer.setAdapter(mOurVolunteerAdapter);
        setVolunteerData();
    }


    public void setVolunteerData() {
        mOurVolunteerList.clear();
        OurVolunteersDataModel volunteerDataModel = new OurVolunteersDataModel();
        volunteerDataModel.setVolunteerProfilePhoto(R.drawable.volunteer_supriya);
        volunteerDataModel.setVolunteerName("Mrs Supiya dutt");
        volunteerDataModel.setVolunteerDesignation("New Dilhi & NCR");
        mOurVolunteerList.add(volunteerDataModel);

        volunteerDataModel = new OurVolunteersDataModel();
        volunteerDataModel.setVolunteerProfilePhoto(R.drawable.volunteer_karthik);
        volunteerDataModel.setVolunteerName("Karthik Ganapati");
        volunteerDataModel.setVolunteerDesignation("Chennai");
        mOurVolunteerList.add(volunteerDataModel);

        volunteerDataModel = new OurVolunteersDataModel();
        volunteerDataModel.setVolunteerProfilePhoto(R.drawable.volunteer_swapna);
        volunteerDataModel.setVolunteerName("Sapana navathe");
        volunteerDataModel.setVolunteerDesignation("New Dilhi & NCR");
        mOurVolunteerList.add(volunteerDataModel);

        volunteerDataModel = new OurVolunteersDataModel();
        volunteerDataModel.setVolunteerProfilePhoto(R.drawable.volunteer_sudhatai);
        volunteerDataModel.setVolunteerName("Sudha Ghokhale");
        volunteerDataModel.setVolunteerDesignation("New Dilhi & NCR");
        mOurVolunteerList.add(volunteerDataModel);

        mOurVolunteerAdapter.notifyDataSetChanged();
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
