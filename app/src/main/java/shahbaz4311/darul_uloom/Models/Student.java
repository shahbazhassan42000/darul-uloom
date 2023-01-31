package shahbaz4311.darul_uloom.Models;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

public class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public String toString() {
        //display id in 10width and name in 20width
        return String.format("%-10d | \t\t %s", id, name);
    }

}
