package app.zingnow.kiosk.adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.zingnow.kiosk.R;
import app.zingnow.kiosk.model.Menu;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.OuterViewHolder> {
    List<Menu> menu_items;

    public MenuAdapter(List<Menu> menu_items)
    {
        this.menu_items = menu_items;
    }


    @NonNull
    @Override
    public MenuAdapter.OuterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_items, parent, false);
        return new OuterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.OuterViewHolder holder, int position) {

        Menu menu = menu_items.get(position);

        holder.item_name.setText(menu.getName());
        holder.item_price.setText("₹ "+menu.getPrice());
        holder.add_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.setSelected(!menu.getSelected());

                if(menu.getSelected())
                {
                    holder.add_text.setText("ADDED");
                    holder.add_layout.setBackgroundResource(R.drawable.green_background_rounded);
                }
                else
                {
                    holder.add_text.setText("ADD");
                    holder.add_layout.setBackgroundResource(R.drawable.orange_background_rounded);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return menu_items.size();
    }

    public class OuterViewHolder extends RecyclerView.ViewHolder {

        TextView item_name,item_price,add_text;
        RelativeLayout add_layout;

        public OuterViewHolder(@NonNull View itemView) {
            super(itemView);

            item_name = itemView.findViewById(R.id.item_name);
            item_price = itemView.findViewById(R.id.item_price);
            add_text = itemView.findViewById(R.id.add_text);
            add_layout = itemView.findViewById(R.id.add_layout);

        }
    }
}
