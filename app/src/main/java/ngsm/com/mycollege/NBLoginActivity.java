package ngsm.com.mycollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class NBLoginActivity extends AppCompatActivity {

    private EditText etNBLoginID, etNBPassword;

    private String login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nblogin_layout);

    }

    public void nbSubmit(View view){

        etNBLoginID = (EditText) findViewById(R.id.etNBLoginID);
        etNBPassword = (EditText) findViewById(R.id.etNBPassword);

        login = etNBLoginID.getText().toString();
        password = etNBPassword.getText().toString();

        if(TextUtils.isEmpty(login)){
            etNBLoginID.setError("Please Enter Your Login ID");
        } else if(TextUtils.isEmpty(password)) {
            etNBPassword.setError("Please Enter Your Password");
        } else {
            boolean loginId = false;
                if (login.equals("principal")) {
                    nbLoginValidation();
                    loginId = true;
                }
                if (loginId == false)
                    etNBLoginID.setError("Invalid Login ID");
        }
    }

    public void nbLoginValidation(){

        if(etNBPassword.getText().toString().equals("principal")){
            startActivity(new Intent(getBaseContext(), EditableNoticeBoardActivity.class));
            finish();
        } else {
            etNBPassword.setError("Wrong Password");
        }

    }

}
