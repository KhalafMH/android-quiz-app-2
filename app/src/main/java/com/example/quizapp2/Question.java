package com.example.quizapp2;

/**
 * Created by Mahdi on 4/12/2017.
 */

public class Question {
    private String mText;
    private boolean mIsTrue;

    public Question(String text, boolean isTrue) {
        mText = text;
        mIsTrue = isTrue;
    }

    public String getText() {
        return mText;
    }

    public boolean isTrue() {
        return mIsTrue;
    }

    @Override
    public String toString() {
        return getText();
    }
}
