package com.example.messagingstompwebsocket.Database;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String sender;
    private String recipient;
    private String Created;
    private String message;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getCreated() {
        Created=GetCurrentISO8601Timestamp();
        return Created;
    }

    public void setCreated(String Created) {
        this.Created = Created;
    }
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private static String GetCurrentISO8601Timestamp() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        // Java Dates don't have microsecond resolution :(
        // Pad out to microseconds to match other examples.
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'000'");
        df.setTimeZone(tz);
        return df.format(new Date());
    }
    @Override
    public String toString() {

        return "MessageModel{" +
                "message='" + message + '\'' +
                ", sender='" + sender + '\'' + ", recipient='" + recipient + '\'' +
                '}';
    }
}