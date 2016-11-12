/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import java.rmi.Remote;

/**
 *
 * @author J.Guzmán
 */
public interface ClientInterface extends Remote{
    public User getUser();
    public void setUser(User user);
}
