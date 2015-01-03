import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
	private static String message,ipCliente;
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
		   Estado.setHorizontalAlignment(JTextField.CENTER);

		   Mensaje.setEditable(false);
		   Mensaje.setHorizontalAlignment(JTextField.CENTER);
		   
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
		  
		   ListenerThread hilo = new ListenerThread();
		   hilo.setAts(Estado, IpString, Mensaje, Lista, mensajes,ipCliente);  
		   hilo.run();
		   
		  
		   
		  }//fin main
	  
	  public static JTextField getMensaje() {
		return Mensaje;
	}

		
	  public static JTextField getEstado() {
		return Estado;
	}


	public static JTextField getIpString() {
		return IpString;
	}



	public static JList<String> getLista() {
		return Lista;
	}



	public static ActionListener enviarMensajeActionListener(){
		  
		  		  
		  return new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					enviarMensaje();
				
				}
			};
		  
	  }
	  
	  public static void enviarMensaje(){
		  /*try {
			Socket socketWriter = new Socket(ipCliente,8000);
			
			OutputStream outstream = socketWriter.getOutputStream(); 
			PrintWriter out = new PrintWriter(outstream);
			out.print(mensajeAEnviar.getText());
			
			agregarMensaje("Enviado: "+getMensajeAEnviar().getText());
			clearTextbox();
			
			socketWriter.close();			
			outstream.close();
			out.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}*/
		SendThread hilo = new SendThread(mensajes, mensajeAEnviar, ipCliente);
		hilo.run();
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

