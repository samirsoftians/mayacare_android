package com.felixtechlabs.mayacare.features.staticscreens;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.felixtechlabs.mayacare.R;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ftl on 6/7/17.
 */

public class OurFoundersAdapter extends RecyclerView.Adapter<OurFoundersAdapter.FounderViewHolder> {


    List<FounderDataModel> ourFoundersList = Collections.emptyList();

    public OurFoundersAdapter(List<FounderDataModel> ourFoundersList) {

        this.ourFoundersList = ourFoundersList;
    }


    @Override
    public FounderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ourFoundersList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_founder, parent, false);

        return new FounderViewHolder(ourFoundersList);

    }

    @Override
    public void onBindViewHolder(FounderViewHolder holder, int position) {
        holder.civFounderPhoto.setImageResource(ourFoundersList.get(position).getOurfoundersprofilrphoto());
        holder.txtFounderName.setText(ourFoundersList.get(position).getFounderName());
        holder.txtFoundersDesignation.setText(ourFoundersList.get(position).getFounderDesignation());
        holder.txtFoundersdDescription.setText(ourFoundersList.get(position).getFounderDescription());
    }

    public class FounderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_our_founders)
        CircleImageView civFounderPhoto;

        @BindView(R.id.txt_founders_name)
        TextView txtFounderName;

        @BindView(R.id.txt_founders_designation)
        TextView txtFoundersDesignation;

        @BindView(R.id.txt_founders_description)
        TextView txtFoundersdDescription;


        public FounderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }

    @Override


    public int getItemCount() {
        return ourFoundersList.size();
    }
}
