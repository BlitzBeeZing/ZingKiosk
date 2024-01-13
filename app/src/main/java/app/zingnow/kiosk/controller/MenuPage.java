package app.zingnow.kiosk.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.zingnow.kiosk.R;
import app.zingnow.kiosk.adapters.MenuAdapter;
import app.zingnow.kiosk.model.Menu;

public class MenuPage extends AppCompatActivity {

    //Defining Views
    RelativeLayout view_cart;
    RecyclerView menu_recyclerview;
    MenuAdapter menu_adapter;
    List<Menu> menu_items = new ArrayList<>();

    //Defining Instances
    private static final long TIMEOUT_DURATION = 100000L; // 3 seconds
    private static final int ACTION_LOGOUT = 1;

    private final Handler timeoutHandler = new Handler();
    private final Runnable timeoutRunnable = new Runnable() {
        @Override
        public void run() {
            logoutUser();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        setUI();
    }

    public void setUI()
    {
        view_cart = findViewById(R.id.view_cart);
        menu_recyclerview = findViewById(R.id.menu_recycler_view);
        menu_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        menu_recyclerview.setLayoutManager(gridLayoutManager);
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        menu_recyclerview.setLayoutAnimation(animation);

        //Make transparent status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        fetchMenuItems();

    }

    public void fetchMenuItems()
    {
        for(int i=1;i<7;i++)
        {
            Menu e = new Menu(i+"", "Paneer Tikka Masala Rice Bowl","","89","Okane Foods",false,false,1);
            menu_items.add(e);
        }
        menu_adapter = new MenuAdapter(menu_items);
        menu_recyclerview.setAdapter(menu_adapter);
        menu_adapter.notifyDataSetChanged();


    }

    public void viewCart(View view)
    {
        List<Menu> selectedItems = getSelectedItems(menu_items);
        for (Menu selectedItem : selectedItems) {
            Log.e("Selected Item", selectedItem.getName() + " : "+  selectedItem.getId());
        }
        if(selectedItems.size()==0)
        {
            Toast.makeText(this, "Add some items first", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(),CartScreen.class);
            intent.putExtra("menuList", (Serializable) selectedItems);
            startActivity(intent);
        }


    }

    private List<Menu> getSelectedItems(List<Menu> menuList) {
        List<Menu> selectedItems = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getSelected()) {
                selectedItems.add(menu);
            }
        }
        return selectedItems;
    }




    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        resetTimeout();
    }

    private void resetTimeout() {
        timeoutHandler.removeCallbacks(timeoutRunnable);
        timeoutHandler.postDelayed(timeoutRunnable, TIMEOUT_DURATION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetTimeout();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timeoutHandler.removeCallbacks(timeoutRunnable);
    }

    private void logoutUser() {
        Intent intent = new Intent(this, CartScreen.class);  // Change it back to screen saver
        startActivity(intent);
    }

}