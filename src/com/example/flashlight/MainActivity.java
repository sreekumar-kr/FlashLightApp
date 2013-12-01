package com.example.flashlight;

import com.mashinga.hello.R;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements View.OnClickListener {

	ToggleButton toggleButton;
	Camera cam;
	Parameters params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);

		toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
		toggleButton.setTextColor(Color.GRAY);
		toggleButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();

		if (id == R.id.toggleButton) {
			if (toggleButton.isChecked()) {
				flashLightOn();
				toggleButton.setTextColor(Color.GREEN);
			} else {
				flashLightOff();
				toggleButton.setTextColor(Color.GRAY);

			}
		}
	}

	public void flashLightOn() {

		try {
			if (getPackageManager().hasSystemFeature(
					PackageManager.FEATURE_CAMERA_FLASH)) {
				cam = Camera.open();
				Parameters p = cam.getParameters();
				p.setFlashMode(Parameters.FLASH_MODE_TORCH);
				cam.setParameters(p);
				cam.startPreview();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getBaseContext(), "Exception flashLightOn()",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void flashLightOff() {
		try {
			if (getPackageManager().hasSystemFeature(
					PackageManager.FEATURE_CAMERA_FLASH)) {
				cam.stopPreview();
				cam.release();
				cam = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getBaseContext(), "Exception flashLightOff",
					Toast.LENGTH_SHORT).show();
		}
	}

}
