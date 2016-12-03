package com.shishimo.android_fragment_sample;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mManager;

    private Button mBlueButton;
    private Button mGreenButton;
    private Button mRedButton;
    private Button mClearButton;
    private Spinner mSpinner;
    private TextView mNumberTextView;

    private String mBackStackName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumberTextView = (TextView)findViewById(R.id.numberTextView);

        mManager = getFragmentManager();
        mManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                // バックスタックの数を記載する。
                int count = mManager.getBackStackEntryCount();
                mNumberTextView.setText("BackStackEntry: " + count);
            }
        });

        mBackStackName = "Stack-A";
        mSpinner = (Spinner)findViewById(R.id.spinner);
        if (mSpinner != null) {
            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView textView = (TextView) view;
                    mBackStackName = textView.getText().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

        mGreenButton = (Button)findViewById(R.id.button_green);

        if (mGreenButton != null) {

            mGreenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addColorFragment("Green", mBackStackName);
                }
            });

        }

        mBlueButton = (Button)findViewById(R.id.button_blue);

        if (mBlueButton != null) {

            mBlueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addColorFragment("Blue", mBackStackName);
                }
            });

        }

        mRedButton = (Button)findViewById(R.id.button_red);

        if (mRedButton != null) {

            mRedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addColorFragment("Red", mBackStackName);
                }
            });

        }

        mClearButton = (Button)findViewById(R.id.button_clear);

        if (mClearButton != null) {

            mClearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // 上から順にポップしていく。
                    //mManager.popBackStack();

                    // 1番上のBlueのバックスタックを全てポップしていく。
                    //mManager.popBackStack("Stack-A", 0);

                    // 1番上のBlueのバックスタックまで消していく。
                    mManager.popBackStack("Stack-A", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    // 全部削除
                    //mManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            });

        }

    }

    public void addColorFragment(String color, String backStackName) {

        // フラグメントのタグ名を作成
        int count = mManager.getBackStackEntryCount();

        String tag = backStackName + ":" + count;

        // フラグメント生成
        ColorFragment fragment = ColorFragment.newInstance(color, backStackName);

        // フラグメントを操作するトランザクションを開始
        FragmentTransaction transaction = mManager.beginTransaction();

        // フラグメントを追加
        transaction.add(R.id.container, fragment, tag);

        // バックスタックに追加
        transaction.addToBackStack(mBackStackName);

        // トランジションを追加
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        // コミット
        transaction.commit();

    }
}
