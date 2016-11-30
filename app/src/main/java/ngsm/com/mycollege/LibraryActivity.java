package ngsm.com.mycollege;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LibraryActivity extends AppCompatActivity {

    private TextView mtvLibraryTimings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_layout);

        mtvLibraryTimings = (TextView) findViewById(R.id.tvLibraryTimings);

        mtvLibraryTimings.setText("9:00 AM To 5:00 PM");
    }
}
