package com.example.dell.bannerandxlistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by 胡计强 on 2017/06/28.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<XlistviewBean.ListBean> list;
    private final ImageLoader imageLoader;
    private final DisplayImageOptions options;

    public MyAdapter(Context context, List<XlistviewBean.ListBean> list) {
        this.context = context;
        this.list = list;
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(context);
        //将configuration配置到imageloader中
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(configuration);
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.ARGB_8888)//设置图片的解码类型
                .showImageOnLoading(R.mipmap.ic_launcher)//设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)//设置图片加载/解码过程中错误时候显示的图片
                .build();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.xlist_item, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.image);
            holder.tv1 = (TextView) convertView.findViewById(R.id.title);
            holder.tv2 = (TextView) convertView.findViewById(R.id.textid);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        XlistviewBean.ListBean bean = list.get(position);
        holder.tv1.setText(bean.getTitle());
        holder.tv2.setText(bean.getId() + "");
        imageLoader.displayImage(bean.getPic(), holder.imageView, options);
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView tv1, tv2;

    }
}
