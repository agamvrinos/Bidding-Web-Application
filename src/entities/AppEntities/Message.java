package entities.AppEntities;

import java.util.Date;

public class Message {
    private Integer id;
    private Integer sender_id;
    private Integer receiver_id;
    private String title;
    private String message;
    private Integer is_read;
    private Date date;
    private Integer is_auto;

    public Message(Integer id, Integer sender_id, Integer receiver_id, String title, String message, Integer is_read, Date date, Integer is_auto) {
        this.id = id;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.title = title;
        this.message = message;
        this.is_read = is_read;
        this.date = date;
        this.is_auto = is_auto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSender_id() {
        return sender_id;
    }

    public void setSender_id(Integer sender_id) {
        this.sender_id = sender_id;
    }

    public Integer getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(Integer receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getIs_read() {
        return is_read;
    }

    public void setIs_read(Integer is_read) {
        this.is_read = is_read;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIs_auto() {
        return is_auto;
    }

    public void setIs_auto(Integer is_auto) {
        this.is_auto = is_auto;
    }
}
