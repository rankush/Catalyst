package com.catalyst.travller.app.fragment;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.catalyst.travller.app.MapsActivity;
import com.catalyst.travller.app.R;
import com.catalyst.travller.app.data.EventInfoBean;
import com.catalyst.travller.app.data.LocationBean;
import com.catalyst.travller.app.data.SQLHelper;
import com.catalyst.travller.app.utils.AppConstants;
import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;
import java.util.List;

/**
 * Created by jitendra.karma on 07/02/2016.
 */
public class CreateEventFragment extends Fragment implements View.OnClickListener, MapsActivity.IRoute {

    private EditText mEventName, mStartTime, mEndTime, mEventDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_create_event, container, false);
        mEventName = (EditText) view.findViewById(R.id.event_name);
        mEventDescription = (EditText) view.findViewById(R.id.event_description);
        mStartTime = (EditText) view.findViewById(R.id.event_start_time);
        mEndTime = (EditText) view.findViewById(R.id.event_end_time);
        view.findViewById(R.id.create_event).setOnClickListener(this);
        view.findViewById(R.id.create_root).setOnClickListener(this);
        mStartTime.setOnClickListener(this);
        mEndTime.setOnClickListener(this);

        return view;
    }

    private static final int MAP_ACTIVITY = 0;

    private void createEvent(View v) {
        if (ifValidEvent(mEventName.getText().toString(), mEventDescription.getText().toString(), mStartTime.getText().toString(), mEndTime.getText().toString())) {
            SQLHelper db = new SQLHelper(getActivity());
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE);
            String userName = sharedPreferences.getString(AppConstants.USER_NAME, null);
            if (userName != null) {
                EventInfoBean infoBean = new EventInfoBean(startDialog.getDatePicker().getDrawingTime(),
                        endDialog.getDatePicker().getDrawingTime(), userName, mEventName.getText().toString(),
                        mEventDescription.getText().toString());
                long eventId = -1;
                if ((eventId = db.insertEvent(infoBean)) > 0) {
                    for (LatLng lat : mLatLongList) {
                        if (db.insertLocation(new LocationBean(lat.longitude, lat.longitude, eventId,
                                mEventName.getText().toString(), mEventDescription.getText().toString()))) {
                            Toast.makeText(getActivity(), "Location created successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                db.close();
            }

        }
    }

    private DatePickerDialog startDialog, endDialog;

    private void initStartDateTimePicker() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        startDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year, monthOfYear, dayOfMonth);

                if (c.getTimeInMillis() < System.currentTimeMillis()) {
                    initStartDateTimePicker();
                } else {
                    mStartTime.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            }
        }, mYear, mMonth, mDay);
        startDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        startDialog.show();
    }

    private void initEndDateTimePicker() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        endDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year, monthOfYear, dayOfMonth);
                if (c.getTimeInMillis() < System.currentTimeMillis()) {
                    initEndDateTimePicker();
                } else {
                    mEndTime.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            }
        }, mYear, mMonth, mDay);
        endDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        endDialog.show();
    }

    private void createRoot() {
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        getActivity().startActivityForResult(intent, MAP_ACTIVITY);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.event_start_time:
                initStartDateTimePicker();
                break;
            case R.id.event_end_time:
                initEndDateTimePicker();
                break;
            case R.id.create_event:
                createEvent(v);
                break;
            case R.id.create_root:
                createRoot();
                break;
        }
    }

    private List<LatLng> mLatLongList;

    private boolean ifValidEvent(String eventName, String eventDescription, String startTime, String endTime) {
//
        if (eventName == null || eventName.isEmpty()) {
            Toast.makeText(getActivity(), "Event name cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (eventDescription == null || eventDescription.isEmpty()) {
            Toast.makeText(getActivity(), "Event description cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (startTime == null || startTime.isEmpty()) {
            Toast.makeText(getActivity(), "Event start time cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (endTime == null || endTime.isEmpty()) {
            Toast.makeText(getActivity(), "Event end time cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        mLatLongList = MapsActivity.getAllLocation();
        if (mLatLongList == null || mLatLongList.isEmpty()) {
            Toast.makeText(getActivity(), "Please create a route", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onRouteCreated(List<LatLng> list) {
        mLatLongList = list;
    }
}
