import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ServerEcho {
	
	private static JTextField Mensaje = new JTextField("",20);
	private static JTextField Estado = new JTextField("",20);
	private static JTextField IpString = new JTextField("",20);
	private static JList<String> Lista = new JList<String>();
	private static InputStreamReader inputStreamReader;
	private static BufferedReader bufferedReader;
	private static String message;
	 
	  public static void setearText(JTextField text, String texto){
		  text.setText(texto);
		  text.setHorizontalAlignment(JTextField.CENTER);

	  }
	  
	
	
	  public static void main(String args[]) {
		  
		   JFrame f = new JFrame("Chat");
		   f.getContentPane().setLayout(new GridLayout(1,1));
		  
		   JPanel p = new JPanel();
		   p.setLayout(new FlowLayout());

		  
		   IpString.setEditable(false);
		   IpString.setHorizontalAlignment(JTextField.CENTER);
		   
		   Estado.setEditable(false);
		   IpString.setHorizontalAlignment(JTextField.CENTER);

		   Mensaje.setEditable(false);
		   IpString.setHorizontalAlignment(JTextField.CENTER);
		   
	
			       
		   p.add(IpString);
		   p.add(Estado);
		   p.add(Mensaje);
		   f.add(p);
			
	    
		   f.setSize(325,150);
		   f.setVisible(true);
		  
		   try {
		          ServerSocket sk = new ServerSocket(7);
		          
		          setearText(IpString,"Ip: "+InetAddress.getLocalHost().getHostAddress()+" - Puerto: "+7);
		          setearText(Estado,"Escuchando..");
		          boolean terminado = false;
		          while (terminado == false) {
		                
		        	   	 Socket cliente = sk.accept();
			             setearText(Estado,"Cliente Conectado.");
		                 inputStreamReader = new InputStreamReader(cliente.getInputStream());
		 				 bufferedReader = new BufferedReader(inputStreamReader); // get the client message
		 				 message = bufferedReader.readLine();
		 				 
		 				 if (message.equals("fin")){terminado = true;}
		 				 
		 				 setearText(Mensaje,message);
		                 cliente.close();
		                 
		                 
		          }
		          sk.close();
		          System.exit(0);
		       
		   } catch (IOException e) {
		          System.out.println(e);
		   }
		  
		 
		   
		  }
	  
	

	 
}

