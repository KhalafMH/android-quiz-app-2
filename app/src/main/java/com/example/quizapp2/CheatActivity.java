package com.example.quizapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private TextView mAnswerTextView;
    private Button mCheatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerTextView = (TextView) findViewById(R.id.answer_textview);
        mAnswerTextView.setVisibility(View.GONE);

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnswerTextView.setVisibility(View.VISIBLE);
                boolean answer = getIntent().getBooleanExtra(
                        Constants.EXTRA_CHEAT_ANSWER, false);
                mAnswerTextView.setText(Boolean.toString(answer));
                Intent data = new Intent();
                data.putExtra(Constants.EXTRA_USER_CHEATED, true);
                setResult(RESULT_OK, data);

            }
        });

    }
}
