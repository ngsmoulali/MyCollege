package ngsm.com.mycollege.Tabs;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ngsm.com.mycollege.ClickInterface;
import ngsm.com.mycollege.FacultyActivity;
import ngsm.com.mycollege.R;

public class Mca extends Fragment {
    private Button btnFaculty;
    private ClickInterface mCallback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (ClickInterface) activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mca, container, false);
        btnFaculty=(Button)view.findViewById(R.id.Faculty);
        btnFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.mcaFaculty();
            }
        });


        return inflater.inflate(R.layout.activity_mca, null);

    }


}