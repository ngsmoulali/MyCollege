package ngsm.com.mycollege.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ngsm.com.mycollege.R;

/**
 * Created by Rishi on 11/26/2016.
 */
public class SwipeAdapter extends PagerAdapter {
    private int[] img_resources={R.drawable.entrance,R.drawable.clg_building,R.drawable.svu_library};
    private Context context;
    private LayoutInflater layoutInflater;

    public SwipeAdapter(Context context){
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
