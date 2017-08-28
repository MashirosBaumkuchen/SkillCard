package com.example.admin.skillcarddemo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by biao.ma on 2017/8/10.
 */

public interface SkillCardContract {
    interface View {
        void setPersenter(SkillCardContract.Persenter persenter);

        void showCardData(List<HashMap<String, Integer>> list);

        void showLoadDataFail();
    }

    interface Persenter {
        void getData();
    }
}
