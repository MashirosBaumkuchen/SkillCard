package com.example.admin.skillcarddemo.model;

import com.example.admin.skillcarddemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by biao.ma on 2017/8/10.
 */

public class SkillCardModel implements ISkillCardModel {
    private SkillCardCallBack callBack;

    public SkillCardModel(SkillCardCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void getData() {
        List<HashMap<String, Integer>> list;
        list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("skill_state", 1);
        map.put("image", R.mipmap.first);
        list.add(map);

        map = new HashMap<>();
        map.put("skill_state", 1);
        map.put("image", R.mipmap.second);
        list.add(map);

        map = new HashMap<>();
        map.put("skill_state", 0);
        map.put("image", R.mipmap.third);
        list.add(map);

        map = new HashMap<>();
        map.put("skill_state", 0);
        map.put("image", R.mipmap.fourth);
        list.add(map);

        map = new HashMap<>();
        map.put("skill_state", 0);
        map.put("image", R.mipmap.fifth);
        list.add(map);
        callBack.onGetSkillSuccess(list);
    }

    @Override
    public void getDataFromCache() {
        callBack.onGetSkillFail();
    }

    @Override
    public void getDataFromWeb() {
        callBack.onGetSkillFail();
    }

    public interface SkillCardCallBack{
        void onGetSkillSuccess(List<HashMap<String, Integer>> list);
        void onGetSkillFail();
    }
}
