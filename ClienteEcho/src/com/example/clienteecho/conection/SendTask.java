package com.example.clienteecho.conection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;

import com.example.clienteecho.view.EnviarMensajesWindow;

public class SendTask extends AsyncTask<Void, Void, Void> {
	  
	 
	  EnviarMensajesWindow window;
	  String message,ip,ipAddress;
	  PrintWriter printwriter;
	  
		public String getMessage() {
			return message;
		}
	
		public void setMessage(String message) {
			this.message = message;
		}
	
		public SendTask(EnviarMensajesWindow ventana, String ip, String mes){
		 	   window= ventana;
		 	   this.ip=ip;
		 	   message=mes;

		 }

	  @Override
	  protected Void doInBackground(Void... arg0) {
	  
	   
	  try {
		   
		Socket socketWriter = new Socket(ip, 7);
		
		 if(message.isEmpty()){message="0";}
		
		printwriter = new PrintWriter(socketWriter.getOutputStream(), true);
		printwriter.write(message); 
		printwriter.flush();
		printwriter.close();
		window.agregarMensaje("Enviado: "+message);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	  @Override
	  protected void onPostExecute(Void result) {
	   window.actualizarLista();
	   window.clear();
	   super.onPostExecute(result);
	  }
	 }