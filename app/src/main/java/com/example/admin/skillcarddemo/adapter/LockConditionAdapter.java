package com.example.admin.skillcarddemo.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.skillcarddemo.R;

import java.util.List;
import java.util.Map;

/**
 * Created by biao.ma on 2017/8/9.
 */

public class LockConditionAdapter extends BaseAdapter {
    private List<Map<String, String>> data;
    private Context context;
    private ConditionViewHolder holder;

    public LockConditionAdapter(Context context, List<Map<String, String>> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            holder = new ConditionViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.condition_item, null);
            holder.Icon = view.findViewById(R.id.type);
            holder.condition = view.findViewById(R.id.condition);
            holder.state = view.findViewById(R.id.state);
            view.setTag(holder);
        } else {
            holder = (ConditionViewHolder) view.getTag();
        }
        if (data.get(i).get("type").equals("camera")){
            holder.Icon.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.phone_play_icon_cameraskill_unlocked));
        } else if (data.get(i).get("type").equals("follow me")){
            holder.Icon.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.phone_play_icon_pathskill_unlocked));
        }
        holder.condition.setText(data.get(i).get("condition"));
        holder.state.setText(data.get(i).get("state"));
        return view;
    }

    class ConditionViewHolder {
        ImageView Icon;
        TextView condition;
        TextView state;
    }

}
