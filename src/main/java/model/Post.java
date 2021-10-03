package model;

import lombok.Data;

import java.util.*;

@Data
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
}
