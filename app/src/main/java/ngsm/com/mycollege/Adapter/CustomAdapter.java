package ngsm.com.mycollege.Adapter;

/**
 * Created by Rishi on 12/4/2016.
 */
import ngsm.com.mycollege.R;
import ngsm.com.mycollege.Util.AppController;
import ngsm.com.mycollege.Util.Faculty;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Faculty> facultyList;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomAdapter(Activity activity, List<Faculty> facultyList) {
        this.activity = activity;
        this.facultyList = facultyList;
    }

    @Override
    public int getCount() {
        return facultyList.size();
    }

    @Override
    public Object getItem(int location) {
        return facultyList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView designation = (TextView) convertView.findViewById(R.id.designation);
        TextView subject = (TextView) convertView.findViewById(R.id.subject);


        Faculty f = facultyList.get(position);

        // thumbnail image
        thumbNail.setImageUrl(f.getImage(), imageLoader);

        // name
        name.setText(f.getName());
        //designation
        designation.setText(f.getDesignation());
        //subject
        subject.setText(f.getSubject());


        return convertView;
    }

}