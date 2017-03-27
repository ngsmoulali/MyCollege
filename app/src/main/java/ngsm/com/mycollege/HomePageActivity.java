package ngsm.com.mycollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_layout);
    }

    public void navigate(View view){

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
        }

    }

}
