/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author chado
 */
public interface ChatClient extends Remote {
	void receiveMessage (String name, String message) throws RemoteException;
        
}