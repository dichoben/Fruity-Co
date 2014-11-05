package com.si5.camash.fruityco.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 05/11/2014.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    private List<Integer> img=new ArrayList<Integer>();

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return img.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public void addImg(int i){
        img.add(i);
        notifyDataSetChanged();
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(img.get(position));
        return imageView;
    }


}
