package com.catalyst.travller.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.catalyst.travller.app.R;
import com.catalyst.travller.app.listener.RecyclerViewCustomListener;

/**
 * Created by jitendra.karma on 06/02/2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeAdapterHolder> {

    private String[] mList;

    public HomeAdapter(String[] list) {
        mList = list;
    }

    public class HomeAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView mEventName;
        protected ImageView mEventImage;

        public HomeAdapterHolder(View itemView) {
            super(itemView);

            mEventName = (TextView) itemView.findViewById(R.id.event_name);
            mEventImage = (ImageView) itemView.findViewById(R.id.event_image);

            itemView.setFocusable(true);
            itemView.setClickable(true);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ((RecyclerViewCustomListener) v.getContext()).onItemClick(v, getLayoutPosition(), RecyclerViewCustomListener.EVENT_LIST_SCREEN);
        }
    }

    @Override
    public HomeAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_event_list_row, parent, false);
        return new HomeAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeAdapterHolder holder, int position) {
        holder.mEventName.setText(mList[position]);
        holder.mEventImage.setImageResource(R.drawable.event_bg);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.length;
    }

    @Override
    public void onViewRecycled(HomeAdapterHolder holder) {
        super.onViewRecycled(holder);
    }
}
