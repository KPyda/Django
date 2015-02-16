/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eltharis.wsn.classes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Klasa userów
 * @author eltharis
 */
@Root
public class User {

    @Element
    private int id;
    @Element
    private String username;
    @Element(required = false)
    private String first_name;
    @Element(required = false)
    private String last_name;
    @Element(required = false)
    private String last_login;

    public User() {

    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getLast_login() {
        return last_login;
    }

    @Override
    public String toString() {
        return username;
    }
}
