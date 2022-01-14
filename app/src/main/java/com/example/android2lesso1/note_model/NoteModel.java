package com.example.android2lesso1.note_model;

import java.io.Serializable;

public class NoteModel implements Serializable {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public NoteModel(String text, int number) {
        this.text = text;
        this.number = number;
    }

    private int number;
}
