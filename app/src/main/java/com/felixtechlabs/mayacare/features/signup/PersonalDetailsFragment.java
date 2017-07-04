package com.felixtechlabs.mayacare.features.signup;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.glide.GlideApp;
import com.felixtechlabs.mayacare.util.MCUtility;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Fragment accepting user's personal details while signUp
 * Created by rohan on 28/5/17.
 * TODO: Remove hardcoded error messages
 */

public class PersonalDetailsFragment extends SignUpBaseFragment {

    @BindView(R.id.civ_profile_photo)
    CircleImageView civProfilePhoto;

    @BindView(R.id.edt_full_name)
    EditText edtFullName;

    @BindView(R.id.il_full_name)
    TextInputLayout ilFullName;

    @BindView(R.id.edt_mobile_number)
    EditText edtMobileNumber;

    @BindView(R.id.il_mobile_number)
    TextInputLayout ilMobileNumber;

    @BindView(R.id.rgp_gender)
    RadioGroup rgpGender;

    @BindView(R.id.edt_dob)
    EditText edtDob;

    @BindView(R.id.il_dob)
    TextInputLayout ilDob;

    private Calendar mCalendar;

    private Uri profilePictureUri;

    @Override
    protected Fragment getFragment() {
        return this;
    }

    @Override
    protected void start() {
        mCalendar = Calendar.getInstance();
    }


    @OnClick(R.id.civ_profile_photo)
    void onClickAddPhoto() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(getActivity(), this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_personal_details;
    }

    @Override
    protected boolean isFormValid() {
        String name = edtFullName.getText().toString().trim();
        if (name.isEmpty()) {
            ilFullName.setError("Enter full name");
            requestFocus(edtFullName);
            return false;
        } else {
            ilFullName.setErrorEnabled(false);
        }

        if (rgpGender.getCheckedRadioButtonId() == -1) {
            MCUtility.showToast(getActivity(), "Select Gender");
            return false;
        }

        String dateOfBirth = edtDob.getText().toString().trim();
        if (dateOfBirth.isEmpty()) {
            ilDob.setError("Enter date of birth");
            requestFocus(edtDob);
            return false;
        } else {
            ilDob.setErrorEnabled(false);
        }


        String mobile = edtMobileNumber.getText().toString().trim();
        if (mobile.isEmpty()) {
            ilMobileNumber.setError("Enter mobile number");
            requestFocus(edtMobileNumber);
            return false;
        } else {
            ilMobileNumber.setErrorEnabled(false);
        }

        mSignUpPresenter.setProfilePictureUri(profilePictureUri);
        mSignUpPresenter.setPersonalDetails(name, MCUtility.getCheckedRadioText(rgpGender),
                dateOfBirth, mobile);

        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                profilePictureUri = result.getUri();
                GlideApp.with(getActivity()).load(profilePictureUri).into(civProfilePhoto);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                MCUtility.showToast(getActivity(), error.getMessage());
            }
        }
    }

    @OnClick(R.id.edt_dob)
    void onClickDOB() {
        new DatePickerDialog(getActivity(), mOnDateSetListener, mCalendar
                .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, month);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setDate();
        }
    };

    private void setDate() {
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        edtDob.setText(sdf.format(mCalendar.getTime()));
    }

}
