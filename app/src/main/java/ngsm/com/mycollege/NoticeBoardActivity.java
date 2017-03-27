package ngsm.com.mycollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NoticeBoardActivity extends AppCompatActivity {

    private TextView tvNoticeBoard;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_board_layout);

        tvNoticeBoard = (TextView) findViewById(R.id.tvNoticeBoard);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'NoticeBoard' node
        mFirebaseDatabase = mFirebaseInstance.getReference("NoticeBoard");

        mFirebaseDatabase.child("NB").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String noticeBoard = String.valueOf(dataSnapshot.getValue());

                tvNoticeBoard.setText(noticeBoard);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //tvNoticeBoard.setText(getIntent().getStringExtra("mytext"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nb_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_nb:
                startActivity(new Intent(getBaseContext(), NBLoginActivity.class));
                break;
        }
        return true;
    }

}
