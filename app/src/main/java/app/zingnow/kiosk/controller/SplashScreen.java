package app.zingnow.kiosk.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.zingnow.kiosk.R;

public class SplashScreen extends AppCompatActivity {
    //Defining required instances
    LottieAnimationView lottie_animation_view;
    FirebaseAuth m_auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setUI();



    }

    private void setUI()
    {
        lottie_animation_view = findViewById(R.id.lottieAnimationView);
        m_auth = FirebaseAuth.getInstance();

        //Make transparent status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        initialiseLottie();
    }

    private void initialiseLottie()
    {
        lottie_animation_view.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                // Check if access token is present in SharedPreferences
                if (isAccessTokenPresent()) {
                    // If the access token exists, go to MainActivity
                    Intent intent = new Intent(getApplicationContext(), MenuPage.class);
                    startActivity(intent);
                    finish(); // Close the splash screen so it's not in the back stack
                } else {
                    // If the access token doesn't exist, go to LoginPage
                    Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                    startActivity(intent);
                    finish(); // Close the splash screen so it's not in the back stack
                }
            }
        });

        lottie_animation_view.playAnimation(); // Ensure the animation plays
    }

    private boolean isAccessTokenPresent() {
        if(m_auth.getCurrentUser()==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


}