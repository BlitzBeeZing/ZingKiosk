package app.zingnow.kiosk.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import app.zingnow.kiosk.R;
import app.zingnow.kiosk.adapters.CartAdapter;
import app.zingnow.kiosk.model.Menu;

public class CartScreen extends AppCompatActivity implements CartAdapter.CartAdapterListener{

    //Defining views
    TextView total_price;
    RelativeLayout checkout;
    RecyclerView cart_recyclerview;

    //Deifining important variables
    CartAdapter cart_adapter;
    ArrayList<Menu> received_list;
    int item_total;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_screen);

        setUI();
    }

    public void setUI()
    {

        total_price = findViewById(R.id.total_price);
        cart_recyclerview = findViewById(R.id.cart_recyclerview);

        item_total = 0;


        cart_recyclerview.setLayoutManager(new LinearLayoutManager(this));



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }


        received_list = (ArrayList<Menu>) getIntent().getSerializableExtra("menuList");

            cart_adapter = new CartAdapter(received_list);
            cart_adapter.setListener(this);
            cart_recyclerview.setAdapter(cart_adapter);
            cart_adapter.notifyDataSetChanged();

            for (Menu menu : received_list) {
                item_total = item_total + Integer.parseInt(menu.getPrice());
            }
            total_price.setText("₹" + item_total);

    }




    public void checkOut(View view)
    {
        Intent intent = new Intent(getApplicationContext(), UserLoginScreen.class);
        startActivity(intent);
    }
    public void onItemAddedOrRemoved(int position, Boolean is_added) {
        // Update the total price text here
        if(position<received_list.size())
        {
            Menu menu = received_list.get(position);
            Log.e("Menu Update", menu.getName() + is_added + position);

            updateTotalPrice();
        }
        if(received_list.isEmpty())
        {
            Intent intent = new Intent(getApplicationContext(), MenuPage.class);
            startActivity(intent);
        }

    }

    private void updateTotalPrice() {
        if(received_list.isEmpty())
        {
            Intent intent = new Intent(getApplicationContext(), MenuPage.class);
            startActivity(intent);
        }
        else {
            int total = 0;
            for(Menu menu:received_list)
            {
                total += Integer.parseInt(menu.getPrice())*menu.getQuantity();
            }
            total_price.setText("₹" + total);
        }


    }


}