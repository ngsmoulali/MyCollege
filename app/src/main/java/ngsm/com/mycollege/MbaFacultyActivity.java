package ngsm.com.mycollege;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MbaFacultyActivity extends AppCompatActivity {

    private RecyclerView mbaFacultyList;

    private DatabaseReference mDatabase;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mba_faculty_layout);
       /* mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() == null){

                    Intent loginIntent = new Intent(getBaseContext(), LoginActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);

                }

            }
        };*/

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Faculty").child("MBA");

        mbaFacultyList = (RecyclerView) findViewById(R.id.mbaFacultyList);
        mbaFacultyList.setHasFixedSize(true);
        mbaFacultyList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        //mAuth.addAuthStateListener(mAuthListener);

        FirebaseRecyclerAdapter<McaFaculty, MbaFacultyActivity.MbaFacultyViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<McaFaculty, MbaFacultyActivity.MbaFacultyViewHolder>(

                McaFaculty.class,
                R.layout.faculty_row,
                MbaFacultyActivity.MbaFacultyViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(MbaFacultyActivity.MbaFacultyViewHolder viewHolder, McaFaculty model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setSubject(model.getSubject());
                viewHolder.setDesignation(model.getDesignation());
                viewHolder.setImage(getApplicationContext(), model.getImage());

            }
        };

        mbaFacultyList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class MbaFacultyViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public MbaFacultyViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setName(String name){

            TextView faculty_name = (TextView) mView.findViewById(R.id.tvFaculty);
            faculty_name.setText(name);

        }

        public void setSubject(String subject){

            TextView faculty_subject = (TextView) mView.findViewById(R.id.tvSubject);
            faculty_subject.setText(subject);

        }

        public void setDesignation(String designation){

            TextView faculty_designation = (TextView) mView.findViewById(R.id.tvDesignation);
            faculty_designation.setText(designation);

        }

        public void setImage(Context context, String image){

            ImageView faculty_image = (ImageView) mView.findViewById(R.id.ivFaculty);
            Picasso.with(context).load(image).into(faculty_image);

        }

    }
}
