/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class SocketServerThread extends Thread{
    
    private final int port = 25565;
    private ServerSocket serverSocket;
    
    @Override
    public void run() {
        try{
            serverSocket = new ServerSocket(port);
            
            while(true){
                Socket socket = serverSocket.accept();
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
