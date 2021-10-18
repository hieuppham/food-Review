package model;

import lombok.Data;

import java.util.Date;
import java.text.SimpleDateFormat;
@Data
public class Comment {
    private int score;
   //post-score == score
    private String name;
    private String contact;
    private String content;
    private Date date;
    
    public String formatDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(this.getDate());
    }
}
