package com.felixtechlabs.mayacare.features.staticscreens;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.features.base.MCBaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by ftl on 6/7/17.
 */

public class OurFoundersActivity extends MCBaseActivity {

    @BindView(R.id.rev_our_founders)
    RecyclerView rvFounders;

    @BindView(R.id.rev_board_of_advisers)
    RecyclerView rvAdvisers;

    OurFoundersAdapter mOurFoundersAdapter;

    BoardOfAdvisersAdapter mBoardOfAdvisersAdapter;

    private ArrayList<FounderDataModel> mOurFoundersList;

    private ArrayList<FounderDataModel> mAdvisersList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.title_our_founders));
        enableHome();
        initRecyclerViews();
    }

    private void initRecyclerViews() {
        mOurFoundersList = new ArrayList<>();
        mAdvisersList = new ArrayList<>();

        mOurFoundersAdapter = new OurFoundersAdapter(mOurFoundersList);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(OurFoundersActivity.this,
                LinearLayoutManager.VERTICAL, false);
        rvFounders.setLayoutManager(LayoutManager);
        rvFounders.setAdapter(mOurFoundersAdapter);

        mBoardOfAdvisersAdapter = new BoardOfAdvisersAdapter(mAdvisersList, getApplication());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OurFoundersActivity.this,
                LinearLayoutManager.VERTICAL, false);
        rvAdvisers.setLayoutManager(linearLayoutManager);
        rvAdvisers.setAdapter(mBoardOfAdvisersAdapter);
        setFoundersData();
        setAdvisersData();
    }

    public void setFoundersData() {
        mOurFoundersList.clear();
        FounderDataModel founderDataModel = new FounderDataModel();
        founderDataModel.setOurfoundersprofilrphoto(R.drawable.founders_vidya_gokhale);
        founderDataModel.setfoundersname("Dr Vidya Gokhale");
        founderDataModel.setfounderDesignation("Director of Maya Care");
        founderDataModel.setfoundersdescription("After a 30-year career in medicine," +
                " Dr Vidya Gokhale retired as Professor of " +
                "Pharmacology from BJ Medical College, Pune.");
        mOurFoundersList.add(founderDataModel);


        founderDataModel = new FounderDataModel();
        founderDataModel.setOurfoundersprofilrphoto(R.drawable.founders_abhay_joshi);
        founderDataModel.setfoundersname("Abhay Joshi");
        founderDataModel.setfounderDesignation("Chife patron, Maya Care");
        founderDataModel.setfoundersdescription("Abhay Joshi has over " +
                "25 years of corporate managerial experience including eight" +
                " years in training school and University students");
        mOurFoundersList.add(founderDataModel);

        founderDataModel = new FounderDataModel();
        founderDataModel.setOurfoundersprofilrphoto(R.drawable.founders_manjiri_joshi);
        founderDataModel.setfoundersname("Manjiri Gokhale Joshi");
        founderDataModel.setfounderDesignation("CEO Maya Care");
        founderDataModel.setfoundersdescription("A training and management " +
                "specialist based in the UK, Manjiri has a Masters degree " +
                "in Mega-project Management");
        mOurFoundersList.add(founderDataModel);
        mOurFoundersAdapter.notifyDataSetChanged();
    }


    public void setAdvisersData() {
        FounderDataModel founderDataModel = new FounderDataModel();
        founderDataModel.setOurfoundersprofilrphoto(R.drawable.advisers_kiran_bedi);
        founderDataModel.setfoundersname("Dr Kiran Bedi");
        founderDataModel.setfounderDesignation("Former IPS oOficer");
        founderDataModel.setfoundersdescription("is India’s first and highest ranking " +
                "(retired in 2007) woman officer who joined the Indian Police Service in 1972. " +
                "She has worked with the United Nations as the Police Advisor");
        mAdvisersList.add(founderDataModel);

        founderDataModel = new FounderDataModel();
        founderDataModel.setOurfoundersprofilrphoto(R.drawable.advisers_vishakha_mulye);
        founderDataModel.setfoundersname("Vishakha  Mulye");
        founderDataModel.setfounderDesignation("MD & CEO of ICICI Venture");
        founderDataModel.setfoundersdescription("She has over 16 years of experience " +
                "in the areas of equity investing, corporate strategy, treasury & markets, " +
                "structured finance and corporate & project finance.");
        mAdvisersList.add(founderDataModel);

        founderDataModel = new FounderDataModel();
        founderDataModel.setOurfoundersprofilrphoto(R.drawable.advisers_anita_pratap);
        founderDataModel.setfoundersname("Anita Pratap ");
        founderDataModel.setfounderDesignation("Journalist");
        founderDataModel.setfoundersdescription("is an award-winning international" +
                " journalist, author and documentary film maker with stints as" +
                " the South Asia Bureau");
        mAdvisersList.add(founderDataModel);
        mBoardOfAdvisersAdapter.notifyDataSetChanged();
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_our_founders;
    }

    @Override
    public boolean isToolbarPresent() {
        return true;
    }
}


