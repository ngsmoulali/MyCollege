package ngsm.com.mycollege;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditNoticeBoardActivity extends AppCompatActivity {

    private EditText etNB;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_notice_board_layout);

        etNB = (EditText) findViewById(R.id.etNB);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'NoticeBoard' node
        mFirebaseDatabase = mFirebaseInstance.getReference("NoticeBoard");

    }

    public void updateNB(View view){

        String nb = etNB.getText().toString();

        if (!TextUtils.isEmpty(nb))
            mFirebaseDatabase.child("NB").setValue(nb);

    }
}
