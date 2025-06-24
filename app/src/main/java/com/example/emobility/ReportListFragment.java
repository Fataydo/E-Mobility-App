package com.example.emobility;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.emobility.classes.Report;
import com.example.emobility.classes.Verwalter;


public class ReportListFragment extends Fragment implements MyAdapter.CustomOnItemClickerListener {
    ArrayList<Report> reportArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView emptyView;
    SharedViewModel sharedViewModel;

    public ReportListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_report_list, container, false);
        //getData(); //Wäre für die ModelView gedacht, das leider nicht funktioniert
        return rootView;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewRL);
        emptyView = (TextView) view.findViewById(R.id.empty_view);

        // Create Layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        /*
        //Looks into the Bundle and shows if there is any Cont into it
        Bundle bundle = getArguments();
        if(bundle != null) {
            Report report = (Report) bundle.getSerializable("report Object");
            reportArrayList.add(report);
        } else {
            Toast.makeText(getContext(), "Doesnt have information into it", Toast.LENGTH_SHORT).show();
        }
        */
        for (int i = 0; i < Verwalter.getReportListe().size(); i++) {
            Log.d("reportList","Report ID: " + String.valueOf(Verwalter.getReportListe().get(i).getReportID()));
        }
        if (Verwalter.getReportListe().isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        if(Verwalter.getReportListe().size() == 0 ) {
            MyAdapter myAdapter = new MyAdapter(getContext(),null, this);
            recyclerView.setAdapter(myAdapter);
        } else {
            for (int i = 0; i < Verwalter.getReportListe().size(); i++) {
                Log.d("reportList","Report ID: " + String.valueOf(Verwalter.getReportListe().get(i).getReportID()));
            }
            // define an adapter
            // this is a referenz of the interface CustomOnMyClickListener Adapter in the class MyAdapter
            MyAdapter myAdapter = new MyAdapter(getContext(),Verwalter.getReportListe(),this);
            recyclerView.setAdapter(myAdapter);
        }
    }

    @Override
    public void customOnItemClick(int position) {
        Log.d("reportInformationActivty" , "Activty started");
        Report report = Verwalter.getReportListe().get(position);
        Intent intent = new Intent(getContext() , ReportInformationActivity.class);
        intent.putExtra("report_object", report);
        startActivity(intent);
    }

    /*
    public void getData() {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getSelectedItem().observe(getViewLifecycleOwner(), new Observer<Report>() {
            @Override
            public void onChanged(Report report) {
                if (report != null ) {
                    reportArrayList.add(report);
                    Log.d("getDataReport", "Context" + report.getContext());
                    Toast.makeText(getContext(), "Information has been passed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    */
}