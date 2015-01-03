import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;


public class SendThread extends Thread{
	
	DefaultListModel<String > mensajes;
	JTextField mensajeAEnviar;
	String ipCliente;
	
	public SendThread(DefaultListModel<String> mensajes, JTextField mensajeAEnviar, String ipCliente){
		this.mensajeAEnviar = mensajeAEnviar;
		this.mensajes = mensajes;
		this.ipCliente = ipCliente;
	}
	
	public void run(){
		try {
			
			Socket socketWriter = new Socket(ipCliente,8000);
			
			OutputStream outstream = socketWriter.getOutputStream(); 
			PrintWriter out = new PrintWriter(outstream);
			out.print(mensajeAEnviar.getText());
			
			mensajes.addElement("Enviado: "+mensajeAEnviar.getText());
			mensajeAEnviar.setText("");
			
			socketWriter.close();			
			outstream.close();
			out.close();
			
		} catch (IOException e) {
			System.out.println("no mande nada");
			e.printStackTrace();
			
		}
	}
	

}
