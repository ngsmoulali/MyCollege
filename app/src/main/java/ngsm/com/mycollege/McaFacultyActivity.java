package ngsm.com.mycollege;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ngsm.com.mycollege.Util.Faculty;

public class McaFacultyActivity extends AppCompatActivity {

    private RecyclerView mcaFacultyList;

    private DatabaseReference mDatabase;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mca_faculty_layout);
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

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Faculty").child("MCA");

        mcaFacultyList = (RecyclerView) findViewById(R.id.mcaFacultyList);
        mcaFacultyList.setHasFixedSize(true);
        mcaFacultyList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        //mAuth.addAuthStateListener(mAuthListener);

        FirebaseRecyclerAdapter<McaFaculty, McaFacultyActivity.McaFacultyViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<McaFaculty, McaFacultyViewHolder>(

                McaFaculty.class,
                R.layout.faculty_row,
                McaFacultyViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(McaFacultyViewHolder viewHolder, McaFaculty model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setSubject(model.getSubject());
                viewHolder.setDesignation(model.getDesignation());
                viewHolder.setImage(getApplicationContext(), model.getImage());

            }
        };

        mcaFacultyList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class McaFacultyViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public McaFacultyViewHolder(View itemView) {
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