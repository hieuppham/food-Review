package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MyFormat {
    public static List<String> toListString(String str) {
        return Arrays.asList(str.split(" "));
    }

    // SQL date (datetime) use YYYY-MM-DD hh:mm:ss
    public static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
    }

    public static String toString(List<String> list) {
        StringBuilder strbul = new StringBuilder();
        for (String str : list) {
            strbul.append(str);
            //for adding comma between elements
            strbul.append(" ");
        }
        //just for removing last comma
        strbul.setLength(strbul.length() - 1);
        String str = strbul.toString();
        return str;
    }

    public static String urlsToIds(String[] urls) {
        List<String> list = new ArrayList<>();
        for (String url : urls) {
            list.add(url.substring(url.indexOf("/d/") + 3, url.indexOf("/view")));
        };
        return toString(list);
    }
}
