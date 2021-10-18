package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private int score;
    private String key;
    private String title;
    private Date date;
    private List<String> texts;
    private List<String> images;
    private List<String> tags;
//    desciption = title;
    private double[] coordinates;

    public Date stringToDate(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(dateStr);
    }
    public String dateToString() {
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       return format.format(this.getDate());
    }

    public String formatDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(this.getDate());
    }
}
