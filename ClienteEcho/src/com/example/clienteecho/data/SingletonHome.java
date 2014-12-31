package com.example.clienteecho.data;

import java.net.Socket;




public class SingletonHome {
    
	Socket socketListener;
	Socket socketWriter;
		
	public Socket getSocketListener() {
		return socketListener;
	}

	public void setSocketListener(Socket socketListener) {
		this.socketListener = socketListener;
	}

	public Socket getSocketWriter() {
		return socketWriter;
	}

	public void setSocketWriter(Socket socketWriter) {
		this.socketWriter = socketWriter;
	}

	private static SingletonHome INSTANCE = new SingletonHome();
 
    private SingletonHome() {
    
    }
 
    public static SingletonHome getInstance() {
        return INSTANCE;
    }
    
   
}