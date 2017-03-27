package ngsm.com.mycollege;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static ngsm.com.mycollege.LoginActivity.LOGOUT;
import static ngsm.com.mycollege.LoginActivity.SID;
import static ngsm.com.mycollege.LoginActivity.SIMAGE;
import static ngsm.com.mycollege.LoginActivity.SNAME;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    private SharedPreferences sharedPreferences;

    private String logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences(LOGOUT,MODE_PRIVATE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        logout = sharedPreferences.getString(LOGOUT, null);

        /*if(LOGOUT.equals(logout)){

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);

        } else {*/

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, MainProfileActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);

        //}

    }
}
