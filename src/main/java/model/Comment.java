package model;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Comment {
    private int score;
    private int post_score;
    private String name;
    private String contact;
    private String content;
    private Date date;
}
