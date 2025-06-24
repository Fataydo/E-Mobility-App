package com.example.emobility;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emobility.classes.Report;
import com.example.emobility.classes.Verwalter;

public class ReportInformationActivity extends AppCompatActivity {
    TextView textview;  // Report ID
    TextView textview1; //Titel
    TextView textview2; //Funktionierbar
    TextView textview3; // Kontext
    TextView textview4; // Station ID
    TextView textview5; // Betreiber
    TextView textview6; // Strasse
    TextView textview7; // Hausnummer
    TextView textview8; // Postleitzahl
    TextView textview9; // Ort
    TextView textview10; // Koordinate
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_information);
        getID();
        if (getIntent().hasExtra("report_object")) {
            Report report = getIntent().getParcelableExtra("report_object");
            int reportID = report.getLadesauleID();
            if (reportID >= 0 && reportID <= Verwalter.getLadesaeule().size()-1) {
                Log.d("reportID", String.valueOf(report.getReportID()));
                textview.setText(getResources().getString(R.string.reportInformationReportID) + String.valueOf(report.getReportID()));
                textview1.setText(getResources().getString(R.string.reportInformationRegard) + report.getRegard());
                textview2.setText(getResources().getString(R.string.reportInformationFunctionable) + Boolean.toString(report.getStateOf()));
                textview3.setText(getResources().getString(R.string.reportInformationContext) + report.getContext());
                textview4.setText(getResources().getString(R.string.reportInformationStationID)+ String.valueOf(Verwalter.getLadesaeule().get(reportID).getLadesauleID()));
                textview5.setText(getResources().getString(R.string.reportInformationOperator)+ Verwalter.getLadesaeule().get(reportID).getBetreiber());
                textview6.setText(getResources().getString(R.string.reportInformationStreet) + Verwalter.getLadesaeule().get(reportID).getStrasse());
                textview7.setText(getResources().getString(R.string.reportInformationHouseNumber) + Verwalter.getLadesaeule().get(reportID).getHausnummer());
                textview8.setText(getResources().getString(R.string.reportInformationPostCode) + String.valueOf(Verwalter.getLadesaeule().get(reportID).getPostleitzahl()));
                textview9.setText(getResources().getString(R.string.reportInformationLocation) + Verwalter.getLadesaeule().get(reportID).getOrt());
                textview10.setText(getResources().getString(R.string.reportInformationLongitude)
                        + Double.toString(Verwalter.getLadesaeule().get(reportID).getLaengengrad()) + getResources().getString(R.string.reportInformationLatitude)
                         + Double.toString(Verwalter.getLadesaeule().get(reportID).getBreitengrad()));

            } else {
                Log.d("reportInformation", "There is no object in report List ");
                Toast.makeText(ReportInformationActivity.this,"Station can't be found",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getID() {
        textview = findViewById(R.id.textViewReportInformation); // Report ID
        textview1 = findViewById(R.id.textView1ReportInformation); // Subject Titel
        textview2 = findViewById(R.id.textView2ReportInformation); // Funktionierbar
        textview3 = findViewById(R.id.textView3ReportInformation); // Kontext
        textview4 = findViewById(R.id.textView4ReportInformation); // Station ID
        textview5 = findViewById(R.id.textView5ReportInformation); // Betreiber
        textview6 = findViewById(R.id.textView6ReportInformation); // Strasse
        textview7 = findViewById(R.id.textView7ReportInformation); // Hausnummer
        textview8 = findViewById(R.id.textView8ReportInformation); // Postleitzahl
        textview9 = findViewById(R.id.textView9ReportInformation); // Ort
        textview10 = findViewById(R.id.textView10ReportInformation); // Koordinate

    }
}