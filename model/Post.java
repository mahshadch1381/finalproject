package model;

import javafx.scene.chart.PieChart;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Post {
    public String title;
    public String description;
    public String publisher;
    public String date;
    public Long time_date;
    public String postPicture;
    public String profile;
    public int likes=0;
    public int reposts=0;
    public PrivacyStatus getPrivacyStatus() {
        return privacyStatus;
    }

    public void setPrivacyStatus(PrivacyStatus privacyStatus) {
        this.privacyStatus = privacyStatus;
    }

    public PrivacyStatus privacyStatus;
    @Override
    public boolean equals(Object o) {
        Post a=(Post) o;
        if (this.title == a.title) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title) && Objects.equals(description, post.description) && Objects.equals(publisher, post.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, publisher);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateString(Date d) {
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy/ HH:mm:ss");
        date=formatter.format(d);
    }
}
