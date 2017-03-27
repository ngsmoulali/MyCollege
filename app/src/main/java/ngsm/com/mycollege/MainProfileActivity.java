package ngsm.com.mycollege;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.BooleanResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import ngsm.com.mycollege.Picresizer.MyAdapter;

import static ngsm.com.mycollege.LoginActivity.LOGOUT;
import static ngsm.com.mycollege.LoginActivity.MyPREFERENCES;
import static ngsm.com.mycollege.LoginActivity.SID;
import static ngsm.com.mycollege.LoginActivity.SIMAGE;
import static ngsm.com.mycollege.LoginActivity.SNAME;

public class MainProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences sharedPreferences;

    private String sID;

    public TextView studentName;
    public ImageView studentImage;
    public TextView studentID;

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseName;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String studentProfileImage;

    private String logout;

    private NavigationView navigationView;

    private Boolean checkInternet;

    static MainProfileActivity mainProfileActivity;

    ArrayList<Integer> item_ids = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_profile_layout);

        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getBaseContext(),"Position is " + i, Toast.LENGTH_SHORT);

                switch (i){

                    case 0:
                        startActivity(new Intent(getBaseContext(), ProfileActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getBaseContext(), PlacementActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getBaseContext(), DepartmentsActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getBaseContext(), LibraryActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getBaseContext(), HostelActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(getBaseContext(), MainProfileActivity.class));
                        finish();
                        break;

                }

            }
        });

        mainProfileActivity = this;

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        sharedPreferences = getSharedPreferences(LOGOUT,MODE_PRIVATE);

        logout = sharedPreferences.getString(LOGOUT,"LOGOUT");

        if(LOGOUT.equals(logout)){

            nav_Menu.findItem(R.id.nav_logout).setVisible(false);
            nav_Menu.findItem(R.id.nav_login).setVisible(true);

        } else {

            nav_Menu.findItem(R.id.nav_logout).setVisible(true);
            nav_Menu.findItem(R.id.nav_login).setVisible(false);

        }

        sharedPreferences = getSharedPreferences(SNAME, MODE_PRIVATE);
        sharedPreferences = getSharedPreferences(SIMAGE, MODE_PRIVATE);
        sharedPreferences = getSharedPreferences(SID,MODE_PRIVATE);

        sID = sharedPreferences.getString(SID,"Student Id");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("studentDetails").child(sID);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView = navigationView.getHeaderView(0);
        studentID = (TextView) hView.findViewById(R.id.tvStudentId);
        studentName = (TextView) hView.findViewById(R.id.tvStudentName);
        studentImage = (ImageView) hView.findViewById(R.id.ivProfileImage);

        //mDatabaseName = FirebaseDatabase.getInstance().getReference().child("studentDetails").child(sID);

        //studentName.setText(mDatabase.toString());

        /*new Thread(new Runnable() {
            public void run() {

                    }
                });
            }
        }).start();*/

        /*Thread thread = new Thread()
        {
            @Override
            public void run() {
                checkInternet = hasInternetAccess(getApplicationContext());
            }
        };

        thread.start();*/

        /*Thread thread = new Thread(new MyRunnable());
        thread.start(); //in background thread*/

        //if(isNetworkAvailable(getApplicationContext())) {

            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    studentID.setText(sID);

                    StudentProfile studentProfile = dataSnapshot.getValue(StudentProfile.class);

                    studentName.setText(String.valueOf(studentProfile.getName()));

                    studentProfileImage = String.valueOf(studentProfile.getImage());

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(SNAME, studentName.getText().toString());
                    editor.putString(SIMAGE, studentProfileImage);
                    editor.commit();

                    //String sag = post.child("email").getValue().toString();

                    //studentID.setText(sID);

                    //studentName.setText(sag);
                    //studentID.setText(sID);
                    Picasso.with(getApplicationContext()).load(studentProfileImage).into(studentImage);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        /*} else {

            Toast.makeText(getBaseContext(), "Please Check Your Internet Setting And Try Again", Toast.LENGTH_SHORT);

        }*/

    }

    public static MainProfileActivity getInstance(){
        return mainProfileActivity;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_noticeBoard) {

            startActivity(new Intent(getBaseContext(), NoticeBoardActivity.class));

        } else if (id == R.id.nav_post) {

            startActivity(new Intent(getBaseContext(), PostActivity.class));

        } else if (id == R.id.nav_login) {

            startActivity(new Intent(getBaseContext(), LoginActivity.class));

        } else if (id == R.id.nav_logout) {

            sharedPreferences = getSharedPreferences(LOGOUT,MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putString(LOGOUT,"LOGOUT");
            editor.commit();

            /*studentID.setText("STUDENT ID");
            studentName.setText("STUDENT NAME");
            studentImage.setImageResource(android.R.color.transparent);*/

            sharedPreferences = getSharedPreferences(SID,MODE_PRIVATE);

            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(SID,"Student Id");
            edit.commit();

            startActivity(new Intent(getBaseContext(),MainProfileActivity.class));
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*public void navigate(View view){

        switch(view.getId()){
            case R.id.homePageBtn:
                startActivity(new Intent(getBaseContext(), ProfileActivity.class));
                break;
            case R.id.placementBtn:
                startActivity(new Intent(getBaseContext(), PlacementActivity.class));
                break;
            case R.id.departmentsBtn:
                startActivity(new Intent(getBaseContext(), DepartmentsActivity.class));
                break;
            case R.id.libraryBtn:
                startActivity(new Intent(getBaseContext(), LibraryActivity.class));
                break;
            *//*case R.id.hostelBtn:
                startActivity(new Intent(getBaseContext(), HostelActivity.class));
                break;*//*
        }

    }*/

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public boolean hasInternetAccess(Context context) {
        if (isNetworkAvailable(context)) {
            try {
                HttpURLConnection urlc = (HttpURLConnection)
                        (new URL("http://clients3.google.com/generate_204")
                                .openConnection());
                urlc.setRequestProperty("User-Agent", "Android");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == 204 &&
                        urlc.getContentLength() == 0);
            } catch (IOException e) {
                Log.e("Internet", "Error checking internet connection", e);
            }
        } else {
            Log.d("Internet", "No network available!");
        }
        return false;
    }

}
