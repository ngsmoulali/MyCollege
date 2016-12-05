package ngsm.com.mycollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlacementActivity extends AppCompatActivity {

    private String TAG = PlacementActivity.class.getSimpleName();
    private ListView mlvPlacement;
    private String[] placementBrief = new String[]{"Placement History", "Tie up Companies"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placement_layout);

        mlvPlacement = (ListView) findViewById(R.id.lvPlacement);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, placementBrief);
        mlvPlacement.setAdapter(adapter);

        mlvPlacement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                if(position == 0){
                    startActivity(new Intent(getBaseContext(), PlacementHistoryActivity.class));
                } else {
                    startActivity(new Intent(getBaseContext(), LibraryActivity.class));
                }
            }
        });

    }
}
