/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import chat.api.ChatClient;
import chat.api.ChatServer;

/**
 *
 * @author chado
 */
public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
	private final Map<String, ChatClient> clients = new ConcurrentHashMap<>();
	
	public ChatServerImpl() throws RemoteException {
		super();
	}
	
	@Override
	public synchronized void registerClient(String name, ChatClient client) throws RemoteException {
		System.out.println("[Serveur] " + name + " se connecte.");
		clients.put(name, client);
		broadcastMessage("[Serveur]", name + " a rejoint le chat !");
	}
	
	@Override
    public synchronized void unregisterClient(String name) throws RemoteException {
        System.out.println("[Serveur] " + name + " se déconnecte.");
        clients.remove(name);
        broadcastMessage("[Serveur]", name + " a quitté le chat.");
    }
	
@Override
    public synchronized void broadcastMessage(String name, String message) throws RemoteException {
        System.out.println("[Serveur] Diffusion de <" + name + ">: " + message);

        // Boucle sur tous les clients pour leur envoyer le message
        // On utilise .entrySet() pour une itération sécurisée
        for (Map.Entry<String, ChatClient> entry : clients.entrySet()) {
            ChatClient client = entry.getValue();
            try {
                // Appel de la méthode distante (callback) sur le client
                client.receiveMessage(name, message);
            } catch (RemoteException e) {
                // Si le client est injoignable (plantage, déconnexion...),
                // on le supprime de la liste.
                System.err.println("Erreur en contactant " + entry.getKey() + ". Suppression.");
                clients.remove(entry.getKey());
            }
        }
    }
}