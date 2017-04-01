package com.example.quizapp2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class PhysicsQuizActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics_quiz);

        String[] questions = getResources().getStringArray(R.array.physics_questions);
        int[] answers = getResources().getIntArray(R.array.physics_answers);
    }
}
