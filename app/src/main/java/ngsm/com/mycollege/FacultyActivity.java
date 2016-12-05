package ngsm.com.mycollege;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ngsm.com.mycollege.Adapter.CustomAdapter;
import ngsm.com.mycollege.Util.AppController;
import ngsm.com.mycollege.Util.Faculty;

import com.firebase.client.Config;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.core.view.View;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FacultyActivity extends Activity implements ClickInterface {

    private static final String TAG = FragmentActivity.class.getSimpleName();
    // json url
    private static final String url = "https://my-coleege.firebaseio.com/";
    private ProgressDialog pDialog;
    private List<Faculty> facultyList = new ArrayList<Faculty>();
    private ListView listView;
    private CustomAdapter adapter;
    private Firebase ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        Firebase.setAndroidContext(this);
        ref=new Firebase(url);




        listView = (ListView) findViewById(R.id.list);
        hidePDialog();
        adapter = new CustomAdapter(this, facultyList);
        listView.setAdapter(adapter);
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();


    }
    public void mcaFaculty(){
        ref.child("Mca").addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                for(com.firebase.client.DataSnapshot ds:dataSnapshot.getChildren()) {
                    Faculty faculty = new Faculty();
                    faculty.setName(ds.getValue(Faculty.class).getName());
                    faculty.setImage(ds.getValue(Faculty.class).getImage());
                    faculty.setDesignation(ds.getValue(Faculty.class).getDesignation());
                    faculty.setSubject(ds.getValue(Faculty.class).getSubject());
                    facultyList.add(faculty);

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getBaseContext(),"Load Failed"+firebaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void mbaFaculty() {
        ref.child("Mba").addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                for(com.firebase.client.DataSnapshot ds:dataSnapshot.getChildren()) {
                    Faculty faculty = new Faculty();
                    faculty.setName(ds.getValue(Faculty.class).getName());
                    faculty.setImage(ds.getValue(Faculty.class).getImage());
                    faculty.setDesignation(ds.getValue(Faculty.class).getDesignation());
                    faculty.setSubject(ds.getValue(Faculty.class).getSubject());
                    facultyList.add(faculty);

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getBaseContext(),"Load Failed"+firebaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void commerceFaculty() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

     private void hidePDialog() {
        if (pDialog != null) {
        pDialog.dismiss();
        pDialog = null;
        }
        }

}
