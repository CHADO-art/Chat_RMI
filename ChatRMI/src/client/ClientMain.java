/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import chat.api.ChatServer;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author chado
 */
public class ClientMain {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez votre pseudo : ");
            String name = scanner.nextLine().trim();

            ChatClientImpl clientImpl = new ChatClientImpl(name);

            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            String url = "rmi://localhost:1099/ChatServer";
            ChatServer server = (ChatServer) Naming.lookup(url);

            server.registerClient(name, clientImpl);

            System.out.println("Connecté au serveur ! Tapez 'exit' pour quitter.");

            String message;
            while (true) {
                message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                server.broadcastMessage(name, message);
            }

            server.unregisterClient(name);
            System.out.println("Déconnecté.");

            System.exit(0); 

        } catch (Exception e) {
            System.err.println("Erreur du client : " + e.getMessage());
            e.printStackTrace();
        }
    }
}