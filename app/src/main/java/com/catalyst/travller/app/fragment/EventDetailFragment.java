package com.catalyst.travller.app.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.catalyst.travller.app.R;
import com.catalyst.travller.app.data.EventInfoBean;

import java.text.SimpleDateFormat;

/**
 * Created by jitendra.karma on 06/02/2016.
 */
public class EventDetailFragment extends Fragment {

    private TextView mEventName, mEventDescription, mStartTime, mEndTime;
    private EventInfoBean mInfoBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_event_detail, container, false);

        mEventName = (TextView) view.findViewById(R.id.event_name);
        mEventDescription = (TextView) view.findViewById(R.id.event_description);
        mStartTime = (TextView) view.findViewById(R.id.event_start_time);
        mEndTime = (TextView) view.findViewById(R.id.event_end_time);

        view.findViewById(R.id.join_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnJoinButtonClick(v);
            }
        });

        initEventDetail();

        return view;
    }

    private void OnJoinButtonClick(View v) {

    }

    public void setEventDetail(EventInfoBean infoBean) {
        mInfoBean = infoBean;
    }

    private void initEventDetail() {
        if (mInfoBean != null) {
            mEventName.setText(mInfoBean.getEventName());
            mEventDescription.setText(mInfoBean.getEventDesc());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            mStartTime.setText(sdf.format(mInfoBean.getEventStartTime()));
            mEndTime.setText(sdf.format(mInfoBean.getEventStartTime()));
        }
    }
}
