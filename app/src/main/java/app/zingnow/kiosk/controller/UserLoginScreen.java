package app.zingnow.kiosk.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import app.zingnow.kiosk.R;

public class UserLoginScreen extends AppCompatActivity {

    EditText user_phone_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_screen);

        setUI();

    }
    public void setUI()
    {

        user_phone_edittext = findViewById(R.id.user_phoneno);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public void nextScreen(View view)
    {
        String user_phoneno = user_phone_edittext.getText().toString();
        if(user_phoneno.length()!=10)
        {
            Toast.makeText(this, "Please enter valid phone number", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(),UserDetailsScreen.class);
            startActivity(intent);
        }

    }
}