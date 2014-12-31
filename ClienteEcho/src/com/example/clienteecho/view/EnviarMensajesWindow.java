package com.example.clienteecho.view;


import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.clienteecho.R;
import com.example.clienteecho.conection.SendTask;





public class EnviarMensajesWindow extends Activity{

	EditText text1;
	ListView l;
	Button send;
	ArrayList<String> mensajes = new ArrayList<String>();
	EnviarMensajesWindow w = this;
	String ip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enviar_mensajes);
	
		 text1 = (EditText)findViewById(R.id.mensaje);
		 send = (Button)findViewById(R.id.send);
		 l= (ListView)findViewById(R.id.lista);
		 ip=(String) getIntent().getExtras().getString("ip");
		 
		 ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mensajes);
	        l.setAdapter(adapter);
		 
		 send.setOnClickListener(buttonConnectOnClickListener);
				
	}
	
	 OnClickListener buttonConnectOnClickListener = 
			   new OnClickListener(){

			    @Override
			    public void onClick(View arg0) {
			     SendTask sendTask = new SendTask(w,ip,text1.getText().toString());
			     
			      sendTask.execute();
			    }};

	public void agregarMensaje(String m){
		mensajes.add(m);
	}
	
	public void actualizarLista(){
		ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mensajes);
        l.setAdapter(adapter);
        l.setSelection(mensajes.size()-1);
	}
	
	public void clear(){
		text1.setText("");
	}
}
