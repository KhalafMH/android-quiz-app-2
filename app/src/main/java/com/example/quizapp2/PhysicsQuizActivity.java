package com.example.quizapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PhysicsQuizActivity extends AppCompatActivity {

    private static final int REQUEST_CHEAT = 0;

    @NonNull
    private List<Question> mQuestions = new ArrayList<>();
    private int mCurrentQuestion = 0;
    private boolean mUserCheated = false;

    private TextView mQuestionView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;

    private Button mCheatButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics_quiz);

        String[] questions = getResources().getStringArray(R.array.physics_questions);
        int[] answers = getResources().getIntArray(R.array.physics_answers);

        if (questions.length != answers.length) {
            throw new AssertionError("Questions and answers are not the same length");
        }
        for (int i = 0; i < questions.length; ++i) {
            Question question = new Question(questions[i], answers[i] == 1);
            mQuestions.add(question);
        }

        final View.OnClickListener nextListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentQuestion = (mCurrentQuestion + 1) % mQuestions.size();
                setQuestion(mCurrentQuestion);
            }
        };
        mQuestionView = (TextView) findViewById(R.id.question_textview);
        mQuestionView.setOnClickListener(nextListener);
        setQuestion(mCurrentQuestion);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mQuestions.get(mCurrentQuestion).isTrue()) {
                    final Toast toast = Toast.makeText(
                            PhysicsQuizActivity.this,
                            mUserCheated ? R.string.cheater : R.string.correct,
                            Toast.LENGTH_SHORT
                    );
                    toast.show();
                } else {
                    Toast.makeText(
                            PhysicsQuizActivity.this,
                            R.string.wrong_answer,
                            Toast.LENGTH_SHORT
                    ).show();
                }
                nextListener.onClick(mTrueButton);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mQuestions.get(mCurrentQuestion).isTrue()) {
                    final Toast toast = Toast.makeText(
                            PhysicsQuizActivity.this,
                            mUserCheated ? R.string.cheater : R.string.correct,
                            Toast.LENGTH_SHORT
                    );
                    toast.show();
                } else {
                    Toast.makeText(
                            PhysicsQuizActivity.this,
                            R.string.wrong_answer,
                            Toast.LENGTH_SHORT
                    ).show();
                }
                nextListener.onClick(mFalseButton);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(nextListener);

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhysicsQuizActivity.this, CheatActivity.class);
                intent.putExtra(Constants.EXTRA_CHEAT_ANSWER, mQuestions.get(mCurrentQuestion).isTrue());
                startActivityForResult(intent, REQUEST_CHEAT);
            }
        });
    }

    private void setQuestion(int currentQuestion) {
        mQuestionView.setText(mQuestions.get(currentQuestion).toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CHEAT) {
            mUserCheated = data.getBooleanExtra(
                    Constants.EXTRA_USER_CHEATED, false);
        }
    }
}
