/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author chado
 */
public class ServerMain {

    public static void main(String[] args) {
        
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("Registre RMI démarré sur le port 1099.");

            ChatServerImpl server = new ChatServerImpl();

            String url = "rmi://localhost:1099/ChatServer";
            Naming.rebind(url, server);

            System.out.println("Serveur de chat prêt. URL : " + url);

        }
        catch (Exception e) {
            System.err.println("Erreur grave du serveur : " + e.getMessage());
            e.printStackTrace();
        }
        
    }
    
}