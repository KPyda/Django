/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eltharis.wsn.classes;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.ElementArray;

/**
 * Tablica userów do deserializacji w show_all
 * @author eltharis
 */
@Root
public class UserArray {
    @ElementArray(name="users")
    private User users[];
    
    public User[] getUsers(){
        return users;
    }
}
