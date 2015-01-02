package com.example.clienteecho;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clienteecho.data.SingletonHome;
import com.example.clienteecho.view.EnviarMensajesWindow;
public class MainActivity extends ActionBarActivity {

	 EditText editTextAddress;
	 String ip;


	 
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main);
	  
	  editTextAddress = (EditText)findViewById(R.id.address);
	  
	
	 }
	 
	 
	public void conectar(View view) {
		Intent i = new Intent(this, EnviarMensajesWindow.class );
		ip = editTextAddress.getText().toString();
		i.putExtra("ip", ip);
		editTextAddress.setText("");
		startActivity(i);
		
	}
	  
 
	 
	  public void clear(View view){
		  editTextAddress.setText(" ");
		  
	  }
	  


}
