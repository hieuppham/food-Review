package model;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private int score;
   //post-score == score
    private String name;
    private String contact;
    private String content;
    private Date date;
}
