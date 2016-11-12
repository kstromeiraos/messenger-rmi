/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cliente;

import java.util.ArrayList;

/**
 *
 * @author J.Guzmán
 */
public class User {
    private String email;
    private String password;
    private String ip;
    private boolean online;
    private ArrayList<User> friends;
    //private ArrayList<User> requests;

    public User(String email, String password, String ip, ArrayList<User> friends) {
        this.email = email;
        this.password = password;
        this.ip = ip;
        this.friends = friends;
        online = true;
    }

    public User(String email) {
        this.email = email;
    }
    
    public User() {
        online = true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
