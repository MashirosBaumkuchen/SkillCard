package com.example.admin.skillcarddemo.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.admin.skillcarddemo.R;

import java.util.List;
import java.util.Map;

/**
 * Created by biao.ma on 2017/8/10.
 */

public class SkillCardUnlockedFragment extends Fragment implements SkillCardActivity.OnFragmentChangeListener, View.OnClickListener, View.OnTouchListener {
    private ImageView moreInfo;
    private View view;
    private ImageView arrowUp;
    private ImageView arrowDown;
    private BottomSheetBehavior behavior;

    public SkillCardUnlockedFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.card_unlock, null);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageDrawable(ContextCompat.getDrawable(SkillCardUnlockedFragment.this.getContext(), getArguments().getInt("image")));

        // init sheet view
        View bottomSheet = view.findViewById(R.id.pop_view);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);// 初始设置为 hidden模式
        behavior.setSkipCollapsed(true);// 设置跳过 collapse模式

        // progressBar进度设置
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setProgress((int) 36500 / 500);

        moreInfo = view.findViewById(R.id.more_info);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO 跳转skill实现页面
            }
        });

        arrowUp = view.findViewById(R.id.arrow_up);
        arrowDown = view.findViewById(R.id.arrow_down);
        arrowUp.setOnClickListener(this);// arrowUp 箭头点击事件监听
        arrowUp.setOnTouchListener(this);// arrowUp 滑动事件监听
        arrowDown.setOnClickListener(this);// arrowDown 箭头点击事件监听
        arrowDown.setOnTouchListener(this);// arrowDown 箭头点击事件监听

        // sheet消失回调
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN)
                    hiddenArrowDownIcon();
                else if (newState == BottomSheetBehavior.STATE_EXPANDED)
                    expandedArrowDownIcon();
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset < 0) {
                    hiddenArrowDownIcon();
                }
            }
        });
        return view;
    }

    // set arrowDown invisible
    private void hiddenArrowDownIcon() {
        arrowDown.setVisibility(View.INVISIBLE);
    }

    // set arrowDown visible
    private void expandedArrowDownIcon() {
        arrowDown.setVisibility(View.VISIBLE);
    }


    private void hiddenPopUpWindow() {
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private void expandedPopUpWindow() {
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void onFragmentChange(int pre, int position) {
        if (behavior != null) {
            hiddenPopUpWindow();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.arrow_up) {
            if (behavior.getState() == BottomSheetBehavior.STATE_HIDDEN)
                expandedPopUpWindow();
        } else if (view.getId() == R.id.arrow_down) {
            if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                hiddenArrowDownIcon();
                hiddenPopUpWindow();
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        float y1 = 0, y2 = 0;
        if (view.getId() == R.id.arrow_up) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                y1 = event.getY();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                y2 = event.getY();
                if (y1 - y2 > ViewConfiguration.get(SkillCardUnlockedFragment.this.getContext()).getScaledTouchSlop()) {
                    if (behavior.getState() == BottomSheetBehavior.STATE_HIDDEN)
                        expandedPopUpWindow();
                }
            }
        } else if (view.getId() == R.id.arrow_down) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                y1 = event.getY();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                y2 = event.getY();
                if (y2 - y1 > ViewConfiguration.get(SkillCardUnlockedFragment.this.getContext()).getScaledTouchSlop()) {
                    if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                        hiddenPopUpWindow();
                        hiddenArrowDownIcon();
                    }
                }
            }
        }
        return false;
    }
}
