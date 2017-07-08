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
 * Created by ftl on 7/7/17.
 */

public class OurVolunteersAdapter extends RecyclerView.Adapter<OurVolunteersAdapter.VolunteerViewHolder> {


    List<OurVolunteersDataModel> volunteerList = Collections.emptyList();

    public OurVolunteersAdapter(List<OurVolunteersDataModel> volunteerList) {

        this.volunteerList = volunteerList;
    }


    @Override
    public VolunteerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View volunteerList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_volunteers, parent, false);

        return new OurVolunteersAdapter.VolunteerViewHolder(volunteerList);

    }

    @Override
    public void onBindViewHolder(VolunteerViewHolder holder, int position) {

        holder.civvolunteerPhoto.setImageResource(volunteerList.get(position).getVolunteerProfilePhoto());
        holder.txtvolunteerName.setText(volunteerList.get(position).getVolunteerName());
        holder.txtvolunteersDesignation.setText(volunteerList.get(position).getVolunteerDesignation());

    }

    public class VolunteerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_our_volunters)
        CircleImageView civvolunteerPhoto;

        @BindView(R.id.txt_volunteer_name)
        TextView txtvolunteerName;

        @BindView(R.id.txt_volunteer_designation)
        TextView txtvolunteersDesignation;


        public VolunteerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);


         }
    }

    @Override
    public int getItemCount() {
        return volunteerList.size();
    }
}
