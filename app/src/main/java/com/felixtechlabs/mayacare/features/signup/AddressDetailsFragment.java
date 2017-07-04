package com.felixtechlabs.mayacare.features.signup;

import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import com.felixtechlabs.mayacare.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

/**
 * Fragment accepting user's address details while signUp
 * Created by rohan on 28/5/17.
 */

public class AddressDetailsFragment extends SignUpBaseFragment {

    @BindView(R.id.il_residential_address)
    TextInputLayout ilResidentialAddress;

    @BindView(R.id.edt_residential_address)
    EditText edtResidentialAddress;

    @BindView(R.id.edt_pin_code)
    EditText edtPinCode;

    @BindView(R.id.il_pin_code)
    TextInputLayout ilPinCode;

    @BindView(R.id.il_city)
    TextInputLayout ilCity;

    @BindView(R.id.edt_city)
    EditText edtCity;

    @BindView(R.id.il_telephone)
    TextInputLayout ilAlternateMobile;

    @BindView(R.id.edt_telephone)
    EditText edtAlternateMobile;

    private ArrayList<EditText> allFields;

    private HashMap<EditText, String> fieldsMessages;

    private ArrayList<TextInputLayout> allInputLayouts;

    @Override
    protected Fragment getFragment() {
        return this;
    }

    @Override
    protected void start() {
        initCollections();
    }

    private void initCollections() {
        allFields = new ArrayList<>();
        allFields.add(edtResidentialAddress);
        allFields.add(edtPinCode);
        allFields.add(edtCity);
        allFields.add(edtAlternateMobile);

        allInputLayouts = new ArrayList<>();
        allInputLayouts.add(ilResidentialAddress);
        allInputLayouts.add(ilPinCode);
        allInputLayouts.add(ilCity);
        allInputLayouts.add(ilAlternateMobile);

        fieldsMessages = new HashMap<>();
        fieldsMessages.put(edtResidentialAddress, "Enter address");
        fieldsMessages.put(edtPinCode, "Enter PIN Code");
        fieldsMessages.put(edtCity, "Enter city name");
        fieldsMessages.put(edtAlternateMobile, "Enter alternate mobile");
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_address_details;
    }

    @Override
    protected boolean isFormValid() {
        for (EditText editText : allFields) {
            if (editText.getText().toString().trim().isEmpty()) {
                allInputLayouts.get(allFields.indexOf(editText))
                        .setError(fieldsMessages.get(editText));
                requestFocus(editText);
                return false;
            } else {
                allInputLayouts.get(allFields.indexOf(editText))
                        .setErrorEnabled(false);
            }
        }


        mSignUpPresenter.setAddressDetails(edtResidentialAddress.getText().toString().trim(),
                edtPinCode.getText().toString().trim(),
                edtCity.getText().toString().trim(),
                edtAlternateMobile.getText().toString().trim());

        return true;
    }
}

