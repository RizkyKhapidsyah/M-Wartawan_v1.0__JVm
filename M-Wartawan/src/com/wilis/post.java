package com.wilis;


import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class post extends Activity {
   
   Spinner spinner1;
    /** Called when the activity is first created. */
   
   EditText judul,berita;
   Button post;
   
   @Override
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Login.CheckLogin()){
        setContentView(R.layout.tulisberita);
        
        judul=(EditText)findViewById(R.id.judul);
        berita=(EditText)findViewById(R.id.berita);
        spinner1=(Spinner)findViewById(R.id.kategori);
        post=(Button)findViewById(R.id.post);
        
        post.setOnClickListener(new View.OnClickListener() {
            
            @Override
            
            public void onClick(View v) {
               
               // TODO Auto-generated method stub
               ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
               postParameters.add(new BasicNameValuePair("judul", judul.getText().toString()));
               postParameters.add(new BasicNameValuePair("berita", berita.getText().toString()));
               postParameters.add(new BasicNameValuePair("spinner1", String.valueOf(spinner1.getSelectedItem())));
               postParameters.add(new BasicNameValuePair("nip", user.nip()));
               String response = null;  
               try {
                  
                  response = CustomHttpClient.executeHttpPost("http://10.0.2.2/wartawan/simpan.php", postParameters);
                  
                  String res = response.toString();
                  res = res.trim();
                  res = res.replaceAll("\\s+","");
                  if (res.equals("1")) {
                	  Toast.makeText(getApplicationContext(), "Post Succesfully",2).show();
                	  berhasil();
                  }
                  else Toast.makeText(getApplicationContext(), "Post Failed",2).show();
               }
               catch (Exception e) {
                  e.printStackTrace();
               }
            }
         });
   }
        else{
        	Intent i = new Intent(this, Login.class);
        	startActivity(i);
        }
   }
   
   public void addListenerOnSpinnerItemSelection() {
		spinner1 = (Spinner) findViewById(R.id.kategori);
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	        /**
	         * Called when a new item is selected (in the Spinner)
	         */
	         public void onItemSelected(AdapterView<?> parent, View view, 
	                    int pos, long id) {
	                // An spinnerItem was selected. You can retrieve the selected item using
	                // parent.getItemAtPosition(pos)

	                Toast.makeText(post.this, "Hello Toast",Toast.LENGTH_SHORT).show();

	            }

	            public void onNothingSelected(AdapterView<?> parent) {
	                // Do nothing, just another required interface callback
	            }

	    }); 
	  }
   
   public void berhasil ()
	  {
		   Intent i = new Intent(this, user.class);
		startActivity(i);
	 }@Override
	 public void onBackPressed() {
		    // do nothing.
		 Intent i = new Intent(this, user.class);
			startActivity(i);
			Login.setisLogin(false);
		} 
   }
