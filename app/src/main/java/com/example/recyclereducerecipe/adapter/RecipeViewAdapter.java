package com.example.recyclereducerecipe.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclereducerecipe.R;
import com.example.recyclereducerecipe.model.Recipe;
import java.util.ArrayList;

public class RecipeViewAdapter extends RecyclerView.Adapter<RecipeViewAdapter.DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Recipe> mDataset;
    private static MyClickListener myClickListener;

    String[] recipename = {"Burrito", "Chicken Sandwhich", "Hamburger", "Greek Salad", "Manacotti", "Pizza", "Roast_Beef"};
    int[] imageV = {R.drawable.burrito, R.drawable.chickensandwhich, R.drawable.hamburger,
            R.drawable.greeksalad, R.drawable.manacotti, R.drawable.pizza, R.drawable.roastbeef};
    String[] burrito = {"chicken\n ", "tortillas\n ", "avocado\n ", "tomatoes\n", "green onion\n", "cheese\n", "beans\n", "rice\n"};
    int[] imageB = {R.drawable.chopchicken, R.drawable.tortillas, R.drawable.avocado, R.drawable.tomatoe, R.drawable.greenonion,
            R.drawable.cheese, R.drawable.beans, R.drawable.rice};

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView label;
        TextView dateTime;
        ImageView picture;
        ImageView picture2;
        TextView nameTextView;
        Button messageButton;


        public DataObjectHolder(final View itemView) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.foodimageView);
            picture2 = (ImageView) itemView.findViewById(R.id.foodimage);
            label = (TextView) itemView.findViewById(R.id.mealtextView);
            dateTime = (TextView) itemView.findViewById(R.id.descrip_textView);
            Button backButton = (Button) itemView.findViewById(R.id.reset_button);
            Button deleteButton = (Button) itemView.findViewById(R.id.delete_button);
            Button editButton = (Button) itemView.findViewById(R.id.edit_button);
            Button nextButton = (Button) itemView.findViewById(R.id.bbutton);
            Button previousButton = (Button) itemView.findViewById(R.id.pbutton);
            nameTextView = (TextView) itemView.findViewById(R.id.descrip_textView);
            messageButton = (Button) itemView.findViewById(R.id.edit_button);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);

            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            myClickListener.onItemClick(position, itemView);
                        }
                    }
                }
            });
        }
        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public RecipeViewAdapter(ArrayList<Recipe> myDataset) {
        mDataset = myDataset;
    }
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }
    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        for (int i = 0; i < 10; i++)
            if (i == 0) {
                holder.label.setText(mDataset.get(position).getRecipeName());
                holder.dateTime.setText(mDataset.get(position).getRecipeIngredients());
                holder.picture.setImageResource(imageB[position]);
            }
    }
    public void addItem(Recipe label, int index) {
        mDataset.add(index, label);
        notifyItemInserted(index);
    }
    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
