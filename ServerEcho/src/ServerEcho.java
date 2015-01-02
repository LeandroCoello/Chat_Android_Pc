import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class ServerEcho {
	
	private static JTextField Mensaje = new JTextField("",20);
	private static JTextField Estado = new JTextField("",20);
	private static JTextField IpString = new JTextField("",20);
	private static JTextField mensajeAEnviar = new JTextField("",20);
	private static InputStreamReader inputStreamReader;
	private static BufferedReader bufferedReader;
	private static String message;
	private static DefaultListModel<String > mensajes = new DefaultListModel<String>();
	private static JList<String> Lista = new JList<String>(mensajes);
	private static JScrollPane panelDesplazamiento = new JScrollPane(Lista);
	private static JButton enviar = new JButton("Enviar");
	
	
	  public static void setearText(JTextField text, String texto){
		  text.setText(texto);
		  text.setHorizontalAlignment(JTextField.CENTER);

	  }
	  
	 
	
	  public static void main(String args[]) {
		  
		   JFrame f = new JFrame("Chat");
		   f.getContentPane().setLayout(new FlowLayout());
		  
		   JPanel p = new JPanel();
		   p.setLayout(new GridLayout(4,1));
		   
		   JPanel p2 = new JPanel();
		   p2.setLayout(new FlowLayout());

		   JPanel p3 = new JPanel();
		   p3.setLayout(new GridLayout(2,1));
		   
		   IpString.setEditable(false);
		   IpString.setHorizontalAlignment(JTextField.CENTER);
		   
		   Estado.setEditable(false);
		   IpString.setHorizontalAlignment(JTextField.CENTER);

		   Mensaje.setEditable(false);
		   IpString.setHorizontalAlignment(JTextField.CENTER);
		   
		   mensajeAEnviar.setHorizontalAlignment(JTextField.LEFT);
		  
		   enviar.addActionListener(enviarMensajeActionListener());
		   
		   panelDesplazamiento.setBounds(10,30,200,110); 
		  
		   p.add(new Label());
		   p.add(IpString);
		   p.add(Estado);
		   p.add(Mensaje);
		   p2.add(panelDesplazamiento); 
		   p3.add(mensajeAEnviar);
		   p3.add(enviar);
		   
		   
		   f.add(p);
		   f.add(p2);
		   f.add(p3);
			
	    
		   f.setSize(325,350);
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
		 				 agregarMensaje("Recibido: "+message);
		 				 
		 				 Lista.ensureIndexIsVisible(Lista.getModel().getSize()-1);

		 				 cliente.close();
		 				
		                 
		          }
		          sk.close();
		          System.exit(0);
		       
		   } catch (IOException e) {
		          System.out.println(e);
		   }
		  
		 
		   
		  }//fin main
	  
	  public static ActionListener enviarMensajeActionListener(){
		  
		  		  
		  return new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
//					 enviarMensaje(mensajeAEnviar.getText());
					agregarMensaje("Enviado: "+getMensajeAEnviar().getText());
					clearTextbox();
				}
			};
		  
	  }
	  
	  public static void agregarMensaje(String m){
		  
		  mensajes.addElement(m);
	  }
	
	  public static void clearTextbox(){
		  mensajeAEnviar.setText("");
	  }
	  
	  public static JTextField getMensajeAEnviar() {
			return mensajeAEnviar;
	  }

      
}

