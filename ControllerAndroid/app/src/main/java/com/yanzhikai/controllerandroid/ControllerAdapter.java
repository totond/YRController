package com.yanzhikai.controllerandroid;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ControllerAdapter extends BaseQuickAdapter<CommandInfo,BaseViewHolder> {
    public ControllerAdapter(int layoutResId, @Nullable List<CommandInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommandInfo item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_content, String.valueOf(item.getId()));
    }
}
