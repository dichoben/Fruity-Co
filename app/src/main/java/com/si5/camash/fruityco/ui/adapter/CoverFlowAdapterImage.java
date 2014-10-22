package com.si5.camash.fruityco.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.si5.camash.fruityco.ui.views.coverFlow.FancyCoverFlow;
import com.si5.camash.fruityco.ui.views.coverFlow.FancyCoverFlowAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 22/10/2014.
 */
public class CoverFlowAdapterImage extends FancyCoverFlowAdapter {


    // =============================================================================
    // Private members
    // =============================================================================

    private List<Integer> images=new ArrayList<Integer>();

    public CoverFlowAdapterImage(List<Integer> images) {
        this.images = images;
    }

    // =============================================================================
    // Supertype overrides
    // =============================================================================

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Integer getItem(int i) {
        return images.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
        ImageView imageView = null;

        if (reuseableView != null) {
            imageView = (ImageView) reuseableView;
        } else {
            imageView = new ImageView(viewGroup.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setLayoutParams(new FancyCoverFlow.LayoutParams(300, 400));

        }

        imageView.setImageResource(this.getItem(i));
        return imageView;
    }
}
