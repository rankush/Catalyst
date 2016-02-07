package com.catalyst.travller.app.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catalyst.travller.app.R;
import com.catalyst.travller.app.adapter.NewEventAdapter;

/**
 * Created by jitendra.karma on 06/02/2016.
 */
public class NewEventFragment extends Fragment {

    private RecyclerView mEventList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_new_event_fragment, container, false);
        mEventList = (RecyclerView) v.findViewById(R.id.new_event_recycler_view);
        initEventList();
        return v;
    }

    private String[] getArray() {
        return new String[]{"New Event 1", "New Event 2", "New Event 3", "New Event 4", "New Event 5"};
    }

    private void initEventList() {
        mEventList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEventList.setAdapter(new NewEventAdapter(getArray()));

    }
}