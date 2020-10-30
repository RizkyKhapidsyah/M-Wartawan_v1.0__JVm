package com.wilis;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends Activity {
   
   static EditText un;
EditText pw;
   TextView error, lupa;
   ImageView login;
   String user, pass;  
   public static String[] nip=new String[1];
   public static boolean isLogin = false;

   public static boolean CheckLogin() {
    return isLogin;
   }

   public static void setisLogin(boolean b)
   {
   isLogin = b;
   }
   
   public static void setuser(String n){
	   nip[0] = n;
   }
   public static String user(){
	   return nip[0];
   }
   
    /** Called when the activity is first created. */
   
   @Override
   
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	   setContentView(R.layout.login);   
	   
	   un=(EditText)findViewById(R.id.user);
	   pw = (EditText)findViewById(R.id.pass);
	   error=(TextView)findViewById(R.id.error);
	   lupa=(TextView)findViewById(R.id.lupa);
	   login=(ImageView)findViewById(R.id.login);
      
       login.setOnClickListener(new View.OnClickListener() {
        
        @Override
        
        public void onClick(View v) {
           
           // TODO Auto-generated method stub
           ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
           postParameters.add(new BasicNameValuePair("username", un.getText().toString()));
           postParameters.add(new BasicNameValuePair("password", pw.getText().toString()));
           
           String response = null;
           
           try {
              
              response = CustomHttpClient.executeHttpPost("http://10.0.2.2/wartawan/check.php", postParameters);
              
              String res = response.toString();
              
              res = res.trim();
              
              res = res.replaceAll("\\s+","");

              if (res.equals("2")) 
              {
               berhasil(v);
               setisLogin(true);
               setuser(un.getText().toString());
              }
              else { 
           	    error.setText("Sorry!! Wrong Username or Password Entered");
           	    lupa.setText("Lupa Password? Hubungi Kantor.");
                   }
           }
           
           catch (Exception e) {
              
              un.setText(e.toString());
           }
        }
     });
  }
      
   public void berhasil (View theButton)
   {
	   Intent i = new Intent(this, user.class);
   	startActivity(i);
    }@Override
    
   public void onBackPressed() {
	   AlertDialog alertDialog = new AlertDialog.Builder(this).create();
       alertDialog.setTitle("Exit Alert");
      // alertDialog.setIcon(R.drawable.appicon);

       alertDialog.setMessage("Are You Sure to Quit ICT MEDIA?");
       alertDialog.setButton("Yes", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int which) {
       	// closeDialog.this.finish();
				Intent exit = new Intent(Intent.ACTION_MAIN);
				exit.addCategory(Intent.CATEGORY_HOME);
				exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(exit);
				finish();
				System.exit(0);
				Login.setisLogin(false);
           return;
       } });
       alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int which) {
             dialog.cancel();
           return;
       }});
         alertDialog.show();
	}

   }

