package app.zingnow.kiosk.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import app.zingnow.kiosk.R;

public class UserDetailsScreen extends AppCompatActivity {
    EditText username_edittext,useraddress_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_screen);

        setUI();
    }
    public void setUI()
    {

        username_edittext = findViewById(R.id.user_name);
        useraddress_edittext = findViewById(R.id.user_address);

        String username = username_edittext.getText().toString();
        String useraddress = useraddress_edittext.getText().toString();

        if(username.length()<=2 && useraddress.length()<=3)
        {
            Toast.makeText(this, "Please enter valid details", Toast.LENGTH_SHORT).show();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}