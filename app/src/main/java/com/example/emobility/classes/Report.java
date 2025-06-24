package com.example.emobility.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Report implements Parcelable {
    private static int count = 0;
    private int reportID;   // ID
    private String regard;  // Betreff
    private String context; // Inhalt
    private boolean stateOf; // Funktionsfähig oder nicht
    private int ladesauleID; // Ladesäule der Betroffen ist

    public Report(String regard, String context, boolean stateOf, int ladesauleID) {
        this.reportID = count;
        this.regard = regard;
        this.context = context;
        this.stateOf = stateOf;
        this.ladesauleID = ladesauleID;
        count++;
    }


    protected Report(Parcel in) {
        reportID = in.readInt();
        regard = in.readString();
        context = in.readString();
        stateOf = in.readByte() != 0;
        ladesauleID = in.readInt();
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getRegard() {
        return regard;
    }

    public void setRegard(String regard) {
        this.regard = regard;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public boolean getStateOf() {
        return stateOf;
    }

    public void setStateOf(boolean stateOf) {
        this.stateOf = stateOf;
    }

    public int getLadesauleID() {
        return ladesauleID;
    }

    public void setLadesauleID(int ladesauleID) {
        this.ladesauleID = ladesauleID;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(reportID);
        parcel.writeString(regard);
        parcel.writeString(context);
        parcel.writeByte((byte) (stateOf ? 1 : 0));
        parcel.writeInt(ladesauleID);
    }


    //private Ladesäulen


}
