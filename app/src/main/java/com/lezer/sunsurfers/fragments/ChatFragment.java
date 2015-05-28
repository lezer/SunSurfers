package com.lezer.sunsurfers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lezer.sunsurfers.R;

public class ChatFragment extends Fragment {

    public static final String TAG = "ChatFragmentTag";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_chat, null);
    }
}
