package ngsm.com.mycollege;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    private EditText metSignInPassword;
    private EditText metSignInId;
    private Button mbtnSignIn;

    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Students");

        metSignInId = (EditText) findViewById(R.id.etSignInId);
        metSignInPassword = (EditText) findViewById(R.id.etSignInPassword);
        mbtnSignIn = (Button) findViewById(R.id.btnSignIn);

        mbtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkLogin();

            }
        });
    }

    private void checkLogin() {

        String id = metSignInId.getText().toString().trim();
        String password = metSignInPassword.getText().toString().trim();

        if(!TextUtils.isEmpty(id) && !TextUtils.isEmpty(password)){

            mAuth.signInWithEmailAndPassword(id, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        checkUserExist();

                    } else {
                        Toast.makeText(getBaseContext(), "Login Error", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }

    private void checkUserExist() {

        final String user_id = mAuth.getCurrentUser().getUid();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild(user_id)){

                    Intent postIntent = new Intent(getBaseContext(), PostActivity.class);
                    postIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(postIntent);

                } else {
                    Toast.makeText(getBaseContext(), "You are not registered as a student", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
