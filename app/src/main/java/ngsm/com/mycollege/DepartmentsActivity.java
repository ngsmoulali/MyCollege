package ngsm.com.mycollege;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import ngsm.com.mycollege.Adapter.PagerAdapterDept;


public class DepartmentsActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        getSupportActionBar().setElevation(0f);


        //Adding toolbar to the activity
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(
                tabLayout.newTab().setText("MBA")
        );
        tabLayout.addTab(tabLayout.newTab().setText("MCA"));
        tabLayout.addTab(tabLayout.newTab().setText("Commerce"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(ContextCompat.getColor(this,android.R.color.white ),ContextCompat.getColor(this,android.R.color.holo_orange_light));
        tabLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary) );

        /*tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.activity_mba));
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.activity_mca));*/
        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        PagerAdapterDept adapter = new PagerAdapterDept(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);

        //Adding onTabSelectedListener to swipe views
       tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab)  {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void mbaFaculty(View view){

        startActivity(new Intent(getBaseContext(), MbaFacultyActivity.class));

    }

    public void mcaFaculty(View view){

        startActivity(new Intent(getBaseContext(), McaFacultyActivity.class));

    }

    public void commerceFaculty(View view){

        startActivity(new Intent(getBaseContext(), CommerceFacultyActivity.class));

    }

}