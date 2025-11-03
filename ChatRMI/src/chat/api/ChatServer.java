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
public interface ChatServer extends Remote {
	void registerClient(String name, ChatClient client) throws RemoteException;
	void broadcastMessage(String name, String message) throws RemoteException;
	void unregisterClient(String name) throws RemoteException;

}