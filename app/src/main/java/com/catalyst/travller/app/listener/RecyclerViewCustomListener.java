package com.catalyst.travller.app.listener;

import android.view.View;

/**
 * Created by jitendra.karma on 06/02/2016.
 */
public interface RecyclerViewCustomListener {

    int EVENT_LIST_SCREEN = 1, NEW_EVENT_SCREEN = 2;

    void onItemClick(View v, int position, int screen, Object object);
}
