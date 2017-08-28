package com.example.admin.skillcarddemo.persenter;

import com.example.admin.skillcarddemo.SkillCardContract;
import com.example.admin.skillcarddemo.model.SkillCardModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by biao.ma on 2017/8/10.
 */

public class SkillCardPersenter implements SkillCardContract.Persenter, SkillCardModel.SkillCardCallBack {
    private SkillCardContract.View view;

    public SkillCardPersenter(SkillCardContract.View view) {
        this.view = view;
        view.setPersenter(this);
    }

    @Override
    public void getData() {
        new SkillCardModel(this).getData();
    }

    @Override
    public void onGetSkillSuccess(List<HashMap<String, Integer>> list) {
        view.showCardData(list);
    }

    @Override
    public void onGetSkillFail() {
        view.showLoadDataFail();
    }
}
