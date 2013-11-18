package org.bhakhrani.sarftester;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class HomeActivity extends Activity implements OnItemSelectedListener {
    public final static String QUIZ_ID = "org.bhakhrani.sarftester.QUIZ_ID";
    public final static String QUIZ_LABEL = "org.bhakhrani.sarftester.QUIZ_LABEL";
    public final static String INCLUDE_MUJARRAD = "org.bhakhrani.sarftester.INCLUDE_MUJARRAD";
    public final static String INCLUDE_MAZEED = "org.bhakhrani.sarftester.INCLUDE_MAZEED";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}
	
	public void startQuiz(View view) {
		Spinner quizTypes = (Spinner) findViewById(R.id.quizTypes);
		int quizId = quizTypes.getSelectedItemPosition();
		
		CheckBox mujarradOption = (CheckBox) findViewById(R.id.isMujarrad);
		CheckBox mazeedOption = (CheckBox) findViewById(R.id.isMazeed);

		String quizLabel = quizTypes.getSelectedItem().toString();
		boolean includeMujarrad = mujarradOption.isChecked();
		boolean includeMazeed = mazeedOption.isChecked();
		
		Intent intent = new Intent(this, QuizActivity.class);

		intent.putExtra(QUIZ_ID, quizId);
		intent.putExtra(QUIZ_LABEL, quizLabel);
		intent.putExtra(INCLUDE_MUJARRAD, includeMujarrad);
		intent.putExtra(INCLUDE_MAZEED, includeMazeed);
		
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		Button startButton = (Button) findViewById(R.id.startButton);
		startButton.setEnabled(true);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		Button startButton = (Button) findViewById(R.id.startButton);
		startButton.setEnabled(false);		
	}
	
	

}
