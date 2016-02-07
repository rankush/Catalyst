package com.catalyst.travller.app.fragment;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.catalyst.travller.app.R;

import java.util.Calendar;

/**
 * Created by jitendra.karma on 07/02/2016.
 */
public class CreateEventFragment extends Fragment implements View.OnClickListener {

    private EditText mStartTime, mEndTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_create_event, container, false);
        mStartTime = (EditText) view.findViewById(R.id.event_start_time);
        mEndTime = (EditText) view.findViewById(R.id.event_end_time);
        view.findViewById(R.id.create_event).setOnClickListener(this);
        view.findViewById(R.id.create_root).setOnClickListener(this);
        mStartTime.setOnClickListener(this);
        mEndTime.setOnClickListener(this);
        return view;
    }

    private void createEvent(View v) {

    }

    private void initStartDateTimePicker() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mStartTime.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
            }
        }, mYear, mMonth, mDay);
        dialog.show();
    }

    private void initEndDateTimePicker() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mEndTime.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
            }
        }, mYear, mMonth, mDay);
        dialog.show();
    }

    private void createRoot() {

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
