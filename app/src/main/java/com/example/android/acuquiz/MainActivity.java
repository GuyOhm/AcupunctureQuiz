package com.example.android.acuquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Keep track of the score
    int quizScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the submit button is clicked
     */
    public void submitAnswers(View view) {
        // Initialize score
        quizScore = 0;

        // Check answer to question 1
        checkEditTextAnswer(R.id.layout_q_1_answer_1, R.string.question_1_answer_1);

        // Check answer to question 2
        checkBoxAnswer(R.id.q_2_right_answer_1, R.id.q_2_right_answer_2, R.id.q_2_wrong_answer_1, R.id.q_2_wrong_answer_2);

        // Check answer to question 3
        checkRadioButtonAnswer(R.id.q_3_right_answer_1);

        // Check answer to question 4
        checkRadioButtonAnswer(R.id.q_4_right_answer_1);

        // Check answer to question 5
        checkEditTextAnswer(R.id.layout_q_5_answer_1, R.string.question_5_answer_1);

        // Check answer to question 6
        checkBoxAnswer(R.id.q_6_right_answer_1, R.id.q_6_right_answer_2, R.id.q_6_wrong_answer_1);

        // Check answer to question 7
        checkRadioButtonAnswer(R.id.q_7_right_answer_1);

        // Check answer to question 8
        checkBoxAnswer(R.id.q_8_right_answer_1, R.id.q_8_right_answer_2, R.id.q_8_wrong_answer_1, R.id.q_8_wrong_answer_2);

        // Create score summary and toast it
        String toastMessage = createScoreSummary(getUserName(), quizScore);
        int duration = Toast.LENGTH_LONG;
        Toast.makeText(this, toastMessage, duration).show();
    }

    /**
     * This method checks the answers in edit text format
     *
     * @param id_given_answer resource id of the given answer
     * @param id_right_answer resource id of the right answer
     */
    private void checkEditTextAnswer(int id_given_answer, int id_right_answer) {
        // Get the answer given and store it
        EditText answerEditText = (EditText) findViewById(id_given_answer);
        String answer = answerEditText.getText().toString();

        // Get the right answer from string resources and store it
        String rightAnswer = getString(id_right_answer);

        // Check if answer is right
        if (answer.equals(rightAnswer)) {
            incrementQuizScore();
        }
    }

    /**
     * This method checks the answers in checkbox format
     *
     * @param id_right_answer_1 resource id of the 1st right answer
     * @param id_right_answer_2 resource id of the 2nd right answer
     * @param id_wrong_answer_1 resource id of the 1st wrong answer
     * @param id_wrong_answer_2 resource id of the 2nd wrong answer
     */
    private void checkBoxAnswer(int id_right_answer_1, int id_right_answer_2, int id_wrong_answer_1, int id_wrong_answer_2) {
        // Check if the first right box is checked
        CheckBox firstRightCheckbox = (CheckBox) findViewById(id_right_answer_1);
        boolean isRightAnswerChecked1 = firstRightCheckbox.isChecked();

        // Check if the second right box is checked
        CheckBox secondRightCheckbox = (CheckBox) findViewById(id_right_answer_2);
        boolean isRightAnswerChecked2 = secondRightCheckbox.isChecked();

        // Check if the first wrong box is checked
        CheckBox firstWrongCheckbox = (CheckBox) findViewById(id_wrong_answer_1);
        boolean isWrongAnswerChecked1 = firstWrongCheckbox.isChecked();

        // Check if the second wrong box is checked
        CheckBox secondWrongCheckbox = (CheckBox) findViewById(id_wrong_answer_2);
        boolean isWrongAnswerChecked2 = secondWrongCheckbox.isChecked();

        if ((isRightAnswerChecked1 && isRightAnswerChecked2) && !(isWrongAnswerChecked1 || isWrongAnswerChecked2)) {
            incrementQuizScore();
        }
    }

    // Overload checkBoxAnswer method to create a signature with 3 parameters only
    private void checkBoxAnswer(int id_right_answer_1, int id_right_answer_2, int id_wrong_answer_1) {
        // Check if the first right box is checked
        CheckBox firstRightCheckbox = (CheckBox) findViewById(id_right_answer_1);
        boolean isRightAnswerChecked1 = firstRightCheckbox.isChecked();

        // Check if the second right box is checked
        CheckBox secondRightCheckbox = (CheckBox) findViewById(id_right_answer_2);
        boolean isRightAnswerChecked2 = secondRightCheckbox.isChecked();

        // Check if the first wrong box is checked
        CheckBox firstWrongCheckbox = (CheckBox) findViewById(id_wrong_answer_1);
        boolean isWrongAnswerChecked1 = firstWrongCheckbox.isChecked();

        if ((isRightAnswerChecked1 && isRightAnswerChecked2) && !(isWrongAnswerChecked1)) {
            incrementQuizScore();
        }
    }

    /**
     * This method checks the answers in radio button format
     *
     * @param id_right_answer resource id of the right answer
     */
    private void checkRadioButtonAnswer(int id_right_answer) {
        // Check if the right answer is checked
        RadioButton rightRadioButton = (RadioButton) findViewById(id_right_answer);
        boolean isRightAnswerChecked = rightRadioButton.isChecked();

        if (isRightAnswerChecked) {
            incrementQuizScore();
        }
    }

    /**
     * This method increments quiz score
     */
    private void incrementQuizScore() {
        quizScore += 1;
    }

    /**
     * This method gets the user name
     */
    private String getUserName() {
        EditText nameEditText = (EditText) findViewById(R.id.layout_name);
        String name = nameEditText.getText().toString();
        return name;
    }

    /**
     * This method creates the score summary
     */
    private String createScoreSummary(String name, int score) {
        String scoreSummary = getString(R.string.score_summary_hello, name);
        scoreSummary += "\n" + getString(R.string.score_summary_score, score);

        if (score <= 3) {
            scoreSummary += "\n" + getString(R.string.score_summary_newbie);
        } else if (score <= 5) {
            scoreSummary += "\n" + getString(R.string.score_summary_keep_it_on);
        } else if (score <= 7) {
            scoreSummary += "\n" + getString(R.string.score_summary_right_path);
        } else if (score == 8) {
            scoreSummary += "\n" + getString(R.string.score_summary_master);
        }
        return scoreSummary;
    }
}
