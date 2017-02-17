package by.wink.ltmfirst.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import by.wink.ltmfirst.R;

/**
 * Created by amine on 10/02/17.
 */

public class LoginActivity extends Activity implements View.OnClickListener{

    EditText usernameET;
    EditText passwordET;

    public static final String EMAIL_KEY = "email";

    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // link activity to layout
        setContentView(R.layout.activity_login);
        // assign xml items
        usernameET = (EditText) findViewById(R.id.login_username_et);
        passwordET = (EditText) findViewById(R.id.login_pwd_et);
        loginBtn = (Button) findViewById(R.id.login_btn);

        if(getIntent() != null){
            if(getIntent().getAction().equals(Intent.ACTION_SEND)) {

                usernameET.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
            }

        }


        loginBtn.setOnClickListener(this);

    }


    private boolean doLogin(String username, String password){

        // do something

        return true;

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.login_btn){
            String username = usernameET.getText().toString();
            String password = passwordET.getText().toString();
            if (doLogin(username, password)) {
                Toast.makeText(LoginActivity.this, getString(R.string.login_success), Toast.LENGTH_SHORT)
                        .show();
                // explict intent : this Activity , ComponentName to create
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra(EMAIL_KEY,username);
                intent.putExtra("age",1);

                startActivity(intent);
                finish();
            }

        }


    }
}
