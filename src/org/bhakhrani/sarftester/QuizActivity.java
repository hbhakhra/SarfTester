package org.bhakhrani.sarftester;

import java.io.IOException;
import java.io.InputStream;

import org.bhakhrani.sarf.view.FlashCard;
import org.bhakhrani.sarf.view.FlashCardQuiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QuizActivity extends Activity {

	private FlashCardQuiz quiz;
	private int quizNumber;
	private String quizLabel;
	private FlashCard currentFlashCard;
	
	private EditText question;
	private EditText answer;
	private Button nextQuestion;
	
	private static final String FILE_NAME = "Verb_List_Formatted_txt.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		// Show the Up button in the action bar.
		setupActionBar();
		
	    super.onCreate(savedInstanceState);

	    // Get the message from the intent
	    Intent intent = getIntent();
	    quizLabel = intent.getStringExtra(HomeActivity.QUIZ_LABEL);
	    TextView quizLabelView = (TextView) findViewById(R.id.selectedQuiz);
	    quizLabelView.setText(quizLabel);
	    quizNumber = intent.getIntExtra(HomeActivity.QUIZ_ID, 6);
	    
	    question = (EditText) findViewById(R.id.question);
	    answer = (EditText) findViewById(R.id.answer);
	    nextQuestion = (Button) findViewById(R.id.nextQuestion);
	    
	    AssetManager am = this.getAssets();
	    InputStream is;
		try {
			is = am.open(FILE_NAME);
			quiz = FlashCardQuiz.getFlashCardQuiz(is);
			getFlashCard();
			showInstructions();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showInstructions() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Add the buttons
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   showQuestion();
		           }
		       });
		builder.setCancelable(false);
		builder.setTitle(quizLabel);
		builder.setMessage(currentFlashCard.getInstructions());
		// Create the AlertDialog
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public void showQuestion() {
		question.setText(currentFlashCard.getQuestion());
	}
	
	public void showAnswer(View view) {
		answer.setText(currentFlashCard.getAnswer());
		nextQuestion.setEnabled(true);
	}
	
	public void nextQuestion(View view) {
		getFlashCard();
		showQuestion();
		answer.setText("");
		nextQuestion.setEnabled(false);
		if (quizNumber == 6) {
			showInstructions();
		}
	}
	
	public void getFlashCard() {
		currentFlashCard = quiz.selectQuiz(quizNumber);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
