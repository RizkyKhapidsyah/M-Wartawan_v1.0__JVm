package com.wilis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class awal extends Activity {
   @Override
   
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
	   setContentView(R.layout.awal);
   }
   
   public void next (View theButton){
	   Intent i = new Intent(this, Login.class);
	   startActivity(i);
   }  
}
