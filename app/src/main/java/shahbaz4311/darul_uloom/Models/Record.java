package shahbaz4311.darul_uloom.Models;

import androidx.annotation.NonNull;

//id, string date, int sabaq, int start, int end, boolean sabqi, int manzil, int student_id
public class Record {
    private int id;
    private String date;
    private int sabaq;
    private int start;
    private int end;
    private boolean sabqi;
    private int manzil;
    private int student_id;

    public Record(int id, String date, int sabaq, int start, int end, boolean sabqi, int manzil, int student_id) {
        this.id = id;
        this.date = date;
        this.sabaq = sabaq;
        this.start = start;
        this.end = end;
        this.sabqi = sabqi;
        this.manzil = manzil;
        this.student_id = student_id;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSabaq() {
        return sabaq;
    }

    public void setSabaq(int sabaq) {
        this.sabaq = sabaq;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public boolean isSabqi() {
        return sabqi;
    }

    public int getSabqi() {
        return sabqi ? 1 : 0;
    }

    public void setSabqi(boolean sabqi) {
        this.sabqi = sabqi;
    }

    public int getManzil() {
        return manzil;
    }

    public void setManzil(int manzil) {
        this.manzil = manzil;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    @NonNull
    @Override
    public  String toString(){
        return "id: "+id+" date: "+date+" sabaq: "+sabaq+" start: "+start+" end: "+end+" sabqi: "+sabqi+" manzil: "+manzil+" student_id: "+student_id;
    }
}
