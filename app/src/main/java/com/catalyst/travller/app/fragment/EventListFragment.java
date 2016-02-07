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
import com.catalyst.travller.app.adapter.HomeAdapter;

/**
 * Created by jitendra.karma on 06/02/2016.
 */
public class EventListFragment extends Fragment {

    private RecyclerView mEventList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_home, container, false);
        mEventList = (RecyclerView) v.findViewById(R.id.recycler_view_main);
        initEventList();
        return v;
    }

    private String[] getArray() {
        return new String[]{getResources().getString(R.string.new_event),
                getResources().getString(R.string.search_event),
                getResources().getString(R.string.all_event)};
    }

    private void initEventList() {
        mEventList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEventList.setAdapter(new HomeAdapter(getArray()));

    }

}
