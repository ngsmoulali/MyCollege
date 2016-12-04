package ngsm.com.mycollege;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class LibraryActivity extends AppCompatActivity {
    ViewPager viewPager;
    SwipeLibrary adapter;
    int count = 0;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        viewPager=(ViewPager)findViewById(R.id.vpLibrary);
        adapter=new SwipeLibrary(this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        // Timer for auto sliding
        timer  = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(count<=3){
                            viewPager.setCurrentItem(count);
                            count++;
                        }else{
                            count = 0;
                            viewPager.setCurrentItem(count);
                        }
                    }
                });
            }
        }, 500, 3000);

    }
}
