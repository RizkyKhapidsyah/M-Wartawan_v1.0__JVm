package com.wilis;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class user extends Activity {   
   
	TextView namas, nips, jks, ttls, alamats, err;
	EditText username, password;
	ImageView update;
	String nama, nip, jk, ttl, alamat;
	public static String[] data = new String[5];
	
	public static String nama() {
	    return data[0];
	   }

	   public static void setnama(String b)
	   {
		   data[0] = b ;
	   }
		
	public static String nip() {
	    return data[1];
	   }

	   public static void setnip(String b)
	   {
	   data[1] = b ;
	   }
		
	public static String jk() {
	    return data[2];
	   }
    public static void setjk(String b)
	   {
		   data[2] = b ;
	   }
		
	public static String ttl() {
	    return data[3];
	   }
    public static void setttl(String b)
	   {
	   data[3] = b ;
	   }
	
public static String alamat() {
    return data[4];
   }
public static void setalamat(String b)
   {
   data[4] = b ;
   }
	
    /** Called when the activity is first created. */
   
   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Login.CheckLogin()){
        	
        setContentView(R.layout.profil);
        nama = "nama";
        nip = "NIP";
        jk = "Jenis Kelamin";
        ttl = "TTL";
        alamat = "Alamat";
        namas = (TextView) findViewById(R.id.name);
        setnama(setdata(nama));
        namas.setText(nama());
        nips = (TextView) findViewById(R.id.nip);
        setnip(setdata(nip));
		nips.setText(nip());
        jks = (TextView) findViewById(R.id.jk);
        setjk(setdata(jk));
		jks.setText(jk());
        ttls = (TextView) findViewById(R.id.ttl);
        setttl(setdata(ttl));
		ttls.setText(ttl());
        alamats = (TextView) findViewById(R.id.alamat);
        setalamat(setdata(alamat));
		alamats.setText(alamat());
        }
        else{
        	Intent i = new Intent(this, Login.class);
        	startActivity(i);
        }
   }
   
   public static String setdata(String cari){
   	   ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();     
       postParameters.add(new BasicNameValuePair("username", Login.user()));
       postParameters.add(new BasicNameValuePair("cr", cari));
       String response = null;
       String res=null;
       try {
       response = CustomHttpClient.executeHttpPost("http://10.0.2.2/wartawan/tampil.php", postParameters);
       res = response.toString();
       res = res.trim();
       } catch (Exception e){
    	   e.printStackTrace();
       }
       String[] data = new String[1];
       data[0]=res;
       return data[0];
    }
   
   public void edit(){
       setContentView(R.layout.editprofile);
       password = (EditText)findViewById(R.id.editpass);
       update=(ImageView)findViewById(R.id.update);
	   err = (TextView)findViewById(R.id.err);
       
       update.setOnClickListener(new View.OnClickListener() {
        
        @Override
        
        public void onClick(View v) {
           
           // TODO Auto-generated method stub
           
           ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
           
           postParameters.add(new BasicNameValuePair("editpass", password.getText().toString()));
           try {
			postParameters.add(new BasicNameValuePair("nip", nip()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
           String response = null;
           if(password.getText().toString() != "")
           {
           
           try {
              response = CustomHttpClient.executeHttpPost("http://10.0.2.2/wartawan/edit.php", postParameters);
              berhasil();
      		Login.setisLogin(false);
              Toast.makeText(getApplicationContext(), "Update Succesfully",2).show();
           }catch (Exception e) {
        	   e.printStackTrace();
           }
           }
           else {
        	   err.setText("Password Is Empty.");
           }
        }
       });
   }
   
public void berhasil ()
{
	   Intent i = new Intent(this, Login.class);
	startActivity(i);
 }@Override
	
	public void onBackPressed() {
		Intent i = new Intent(this, user.class);
			startActivity(i);
	}

		  public boolean onCreateOptionsMenu(Menu menu) {
			        MenuInflater inflater = getMenuInflater();
			        inflater.inflate(R.menu.login, menu);
			        return true;
			    }
			 
			    public boolean onOptionsItemSelected(MenuItem item) {
		        switch (item.getItemId()) {
			            case R.id.post:
			            	Intent i = new Intent(this, post.class);
			        	startActivity(i);
			                return true;
			            case R.id.edit:
			                edit();
			                return true;
			            case R.id.exit:
			         	   Intent a = new Intent(this, Login.class);
			         		startActivity(a);
			         		Login.setisLogin(false);
			                return true;
			            default:
			                return super.onOptionsItemSelected(item);
			        }
			    }
}

