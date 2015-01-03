package com.example.clienteecho.conection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.clienteecho.view.EnviarMensajesWindow;

public class ReceiveThread extends Thread {
	  
	 
	  EnviarMensajesWindow window;
	  String message,ip;
	  PrintWriter printwriter;
	  ServerSocket socketListener = null;
	  
	  
		public String getMessage() {
			return message;
		}
	
		public void setMessage(String message) {
			this.message = message;
		}
	
		public ReceiveThread(EnviarMensajesWindow ventana,String ip){
		 	   window= ventana;
		 	   this.ip=ip;
		 }

	  @Override
	  public void run() {
		  	
		  	
		  
			try {
				socketListener = new ServerSocket(8000);
				window.agregarMensaje("Recibido: "+socketListener.getInetAddress().toString());
				window.actualizarLista();
			}catch (IOException ex) {
				System.out.println("Problem in message reading before while");
			}	
		   
			while (true) {
				
	 
					Socket cliente=null;
					try {
						cliente = socketListener.accept();
					
				
					InputStreamReader inputStreamReader = new InputStreamReader(cliente.getInputStream());
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader); // get the client message
					message = bufferedReader.readLine();
	 
					window.agregarMensaje("Recibido: "+message);
					window.actualizarLista();
				
					cliente.close();
					inputStreamReader.close();
					bufferedReader.close();
					} catch (IOException e) {
						
						e.printStackTrace();
						System.out.println("Problem in message reading");
					}
				
			
			}
	  }
	  
	  public void cerrar(){
		  try {
			socketListener.close();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	  }
}
	  
		