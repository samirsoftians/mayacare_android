package com.felixtechlabs.mayacare.features.staticscreens;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.felixtechlabs.mayacare.R;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ftl on 10/7/17.
 */

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {


    List<ServicesDataModel> servicesModelList = Collections.emptyList();

    public ServicesAdapter(List<ServicesDataModel> servicesModelList) {

        this.servicesModelList = servicesModelList;
    }


    @Override
    public ServicesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ServicesViewHolder = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_services, parent, false);

        return new ServicesAdapter.ServicesViewHolder(ServicesViewHolder);

    }

    @Override
    public void onBindViewHolder(ServicesViewHolder holder, int position) {


        holder.imvServicesPhoto.setImageResource(servicesModelList.get(position).getServicesPhoto());
        holder.txtServicesName.setText(servicesModelList.get(position).getServicesName());

    }

    public class ServicesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imv_services_photo)
        ImageView imvServicesPhoto;

        @BindView(R.id.txt_services_name)
        TextView txtServicesName;

        public ServicesViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemCount() {
        return servicesModelList.size();
    }


}
