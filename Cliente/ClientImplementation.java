/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cliente;

/**
 *
 * @author J.Guzmán
 */
public class ClientImplementation implements ClientInterface{
    private User user;

    public ClientImplementation() {
    }    

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
    
}
