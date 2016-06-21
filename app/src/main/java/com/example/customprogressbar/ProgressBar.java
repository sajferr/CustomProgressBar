package com.example.customprogressbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ProgressBar extends Activity {

	CharSequence[] values = { "Android", "Apple", "Java" };
	boolean[] itemChecked = new boolean[values.length];
	private ProgressDialog _progressDialog;
	private int _progress = 0;

	private Handler _progressHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_bar);
		Log.i("Uwaga","1");
		
		/**
		 * Set click on button
		 */
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(1);
				_progress = 0;
				Log.i("Uwaga","2");
				_progressDialog.setProgress(0);
				_progressHandler.sendEmptyMessage(0);
				
			}
		});

		/**
		 * start progress thread
		 */
		_progressHandler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if(_progress>=100) {
					Log.i("Uwaga","3");
					_progressDialog.dismiss();
				} else {
					_progress++;
					Log.i("Uwaga","4");
					_progressDialog.incrementProgressBy(1);
					_progressHandler.sendEmptyMessageDelayed(0, 100);
				}
			}
		};
		
	}

	/**
	 * create dialog for hide and cancel
	 */
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch(id) {
		case 0:
			Log.i("Uwaga","5");
			return new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher).setTitle("Open Dialog with text...")

					/**
					 * set positive button
					 */
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Log.i("Uwaga","6");
							Toast.makeText(getBaseContext(), "OK button clicked!", Toast.LENGTH_SHORT).show();
						}
					})
					
					/**
					 * set negative button
					 */
					.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Log.i("Uwaga","7");
							Toast.makeText(getBaseContext(), "Cancel button clicked!", Toast.LENGTH_SHORT).show();
						}
					})
					
					.setMultiChoiceItems(values, itemChecked, new DialogInterface.OnMultiChoiceClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which, boolean isChecked) {
							Log.i("Uwaga","8");
							// TODO Auto-generated method stub
							Toast.makeText(getBaseContext(), values[which] + (isChecked ? " checked!" : " unchecked!"), Toast.LENGTH_SHORT).show();
						}
					}).create();
		case 1:
			_progressDialog = new ProgressDialog(this);
			_progressDialog.setIcon(R.drawable.ic_launcher);
			Log.i("Uwaga","9");
			_progressDialog.setTitle("Uploading files...");
			_progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			_progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Hide", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Log.i("Uwaga","10");
					// TODO Auto-generated method stub
					Toast.makeText(getBaseContext(), "Hide clicked!", Toast.LENGTH_SHORT).show();
				}
			});
			
			_progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Log.i("Uwaga","11");
					// TODO Auto-generated method stub
					Toast.makeText(getBaseContext(), "Cancel clicked!", Toast.LENGTH_SHORT).show();
				}
			});
			return _progressDialog;
		}
		return null;
	}

	
}
