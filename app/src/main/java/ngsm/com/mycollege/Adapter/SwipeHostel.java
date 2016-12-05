package ngsm.com.mycollege.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ngsm.com.mycollege.R;

/**
 * Created by Rishi on 11/30/2016.
 */
public class SwipeHostel extends PagerAdapter {
    private int[] img_resources={R.drawable.hostels_1,R.drawable.canteen};
    private Context context;
    private LayoutInflater layoutInflater;

    public SwipeHostel(Context context){
        this.context=context;

    }
    @Override
    public int getCount() {
        return img_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {

        return (view==(LinearLayout)o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView ivSwipe=(ImageView)item_view.findViewById(R.id.ivSwipe);
        ivSwipe.setImageResource(img_resources[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}

