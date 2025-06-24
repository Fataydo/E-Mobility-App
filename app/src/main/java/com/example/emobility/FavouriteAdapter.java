package com.example.emobility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emobility.classes.Favourite;
import com.example.emobility.classes.Report;
import com.example.emobility.classes.Verwalter;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder1> {
    Context context;
    ArrayList<Favourite> favouriteArrayList;


    public FavouriteAdapter(Context ct, ArrayList<Favourite> favouriteArrayList) {
        this.context = ct;
        this.favouriteArrayList = favouriteArrayList;
    }

    @NonNull
    @Override
    public FavouriteAdapter.MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //This is where you infalte the layout (Giving a look to our rows)
        //Our Global CustomOnItemClickLister
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row_favourite,parent, false);
        return new MyViewHolder1(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.MyViewHolder1 holder, int position) {
        // assigning values to the views we created in the recycler_view_row layout file
        // based on the position of the recycler view
        //holder.subjectView.setText(reportArrayList.get(position).getLadesaeulen().getBetreiber()); <- Das hier kann dann der Titel sein
        holder.contextView.setText("Click for more Details"); // Ich weiß nicht ob man hier ein Beschreibung öffnen sollte, --> habe ich gemacht :D
        int temp = favouriteArrayList.get(position).getLadesauleID();
        holder.subjectView.setText(Verwalter.getLadesaeule().get(temp).getBetreiber()); // Hiernochmal schauen was wir hier machen
    }

    /**
     * @brief Gibt die Anzahl an Items die gezeigt werden
     * @return integer
     */
    @Override
    public int getItemCount() {
        // the Recycler view just wants to know the number of items you want displayed
        return favouriteArrayList.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        //grabing our Views and assigning them in variables

        ImageView imageView;
        TextView subjectView, contextView;
        Button deleteButton;

        //this is our local global variable customListener that got the value of the Adapter
        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewRV1);
            contextView = itemView.findViewById(R.id.textviewFavouriteTitelRV);
            deleteButton = itemView.findViewById(R.id.deleteButtonFavourite);
            subjectView = itemView.findViewById(R.id.textviewFavouriteTitelRV);
            //Listener

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    favouriteArrayList.remove(getAdapterPosition());
                    //Verwalter.getFavmarkers().remove(getAdapterPosition());
                    //Verwalter.getRemoveElements().add(Verwalter.getFavmarkers().get(getAdapterPosition()).getId());
                    Verwalter.setFavouritenListe(favouriteArrayList);


                    int position = getAdapterPosition();
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(),favouriteArrayList.size());
                    Toast.makeText(view.getContext(), "Deleted Favourite: " + position, Toast.LENGTH_SHORT).show(); // hier nochmal hineinschauen

                }
            });
        }

    }
}