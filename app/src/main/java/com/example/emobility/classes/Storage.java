package com.example.emobility.classes;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;


//HIER NOCHMAL UNBEDINGT ANSCHAUEN
public class Storage {
    private List<User> userList = new ArrayList<>();

    public Storage() {

    }
    public boolean checkUser(String username, String password) {
        boolean flagFound = false;
        Log.d("storageUser", username + ":" + password);
        String tempUser = null;
        String tempPassword = null;
        for(int i = 0; i < userList.size(); i++) {
             Log.d("StorageUser", userList.get(i).getUsername() + ":" + userList.get(i).getPassword() );
             //Überprüft ob Username und Password identisch sind, wenn ja Gefunden
             if (userList.get(i).getUsername().contains(username) && userList.get(i).getPassword().contains(password)) {
                Log.d("StorageUser", "User has been found");
                flagFound = true;
                break;
            }
        }
        /*
        for(int i = 0; i < developersList.size(); i++) {
            Log.d("StorageUser", developersList.get(i).getUsername() + ":" + developersList.get(i).getPassword() );
            if (developersList.get(i).getUsername().contains(username) && developersList.get(i).getPassword().contains(password)) { //
                Log.d("StorageUser", "User has been found");
                flagFound = true;
                break;
            }
        }
         */
        if (flagFound == false) {
            Log.d("USER","User can't be found");
        }
        return flagFound; //default return value ist false
    }

/*
    public boolean getDevelopersList(String name) {
        boolean state = false;
        for(int i = 0; i < developersList.size(); i++) {
            if (developersList.get(i).getUsername().contains(name) == true)
                state = true;

        }
        return state;
    }
 */
    public boolean isItDeveloper(String name) {
        boolean foundDeveloper = false;
        for(int i = 0; i < userList.size(); i++) {
            if (userList.get(i) instanceof Developer && userList.get(i).getUsername().contains(name) && userList.get(i).getUserView() == false) {
                foundDeveloper = true;
                break;
            }
        }
        return foundDeveloper;
    }

    public void setDevelopersList(Developer developer) {
        this.userList.add(developer);
    }

    public void setUserList(User user) {
        this.userList.add(user);
    }
}
