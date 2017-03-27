package ngsm.com.mycollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditableNoticeBoardActivity extends AppCompatActivity {

    private EditText etNoticeBoard;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editable_notice_board_layout);

        etNoticeBoard = (EditText) findViewById(R.id.etNoticeBoard);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'NoticeBoard' node
        mFirebaseDatabase = mFirebaseInstance.getReference("NoticeBoard");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_nb_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_save_nb:
                String nb = etNoticeBoard.getText().toString();

                if (!TextUtils.isEmpty(nb))
                    mFirebaseDatabase.child("NB").setValue(nb);

                Intent intent = new Intent(getBaseContext(), NoticeBoardActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }

}
