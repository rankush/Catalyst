package com.catalyst.travller.app.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.catalyst.travller.app.R;

/**
 * Created by jitendra.karma on 06/02/2016.
 */
public class EventDetailFragment extends Fragment {

    private TextView mEventName, mEventDescription, mStartTime, mEndTime;

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

        return view;
    }

    private void OnJoinButtonClick(View v) {

    }
}
