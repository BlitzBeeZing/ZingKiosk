package app.zingnow.kiosk.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.zingnow.kiosk.R;
import app.zingnow.kiosk.utils.TypeWriter;

public class ScreenSaver extends AppCompatActivity {


    //Defining views
    TextView company_name;
    RelativeLayout book_meal_btn;
    ImageView zing_logo;

    //Defining instances
    TypeWriter type_writer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_saver);
        //getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        //getWindow().setExitTransition(new Explode());



        setUI();
    }

        public void setUI()
        {

            company_name = findViewById(R.id.company_name);
            book_meal_btn = findViewById(R.id.book_meal_btn);
            zing_logo = findViewById(R.id.zing_logo);

            type_writer = (TypeWriter) findViewById(R.id.main_message);

            //Make transparent status bar
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Window w = getWindow();
                w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            }

            fetchDetails();
        }

        public void fetchDetails()
        {
            SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
            String location_name = sharedPreferences.getString("location_name", "");
            company_name.setText(location_name);
            setTypeWriter();
        }

        public void setTypeWriter()
        {
            type_writer.setText("");
            type_writer.setCharacterDelay(70);
            type_writer.animateText("Easy & Affordable\nMeals@Work.");
            startContinuousZoomAnimation();
        }
    private void startContinuousZoomAnimation() {
        // Define the initial and final scale values
        float initialScale = 1f;
        float finalScale = 1.2f;

        // Create ValueAnimator for continuous zooming
        ValueAnimator zoomAnimator = ValueAnimator.ofFloat(initialScale, finalScale);
        zoomAnimator.setDuration(1000); // Duration in milliseconds
        zoomAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        zoomAnimator.setRepeatCount(ValueAnimator.INFINITE);
        zoomAnimator.setRepeatMode(ValueAnimator.REVERSE);

        // Update the scale of the ImageView during animation
        zoomAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                zing_logo.setScaleX(animatedValue);
                zing_logo.setScaleY(animatedValue);
            }
        });

        // Start the animation
        zoomAnimator.start();
    }


    public void bookMeal(View view)
        {
            animateImage(view);
        }

    public void animateImage(View view) {
        RelativeLayout animatedImage = findViewById(R.id.book_meal_btn);

        // Create a scale animation for zooming in
        Animation scaleInAnimation = new ScaleAnimation(1, 0.8f, 1, 0.8f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleInAnimation.setDuration(150); // in milliseconds

        // Create an alpha animation for fading in
        Animation fadeInAnimation = new AlphaAnimation(1, 0.6f);
        fadeInAnimation.setDuration(120); // in milliseconds

        // Set up a listener for the animation set
        Animation.AnimationListener animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Animation started
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(getApplicationContext(),MenuPage.class);
                startActivity(intent);
                // Animation ended, perform any necessary actions

                // You can add additional actions here, such as starting a new activity or executing code.
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Animation repeated, if set to repeat
                Intent intent = new Intent(getApplicationContext(), MenuPage.class);
                startActivity(intent);
            }
        };

        // Combine the scale and alpha animations into a set
        Animation animationSet = new AnimationSet(false);
        ((AnimationSet) animationSet).addAnimation(scaleInAnimation);
        ((AnimationSet) animationSet).addAnimation(fadeInAnimation);
        animationSet.setAnimationListener(animationListener);

        // Start the animation set
        animatedImage.startAnimation(animationSet);
    }










    }




