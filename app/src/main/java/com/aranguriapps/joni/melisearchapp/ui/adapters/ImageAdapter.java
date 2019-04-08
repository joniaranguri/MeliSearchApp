package com.aranguriapps.joni.melisearchapp.ui.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aranguriapps.joni.melisearchapp.R;
import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.domain.MeliSearchImage;
import com.aranguriapps.joni.melisearchapp.io.api.MeliSearchApiConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<MeliSearchImage> picturesList;

    public ImageAdapter(Context context) {
        mContext = context;
        this.picturesList = new ArrayList<>();
    }

    public void replace(ArrayList<MeliSearchImage> images) {
        this.picturesList = images;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return picturesList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        final ImageView img = new ImageView(container.getContext());
        img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Picasso.get()
                .load(picturesList.get(position).getUrl())
                // .placeholder(R.drawable.plusbtn)
                .into(img);
        container.addView(img, 0);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}