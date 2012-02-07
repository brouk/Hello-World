package cz.brouk.helloWorld;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class HelloWorld extends Activity {
	
	private Handler handler = new Handler();
	private ProgressBar progressBar;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Log.v("INFO", "Start new activity ...");
        
        Button btn1 = (Button) findViewById(R.id.btn1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        
        btn1.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				//start progressBar
				// if done -> start activity 2
				Log.v("INFO", "Button pressed ...");
				progressBar.setVisibility(ProgressBar.VISIBLE);
				//startProgressBar();
				new MyProgressBar().start();
			}
		
        });
    }
    
    public void startProgressBar() {
    	//---do some work in background thread---
        new Thread(new Runnable() {
        	public void run() {
        		Log.v("INFO", "Start run method ...");
                try {
                	//---do some work here---
                	for (int i=0; i < 10; i++) {
                		Log.v("INFO", "Waiting ...");
						Thread.sleep(500);
					} 
                	//progressBar.setVisibility(ProgressBar.GONE);
                } catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
            	
                //---hides the progress bar---
                handler.post(new Runnable() {
                    public void run() {
                        //---0 - VISIBLE; 4 - INVISIBLE; 8 - GONE---
                        progressBar.setVisibility(ProgressBar.GONE);
                    }
                });
            }
        }).start();
    }
    


private class MyProgressBar extends Thread {
	public void run() {
		Log.v("INFO", "Start run method ...");
        try {
        	//---do some work here---
        	for (int i=0; i < 10; i++) {
        		Log.v("INFO", "Waiting ...");
				Thread.sleep(500);
			} 
        	//progressBar.setVisibility(ProgressBar.GONE);
        } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    	
        //---hides the progress bar---
        handler.post(new Runnable() {
            public void run() {
                //---0 - VISIBLE; 4 - INVISIBLE; 8 - GONE---
                progressBar.setVisibility(ProgressBar.GONE);
            }
        });
    }
}

    
    
}
    

    
