package model;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Data
public class Post {
    private int _id;
    private String title;
    private Date date;
    private String content;
    private List<String> images;
    private List<String> hashtags;
    private String name;
    private String contact;

}
