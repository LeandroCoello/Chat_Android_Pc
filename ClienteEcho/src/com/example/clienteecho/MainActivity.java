package com.example.clienteecho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

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
