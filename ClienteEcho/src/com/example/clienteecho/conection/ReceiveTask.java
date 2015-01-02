package com.example.clienteecho.conection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.os.AsyncTask;

import com.example.clienteecho.data.SingletonHome;
import com.example.clienteecho.view.EnviarMensajesWindow;

public class ReceiveTask extends AsyncTask<Void, Void, Void> {
	  
	 
	  EnviarMensajesWindow window;
	  SingletonHome s= SingletonHome.getInstance();
	  String message,ip;
	  PrintWriter printwriter;
	  
		public String getMessage() {
			return message;
		}
	
		public void setMessage(String message) {
			this.message = message;
		}
	
		public ReceiveTask(EnviarMensajesWindow ventana,String ip){
		 	   window= ventana;
		 	   this.ip=ip;
		 }

	  @Override
	  protected Void doInBackground(Void... arg0) {
	  
	     /* 
		   
		   while (true) {
				try {
	 
					Socket socketListener = new Socket(ip, 9);
					inputStreamReader = new InputStreamReader(socketListener.getInputStream());
					bufferedReader = new BufferedReader(inputStreamReader); // get the client message
					message = bufferedReader.readLine();
	 
					System.out.println(message);
					inputStreamReader.close();
					clientSocket.close();
	 
				} catch (IOException ex) {
					System.out.println("Problem in message reading");
				}
			}*/return null;
	}

	  @Override
	  protected void onPostExecute(Void result) {
	   window.actualizarLista();
	   super.onPostExecute(result);
	  }
	 }