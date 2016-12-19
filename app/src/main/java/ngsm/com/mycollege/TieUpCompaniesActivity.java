package ngsm.com.mycollege;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class TieUpCompaniesActivity extends AppCompatActivity {

    private TextView mtvTieUpCompany;

    private Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tie_up_companies_layout);

        Firebase.setAndroidContext(this);

        ref = new Firebase("https://college-7a896.firebaseio.com");

        mtvTieUpCompany = (TextView) findViewById(R.id.tvTieUpCompany);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Firebase cRef = ref.child("TieUp Companies");
        cRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String companies = "";
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String company = ds.getValue(String.class);
                    companies = companies + company + "\n";
                }
                mtvTieUpCompany.setText(companies);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
