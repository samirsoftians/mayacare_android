package com.felixtechlabs.mayacare.features.request;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.features.base.MCBaseActivity;
import com.felixtechlabs.mayacare.models.Consumer;
import com.felixtechlabs.mayacare.models.Request;
import com.felixtechlabs.mayacare.util.MCConstants;
import com.felixtechlabs.mayacare.util.MCDialogUtility;
import com.felixtechlabs.mayacare.util.MCUtility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by rohan on 7/7/17.
 */

public class NewRequestActivity extends MCBaseActivity {

    @BindView(R.id.edt_full_name)
    EditText edtFullName;

    @BindView(R.id.il_full_name)
    TextInputLayout ilFullName;

    @BindView(R.id.edt_mobile_number)
    EditText edtMobileNumber;

    @BindView(R.id.il_mobile_number)
    TextInputLayout ilMobileNumber;

    @BindView(R.id.edt_date)
    EditText edtDate;

    @BindView(R.id.il_date)
    TextInputLayout ilDate;

    @BindView(R.id.edt_time)
    EditText edtTime;

    @BindView(R.id.il_time)
    TextInputLayout ilTime;

    @BindView(R.id.il_residential_address)
    TextInputLayout ilResidentialAddress;

    @BindView(R.id.edt_residential_address)
    EditText edtResidentialAddress;

    @BindView(R.id.edt_email)
    EditText edtEmail;

    @BindView(R.id.il_email)
    TextInputLayout ilEmail;

    @BindView(R.id.edt_services)
    EditText edtServices;

    @BindView(R.id.edt_comments)
    EditText edtComments;

    @BindView(R.id.il_services)
    TextInputLayout ilServices;


    private Calendar mCalendar;

    private ArrayList<String> services;

    private ArrayList<String> selectedServices;

    private Integer[] selectedIndices;

    private String servicesString = "";

    private ArrayList<EditText> allFields;

    private HashMap<EditText, String> fieldsMessages;

    private ArrayList<TextInputLayout> allInputLayouts;

    private Request mRequest;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Request A Volunteer");
        mRequest = (Request) getIntent().getSerializableExtra(MCConstants.KEY_REQUEST_OBJECT);
        enableHome();
        init();
        loadConsumerDetails();
    }

    private void init() {
        mCalendar = Calendar.getInstance();
        services = new ArrayList<>();
        services.addAll(Arrays.asList(getResources().getStringArray(R.array.array_services)));
        allFields = new ArrayList<>();
        allFields.add(edtFullName);
        allFields.add(edtMobileNumber);
        allFields.add(edtEmail);
        allFields.add(edtResidentialAddress);
        allFields.add(edtServices);
        allFields.add(edtDate);
        allFields.add(edtTime);

        allInputLayouts = new ArrayList<>();
        allInputLayouts.add(ilFullName);
        allInputLayouts.add(ilMobileNumber);
        allInputLayouts.add(ilEmail);
        allInputLayouts.add(ilResidentialAddress);
        allInputLayouts.add(ilServices);
        allInputLayouts.add(ilDate);
        allInputLayouts.add(ilTime);

        fieldsMessages = new HashMap<>();
        fieldsMessages.put(edtFullName, "Enter name");
        fieldsMessages.put(edtMobileNumber, "Enter mobile number");
        fieldsMessages.put(edtEmail, "Enter email address");
        fieldsMessages.put(edtResidentialAddress, "Enter address");
        fieldsMessages.put(edtServices, "Select at least one service");
        fieldsMessages.put(edtDate, "Select date");
        fieldsMessages.put(edtTime, "Select time");
    }


    private void loadConsumerDetails() {
        Consumer consumer = MCUtility.getConsumer(this);
        edtFullName.setText(consumer.getFullName());
        edtMobileNumber.setText(consumer.getMobileNumber());
        edtEmail.setText(consumer.getEmail());
        edtResidentialAddress.setText(consumer.getAddress());
        if (mRequest != null) {
            edtDate.setText(MCUtility.getDisplayDate(mRequest.getRequestDateTime()));
            edtTime.setText(MCUtility.getDisplayTime(mRequest.getRequestDateTime()));
            edtServices.setText(MCUtility.getStringFromList(mRequest.getServices()));
            edtComments.setText(mRequest.getComments());
            selectedIndices = new Integer[mRequest.getServices().size()];
            for (String service : mRequest.getServices()) {
                int index = mRequest.getServices().indexOf(service);
                selectedIndices[index] = services.indexOf(service);
            }
            mCalendar.setTimeInMillis(mRequest.getRequestDateTime());
            selectedServices = new ArrayList<String>();
            selectedServices.clear();
            selectedServices.addAll(mRequest.getServices());
        }
    }

    @OnClick(R.id.edt_date)
    void onClickDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                mOnDateSetListener, mCalendar
                .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    @OnClick(R.id.edt_time)
    void onClickTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), mOnTimeSetListener, mCalendar
                .get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }

    @OnClick(R.id.btn_next)
    void onClickNext() {
        if (isFormValid()) {
            Request request = new Request();
            request.setRequestDateTime(mCalendar.getTimeInMillis());
            request.setRequesterName(edtFullName.getText().toString());
            request.setRequesterMobile(edtMobileNumber.getText().toString());
            request.setRequesterEmail(edtEmail.getText().toString());
            request.setRequesterAddress(edtResidentialAddress.getText().toString());
            request.setRequestedAt(System.currentTimeMillis());
            request.setComments(edtComments.getText().toString());
            request.setServices(selectedServices);
            request.setRequesterId(MCUtility.getUid(this));
            request.setStatus(MCConstants.RequestStatusInt.PENDING);
            Intent requestIntent = getNewIntent(ReviewRequestActivity.class);
            requestIntent.putExtra(MCConstants.KEY_REQUEST_OBJECT, request);
            startActivity(requestIntent);
            finish();
        }
    }

    private boolean isFormValid() {
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
        return true;
    }

    @OnClick(R.id.edt_services)
    void selectServices() {
        MCDialogUtility.getServicesDialog(this, selectedIndices, (dialog, which, text) -> {
            dialog.setSelectedIndices(which);
            selectedIndices = which;
            servicesString = "";
            selectedServices = new ArrayList<String>();
            selectedServices.clear();
            for (int index = 0; index < which.length; index++) {
                String service = services.get(which[index]);
                selectedServices.add(service);
                if (index == which.length - 1) {
                    servicesString = servicesString.concat(service);
                } else {
                    servicesString = servicesString.concat(service).concat(", ");
                }

            }
            edtServices.setText(servicesString);
            return true;
        }).show();
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

    TimePickerDialog.OnTimeSetListener mOnTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendar.set(Calendar.MINUTE, minute);
            if (System.currentTimeMillis() >= mCalendar.getTimeInMillis()) {
                showToast("Select Proper Time");
                edtTime.setText("");
            } else {
                setTime();
            }

        }

    };

    private void setDate() {
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        edtDate.setText(sdf.format(mCalendar.getTime()));
    }

    private void setTime() {
        String dateFormat = "hh:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        edtTime.setText(sdf.format(mCalendar.getTime()));
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_new_request;
    }

    @Override
    public boolean isToolbarPresent() {
        return true;
    }

    /**
     * Request focus to edit text
     *
     * @param view - view to request
     */
    protected void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow()
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
