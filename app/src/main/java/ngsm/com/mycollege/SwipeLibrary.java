package ngsm.com.mycollege;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Rishi on 12/4/2016.
 */
public class SwipeLibrary extends PagerAdapter {
    private int[] img_resources = {R.drawable.svu_library, R.drawable.library_2, R.drawable.library_3, R.drawable.library_4};
    private Context context;
    private LayoutInflater layoutInflater;

    public SwipeLibrary(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return img_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {

        return (view == (LinearLayout) o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);
        ImageView ivSwipe = (ImageView) item_view.findViewById(R.id.ivSwipe);
        ivSwipe.setImageResource(img_resources[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
