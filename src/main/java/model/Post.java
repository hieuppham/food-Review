package model;

import lombok.Data;

import java.util.*;

@Data
public class Post {
    private int _id;
    private String title;
    private Date date;
    private List<String> header;
    private List<String> content;
    private List<String> images;
    private List<String> hashtags;
    private String name;
    private String contact;
    private Feature features;

    public Map getParas(){
        Map<String, String> map = new HashMap();
        for (int i = 0; i < header.size(); i++) {
            map.put(header.get(i), content.get(i));
        }
        return map;
    }
}
