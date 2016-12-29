package fr.p8.m2ise.androidfacecamrecog;

/**
 * Created by nizar on 28/12/16.
 */

public class MessageModel {
    String date;
    String message;
    String time;

    public MessageModel() {
    }


    public MessageModel(String date, String message, String time) {
        this.date = date;
        this.message = message;
        this.time = time;
    }


    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        String res = "toString: " + getMessage() + " , " + getDate() + " , " + getTime();
        return res;
    }
}
