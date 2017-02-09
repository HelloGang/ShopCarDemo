package com.example.administrator.shopcardemo;

import android.annotation.SuppressLint;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.administrator.shopcardemo.fragment.BuyCarFragment;
import com.example.administrator.shopcardemo.fragment.HomeFragment;
import com.example.administrator.shopcardemo.fragment.MyFragment;
import com.example.administrator.shopcardemo.fragment.NewsFragmentTabHost;
import com.example.administrator.shopcardemo.fragment.TopicFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NewsFragmentTabHost mTabHost;
    private TextView title;
    // 图片
    @DrawableRes
    private int mImages[] = {
            R.drawable.tab_home,
            R.drawable.tab_topic,
            R.drawable.tab_car,
            R.drawable.tab_my
    };

    // 标题
    private String mFragmentTags[] = {
            "商品",
            "优惠",
            "购物车",
            "我的"
    };
    //Fragment集合
    private List<Fragment> fragmentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (TextView)findViewById(R.id.title);
        mTabHost = (NewsFragmentTabHost) findViewById(R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        //初始化Fragment
        initFragment();
        //初始化TAB
        Bundle bundle = null;
        for (int i = 0; i < mImages.length; i++) {
            // Tab按钮添加文字和图片
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mFragmentTags[i]).setIndicator(getImageView(i,mFragmentTags[i]));
            // 添加Fragment
            bundle = new Bundle();
            mTabHost.addTab(tabSpec, fragmentList.get(i).getClass(), bundle);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.pedo_actionbar_bkg);
        }
    }
    //初始化Fragment
    private void initFragment() {
        HomeFragment homeFragment = new HomeFragment();
        TopicFragment topicFragment = new TopicFragment();
        BuyCarFragment buyCarFragment = new BuyCarFragment();
        MyFragment myFragment = new MyFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(topicFragment);
        fragmentList.add(buyCarFragment);
        fragmentList.add(myFragment);
    }

    // 获得图片资源 设置初始化指示器
    private View getImageView(int index, String str) {
        @SuppressLint("InflateParams")
        View view = getLayoutInflater().inflate(R.layout.view_tab_indicator, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_iv_image);
        TextView label=(TextView)view.findViewById(R.id.tab_label);
        label.setText(str);
        imageView.setImageResource(mImages[index]);
        return view;
    }
}
