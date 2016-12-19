package ngsm.com.mycollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText metLoginId, metPassword;
    private Button btnLogin;

    private Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://college-7a896.firebaseio.com");

        metLoginId = (EditText) findViewById(R.id.etLoginId);
        metPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void login(View view){

        if(TextUtils.isEmpty(metLoginId.getText().toString())){
            metLoginId.setError("Please Enter Your Student ID");
        } else if(TextUtils.isEmpty(metPassword.getText().toString())) {
            metPassword.setError("Please Enter Your Password");
        } else {
                Firebase sRef = ref.child("studentIds");
                sRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean loginId = false;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String studentId = ds.getValue(String.class);
                            if (metLoginId.getText().toString().equals(studentId)) {
                                loginValidation();
                                loginId = true;
                                break;
                            }
                        }
                        if (loginId == false)
                            metLoginId.setError("Invalid Student ID");
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
        }
    }

    public void loginValidation(){
        Firebase cRef = ref.child("studentDetails").child(metLoginId.getText().toString());
        cRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StudentDetails studentDetails = dataSnapshot.getValue(StudentDetails.class);
                String password = studentDetails.getPassword();
                if(metPassword.getText().toString().equals(password)){
                    startActivity(new Intent(getBaseContext(), MainProfileActivity.class));
                } else {
                    metPassword.setError("Wrong Password");
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
