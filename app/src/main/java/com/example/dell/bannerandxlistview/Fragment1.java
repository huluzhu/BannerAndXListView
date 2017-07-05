package com.example.dell.bannerandxlistview;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

/**
 * Created by 胡计强 on 2017/06/28.
 */
public class Fragment1 extends Fragment implements XListView.IXListViewListener {
    private String luoboUrl = "http://api.kkmh.com/v1/topic_new/discovery_list?gender=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3ODM5MDM5MzE1LCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjIuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tRmluZENhdGVnb3J5VGFiTmFtZSI6IuWFqOmDqCIsIklzQXV0b0xvYWQiOmZhbHNlLCIkbGliX3ZlcnNpb24iOiIxLjYuMzQiLCIkbmV0d29ya190eXBlIjoiV0lGSSIsIiR3aWZpIjp0cnVlLCIkbWFudWZhY3R1cmVyIjoic2Ftc3VuZyIsIkZyb21GaW5kVGFiTmFtZSI6IuaOqOiNkCIsIiRzY3JlZW5faGVpZ2h0Ijo1NzYsIkNhdGVnb3J5Ijoi5peg5rOV6I635Y-WIiwiSG9tZXBhZ2VVcGRhdGVEYXRlIjowLCJQcm9wZXJ0eUV2ZW50IjoiUmVhZEZpbmRQYWdlIiwiRmluZFRhYk5hbWUiOiLmjqjojZAiLCJhYnRlc3RfZ3JvdXAiOjQ2LCIkc2NyZWVuX3dpZHRoIjoxMDI0LCJGaW5kQ2F0ZWdvcnlUYWJOYW1lIjoi5YWo6YOoIiwiJG9zIjoiQW5kcm9pZCIsIlRyaWdnZXJQYWdlIjoiSG9tZVBhZ2UiLCIkY2FycmllciI6IkNNQ0MiLCIkbW9kZWwiOiJHVC1QNTIxMCIsIiRhcHBfdmVyc2lvbiI6IjMuOC4xIn0sInR5cGUiOiJ0cmFjayIsImRpc3RpbmN0X2lkIjoiQTo5MDUxMDQyNzYzNzU1MTA5Iiwib3JpZ2luYWxfaWQiOiJBOjkwNTEwNDI3NjM3NTUxMDkiLCJldmVudCI6IlJlYWRGaW5kUGFnZSJ9";
    private String xlistUrl = "http://qhb.2dyt.com/Bwei/news?type=1&postkey=1503d&page=";
    private int page = 1;
    private View view;
    private Banner banner;
    private MyMessage myMessage;
    private MyAdapter adapter;
    private List<XlistviewBean.ListBean> xlist;
    private List<LunBoBean.DataBean.InfosBean.BannersBean> lunbolist;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            Gson gson = new Gson();
            if (msg.what == 1) {
                LunBoBean lunBoBean = gson.fromJson(msg.obj.toString(), LunBoBean.class);
                lunbolist.addAll(lunBoBean.getData().getInfos().get(0).getBanners());
                List<String> title = new ArrayList<>();
                List<String> image = new ArrayList<>();
                for (LunBoBean.DataBean.InfosBean.BannersBean bannersBean : lunbolist) {
                    title.add(bannersBean.getTarget_title());
                    image.add(bannersBean.getPic());
                }
                banner.setBannerTitles(title);
                banner.setImages(image);
                banner.start();
            } else if (msg.what == 2) {
                XlistviewBean xlistviewBean = gson.fromJson(msg.obj.toString(), XlistviewBean.class);
                xlist.addAll(xlistviewBean.getList());
                myMessage.delete();
                myMessage.insert(xlist);
                adapter.notifyDataSetChanged();
            }
            stopXList();

        }
    };
    private XListView xlistview;

    private void stopXList() {
        xlistview.stopRefresh();
        xlistview.stopLoadMore();
        xlistview.setRefreshTime("刚刚");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.yemian1, null);
        xlist = new ArrayList<>();
        lunbolist = new ArrayList<>();
        myMessage = new MyMessage(getActivity());
        initBanner();
        initXList();
        //判断网络
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            Toast.makeText(getActivity(), "网络可用", Toast.LENGTH_SHORT).show();
            loadData(1);
            loadData(2);
        } else {
            Toast.makeText(getActivity(), "网络不可用", Toast.LENGTH_SHORT).show();
            xlist.addAll(myMessage.query());
            adapter.notifyDataSetChanged();
        }
        return view;
    }

    private void loadData(final int i) {
        new Thread() {
            @Override
            public void run() {
                if (i == 1) {
                    String str = UrlUtils.getUrlConnect(luoboUrl);
                    Message msg = Message.obtain();
                    msg.what = 1;
                    msg.obj = str;
                    handler.sendMessage(msg);
                } else if (i == 2) {
                    String str = UrlUtils.getUrlConnect(xlistUrl + page);
                    Message msg = Message.obtain();
                    msg.what = 2;
                    msg.obj = str;
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }

    private void initXList() {
        xlistview = (XListView) view.findViewById(R.id.myxlist);
        adapter = new MyAdapter(getActivity(), xlist);
        xlistview.setAdapter(adapter);
        xlistview.setPullLoadEnable(true);
        xlistview.setPullRefreshEnable(true);
        xlistview.setXListViewListener(this);
        xlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                XlistviewBean.ListBean b = xlist.get(position - 1);
                intent.putExtra("image", b.getPic());
                startActivity(intent);
            }
        });

    }


    private void initBanner() {
        banner = (Banner) view.findViewById(R.id.mybanner);
        banner.setBannerAnimation(Transformer.ForegroundToBackground);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        banner.setImageLoader(new BannerImageloader(getActivity()));
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                LunBoBean.DataBean.InfosBean.BannersBean bean = lunbolist.get(position);
                intent.putExtra("image", bean.getPic());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        page = 1;
        xlist.clear();
        loadData(2);

    }

    @Override
    public void onLoadMore() {
        page++;
        loadData(2);
    }

}
