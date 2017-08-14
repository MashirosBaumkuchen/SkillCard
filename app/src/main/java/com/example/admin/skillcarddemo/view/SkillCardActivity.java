package com.example.admin.skillcarddemo.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.example.admin.skillcarddemo.R;
import com.example.admin.skillcarddemo.SkillCardContract;
import com.example.admin.skillcarddemo.SkillPageTransformation;
import com.example.admin.skillcarddemo.adapter.SkillCardAdapter;
import com.example.admin.skillcarddemo.persenter.SkillCardPersenter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by biao.ma on 2017/8/10.
 */

public class SkillCardActivity extends AppCompatActivity implements SkillCardContract.View{
    private SkillCardContract.Persenter persenter;
    private List<Fragment> fragmentList;
    private List<HashMap<String, Integer>> list;
    private ViewPager viewPager;
    private List<OnFragmentChangeListener> listenerList;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_card);
        new SkillCardPersenter(this);
        persenter.getData();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.skill_card_tab);

        listenerList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("image", list.get(i).get("image"));
            if (list.get(i).get("skill_state") == 0) {
                SkillCardFragment skillCardFragment = new SkillCardFragment();
                skillCardFragment.setArguments(bundle);
                fragmentList.add(skillCardFragment);
                listenerList.add(skillCardFragment);
            } else if (list.get(i).get("skill_state") == 1) {
                SkillCardUnlockedFragment skillUnlockedFragment = new SkillCardUnlockedFragment();
                skillUnlockedFragment.setArguments(bundle);
                fragmentList.add(skillUnlockedFragment);
                listenerList.add(skillUnlockedFragment);
            }
            tabLayout.addTab(tabLayout.newTab());
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);

        // 设置skill card宽度
        int pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 5.0f / 6.0f);
        ViewGroup.LayoutParams lp = viewPager.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            lp.width = pagerWidth;
        }
        viewPager.setLayoutParams(lp);

        // 边缘margin距离
        viewPager.setPageMargin(-12);

        // 设置Skill滑动效果和显示效果
        viewPager.setPageTransformer(true, new SkillPageTransformation());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            boolean start = true;
            int fromPosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (start) {
                    fromPosition = position;
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (listenerList.size() > 0)
                    for (int i = 0; i < listenerList.size(); i++) {
                        listenerList.get(i).onFragmentChange(fromPosition, position);
                    }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 1) {
                    start = true;
                } else {
                    start = false;
                }
            }
        });
        viewPager.setAdapter(new SkillCardAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setCurrentItem(2); //viewPager进入页面号设置
        tabLayout.setupWithViewPager(viewPager);
        findViewById(R.id.image_close_skill_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkillCardActivity.this.finish();
            }
        });
    }

    @Override
    public void setPersenter(SkillCardContract.Persenter persenter) {
        this.persenter = persenter;
    }

    @Override
    public void showCardData(List<HashMap<String, Integer>> list) {
        this.list = list;
        initView();
    }

    @Override
    public void showLoadDataFail() {

    }

    interface OnFragmentChangeListener {
        void onFragmentChange(int pre, int position);
    }

}
