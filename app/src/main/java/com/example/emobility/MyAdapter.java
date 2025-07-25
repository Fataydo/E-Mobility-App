package com.example.emobility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.emobility.classes.Report;
import com.example.emobility.classes.Verwalter;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Report> reportArrayList;
    private CustomOnItemClickerListener myListener;

    public interface CustomOnItemClickerListener {
        void customOnItemClick(int position);
    }


    public MyAdapter(Context ct, ArrayList<Report> reportArrayList, CustomOnItemClickerListener myListenerTemp) {
        this.context = ct;
        this.reportArrayList = reportArrayList;
        this.myListener = myListenerTemp;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //This is where you infalte the layout (Giving a look to our rows)
        //Our Global CustomOnItemClickLister
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reycler_view_row,parent, false);
        return new MyAdapter.MyViewHolder(view, myListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        // assigning values to the views we created in the recycler_view_row layout file
        // based on the position of the recycler view
        //holder.subjectView.setText(reportArrayList.get(position).getLadesaeulen().getBetreiber()); <- Das hier kann dann der Titel sein
        holder.contextView.setText("Click for more Details"); // Ich weiß nicht ob man hier ein Beschreibung öffnen sollte, --> habe ich gemacht :D
        holder.subjectView.setText(reportArrayList.get(position).getRegard());
        holder.StateView.setText("Functionable:" + Boolean.toString(reportArrayList.get(position).getStateOf()));
    }

    /**
     * @brief Gibt die Anzahl an Items die gezeigt werden
     * @return reportArrayList.size
     */
    @Override
    public int getItemCount() {
        // the Recycler view just wants to know the number of items you want displayed
        return reportArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //grabing our Views and assigning them in variables

        ImageView imageView;
        TextView subjectView, contextView, StateView;
        Button deleteButton;

        //this is our local global variable customListener that got the value of the Adapter
        CustomOnItemClickerListener customListener;
        public MyViewHolder(@NonNull View itemView, CustomOnItemClickerListener customListenerTemp) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewRV);
            subjectView = itemView.findViewById(R.id.textViewRV);
            contextView = itemView.findViewById(R.id.textViewRV2);
            StateView = itemView.findViewById(R.id.textViewRV3);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            //Listener
            this.customListener = customListenerTemp;
            itemView.setOnClickListener(this);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reportArrayList.remove(getAdapterPosition());
                    Verwalter.setReportListe(reportArrayList);

                    int position = getAdapterPosition();
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(),reportArrayList.size());
                    Toast.makeText(view.getContext(), "Deleted Report: " + position, Toast.LENGTH_SHORT).show(); // hier nochmal hineinschauen

                }
            });

            //

        }

        @Override
        public void onClick(View view) {
            customListener.customOnItemClick(getAdapterPosition());
        }
    }
}
