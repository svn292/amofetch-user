package com.foodkal.app.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.foodkal.app.R;
import com.foodkal.app.fragments.SliderDialogFragment;
import com.foodkal.app.models.Image;

import java.util.List;

/**
 * Created by santhosh@appoets.com on 09-11-2017.
 */

public class SliderPagerAdapter extends PagerAdapter {
    private Activity activity;
    private List<Image> image_arraylist;
    private Boolean isClickable = false;

    public SliderPagerAdapter(Activity activity, List<Image> image_arraylist, Boolean isClickable) {
        this.activity = activity;
        this.image_arraylist = image_arraylist;
        this.isClickable = isClickable;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.layout_slider, container, false);
        ImageView im_slider = view.findViewById(R.id.im_slider);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_restaurant_place_holder)
                .error(R.drawable.ic_restaurant_place_holder)
                .priority(Priority.HIGH);

        Glide
                .with(activity.getApplicationContext())
                .load(image_arraylist.get(position).getUrl())
                .apply(options)
                .into(im_slider);
        if (isClickable) {
            im_slider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager manager = activity.getFragmentManager();
                    SliderDialogFragment sliderDialogFragment = new SliderDialogFragment();
                    sliderDialogFragment.show(manager, "slider_dialog");
                }
            });
        }
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return image_arraylist.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
