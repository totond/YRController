package com.yanzhikai.controllerandroid;

import android.content.Context;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import static com.yanzhikai.controllerandroid.ConstantKt.STATE_ENABLE;

public class ControllerActivity extends AppCompatActivity {

    private ControllerAdapter mAdapter;
    private ArrayList<CommandInfo> mCommandInfoList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        initData();
        initView();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    private void initData() {
        mCommandInfoList = new ArrayList<>();
        mCommandInfoList.add(new CommandInfo(10001, "关屏幕", STATE_ENABLE));
        mCommandInfoList.add(new CommandInfo(10002, "上一首", STATE_ENABLE));
        mCommandInfoList.add(new CommandInfo(10003, "下一首", STATE_ENABLE));
        mCommandInfoList.add(new CommandInfo(10004, "播放/暂停", STATE_ENABLE));
        mCommandInfoList.add(new CommandInfo(10005, "声音大一点", STATE_ENABLE));
        mCommandInfoList.add(new CommandInfo(10006, "声音小一点", STATE_ENABLE));
    }

    private void initView(){
        mRecyclerView = findViewById(R.id.rv_command);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mAdapter = new ControllerAdapter(R.layout.item_commond, mCommandInfoList);
        mAdapter.setOnItemClickListener(mItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
    }

    private BaseQuickAdapter.OnItemClickListener mItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            CommandInfo commandInfo = mCommandInfoList.get(position);
            ConnectManager.getInstance().sendCommand(commandInfo.getId() + "\n");
        }
    };

}
