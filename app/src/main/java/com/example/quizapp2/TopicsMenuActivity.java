package com.example.quizapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TopicsMenuActivity extends AppCompatActivity {

    Button mPhysicsQuizButton;
    Button mMathQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics_menu);

        mPhysicsQuizButton = (Button) findViewById(R.id.physics_quiz_button);
        mPhysicsQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopicsMenuActivity.this, PhysicsQuizActivity.class);
                startActivity(intent);
            }
        });

        mMathQuizButton = (Button) findViewById(R.id.math_quiz_button);
        mMathQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopicsMenuActivity.this, MathQuizActivity.class);
                startActivity(intent);
            }
        });
    }
}
