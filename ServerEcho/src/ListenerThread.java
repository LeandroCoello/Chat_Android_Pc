import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;


public class ListenerThread extends Thread {

	JTextField Estado;
	JTextField IpString;
	JTextField Mensaje;
	JList<String> Lista; 
	DefaultListModel<String > mensajes;
	String message,ipCliente;
	
	
	
    public void setAts(JTextField Estado, JTextField IpString, JTextField Mensaje, JList<String> Lista, 
	DefaultListModel<String > mensajes, String ipCliente) {
		this.Estado= Estado;
		this.IpString= IpString;
		this.Mensaje= Mensaje;
		this.Lista= Lista;
		this.mensajes= mensajes;
		this.ipCliente= ipCliente;
	}

	public void run() {
    	 try {
	          ServerSocket sk = new ServerSocket(7);
	          
	          IpString.setText("Ip: "+InetAddress.getLocalHost().getHostAddress()+" - Puerto: "+7);
	          Estado.setText("Escuchando..");
	         
	          
	          boolean terminado = false;
	          while (terminado == false) {
	                
		        	 Socket cliente = sk.accept();
			         Estado.setText("Cliente Conectado.");
			         InputStreamReader inputStreamReader = new InputStreamReader(cliente.getInputStream());
			         BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		 			 message = bufferedReader.readLine();	 				
	 				 
		 			 if (message.equals("fin")){terminado = true;}
		 			 
		 			ipCliente= cliente.getInetAddress().toString();		 				 
	 				 Mensaje.setText(message);
	 				 mensajes.addElement("Recibido: "+ipCliente+" "+message);
	 				 
	 				Lista.ensureIndexIsVisible(Lista.getModel().getSize()-1);

	 				
			        
	 				cliente.close();
	                 
	          }
	          
	         
	          sk.close();
	          System.exit(0);
	       
	   } catch (IOException e) {
	          System.out.println(e);
	   }
    }

    

}