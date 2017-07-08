package com.felixtechlabs.mayacare.features.dashboard;

import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.glide.GlideApp;
import com.felixtechlabs.mayacare.models.Consumer;
import com.felixtechlabs.mayacare.util.MCUtility;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ftl on 30/6/17.
 */

public class ProfileFragmant extends DashboardBaseFragment {

    @BindView(R.id.civ_profile_photo)
    CircleImageView civProfilePhoto;

    @BindView(R.id.txt_full_name)
    TextView txtFullName;

    @BindView(R.id.edt_mobile)
    EditText edtMobile;

    @BindView(R.id.edt_address)
    EditText edtAddress;

    @BindView(R.id.edt_email)
    EditText edtEmail;

    @Override
    protected Fragment getFragment() {
        return this;
    }

    @Override
    protected void start() {
        loadUserData();
    }

    private void loadUserData() {
        Consumer consumer = MCUtility.getConsumer(getActivity());
        GlideApp.with(getActivity()).load(consumer.getProfilePictureUrl())
                .placeholder(R.drawable.ic_add_photo).into(civProfilePhoto);
        txtFullName.setText(consumer.getFullName());
        edtMobile.setText(consumer.getMobileNumber());
        edtEmail.setText(consumer.getEmail());
        edtAddress.setText(consumer.getAddress());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_profile;
    }
}
