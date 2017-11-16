package com.litto.atm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private CheckBox cbUserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cbUserid = findViewById(R.id.cb_remember_userid);
        cbUserid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getSharedPreferences("abc", MODE_PRIVATE)
                        .edit()
                        .putBoolean("REMEMBER", isChecked)
                        .apply();
            }
        });
        boolean remember = getSharedPreferences("abc", MODE_PRIVATE)
                .getBoolean("REMEMBER", false);
        cbUserid.setChecked(remember);
        if (remember) {
            String userid = getSharedPreferences("abc", MODE_PRIVATE)
                    .getString("USERID", null);
            Log.d(TAG, "onCreate: " + userid);
            EditText edUserid = findViewById(R.id.ed_userid);
            edUserid.setText(userid);
        }
    }

    public void login(View view){
        EditText edUserid = findViewById(R.id.ed_userid);
        EditText edPasswd = findViewById(R.id.ed_passwd);
        String userid = edUserid.getText().toString();
        String passwd = edPasswd.getText().toString();
        //
        try {
            URL url = new URL("http://atm201605.appspot.com/login?uid="+userid+"&pw="+passwd);
            InputStream is = url.openStream();
            int data = is.read();
            Log.d(TAG, "login: "+ data);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*if ("jack".equals(userid) &&
                "1234".equals(passwd) ){
            getIntent().putExtra("USERID", userid);
            setResult(RESULT_OK, getIntent());
            finish();
        }*/
    }

    public void quit(View view){

    }
}
