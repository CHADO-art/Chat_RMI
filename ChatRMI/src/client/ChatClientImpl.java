/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import chat.api.ChatClient;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 *
 * @author chado
 */
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
	private final String name;
	
	public ChatClientImpl(String name) throws RemoteException {
		super();
		this.name = name;
	}
	
	@Override
	public void receiveMessage(String name, String message) throws RemoteException {
		if (!name.equals(this.name)) {
			System.out.println("<" + name + ">: " + message);
		}
	}
}