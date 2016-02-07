package com.catalyst.travller.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.catalyst.travller.app.R;
import com.catalyst.travller.app.data.EventInfoBean;
import com.catalyst.travller.app.listener.RecyclerViewCustomListener;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by jitendra.karma on 06/02/2016.
 */
public class NewEventAdapter extends RecyclerView.Adapter<NewEventAdapter.HomeAdapterHolder> {

    private List<EventInfoBean> mList;

    public NewEventAdapter(List<EventInfoBean> list) {
        mList = list;
    }

    public class HomeAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView mEventName, mEventStartTime, mEventEndTime;
        protected ImageView mEventImage;

        public HomeAdapterHolder(View itemView) {
            super(itemView);

            mEventName = (TextView) itemView.findViewById(R.id.event_name);
            mEventImage = (ImageView) itemView.findViewById(R.id.event_image);
            mEventStartTime = (TextView) itemView.findViewById(R.id.event_start_time);
            mEventEndTime = (TextView) itemView.findViewById(R.id.event_end_time);

            itemView.setFocusable(true);
            itemView.setClickable(true);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ((RecyclerViewCustomListener) v.getContext()).onItemClick(v, getLayoutPosition(),
                    RecyclerViewCustomListener.NEW_EVENT_SCREEN, mList.get(getLayoutPosition()));
        }
    }

    @Override
    public HomeAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_new_event_row, parent, false);
        return new HomeAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeAdapterHolder holder, int position) {
        holder.mEventName.setText(mList.get(position).getEventName());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String date = sdf.format(mList.get(position).getEventStartTime());
        holder.mEventStartTime.setText(date);
        date = sdf.format(mList.get(position).getEventEndTime());
        holder.mEventEndTime.setText(date);

        holder.mEventImage.setImageResource(R.drawable.card_bg);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onViewRecycled(HomeAdapterHolder holder) {
        super.onViewRecycled(holder);
    }
}
