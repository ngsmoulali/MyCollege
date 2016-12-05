package ngsm.com.mycollege;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class PlacementHistoryActivity extends AppCompatActivity {

    private TextView mtvCompanyName, mtvYear, mtvStudents;

    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placement_history_layout);

        Firebase.setAndroidContext(this);

        ref = new Firebase("https://college-7a896.firebaseio.com");

        mtvCompanyName = (TextView) findViewById(R.id.tvCompanyName);
        mtvYear = (TextView) findViewById(R.id.tvYear);
        mtvStudents = (TextView) findViewById(R.id.tvStudents);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Firebase cRef = ref.child("placementHistory");
        cRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String companies = "";
                String years = "";
                String students = "";
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    PlacementHistory placementHistory = ds.getValue(PlacementHistory.class);
                    companies = companies + placementHistory.companyName + "\n";
                    years = years + placementHistory.year + "\n";
                    students = students + placementHistory.noOfStudents + "\n";
                }
                mtvCompanyName.setText(companies);
                mtvYear.setText(years);
                mtvStudents.setText(students);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
