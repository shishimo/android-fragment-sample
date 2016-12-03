package com.shishimo.android_fragment_sample;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ColorFragment extends Fragment {

    private static final String PRAM_COLOR = "Color";
    private String mColor;

    public ColorFragment() {
        // Required empty public constructor
    }

    public static ColorFragment newInstance(String color, String backStackName) {
        ColorFragment fragment = new ColorFragment();
        Bundle args = new Bundle();
        args.putString(PRAM_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColor = getArguments().getString(PRAM_COLOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_color, container, false);

        // テキストビューを設定
        TextView textView = (TextView)v.findViewById(R.id.stack_text_view);

        if (textView != null) {
            // 白文字
            textView.setTextColor(getResources().getColor(R.color.white, null));

            // テキストはタグを利用
            textView.setText(getTag());

            // 指定されたカラーをバックエンドに設定
            if (mColor.equals("Blue") ) {
                textView.setBackgroundResource(R.color.blue);
            } else if (mColor == "Green") {
                textView.setBackgroundResource(R.color.green);
            } else if (mColor == "Red") {
                textView.setBackgroundResource(R.color.red);
            } else {
                textView.setBackgroundResource(R.color.black);
            }
        }

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
