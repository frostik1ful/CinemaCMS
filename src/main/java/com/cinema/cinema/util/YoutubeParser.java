package com.cinema.cinema.util;

public class YoutubeParser {
    public static String parseLink(String link){
        if (link.startsWith("https://www.youtube.com/")){
            if (!link.startsWith("https://www.youtube.com/embed/")){
                if (link.contains("&ab_channel")){
                    return "https://www.youtube.com/embed/" + link.substring(32, link.indexOf("&"));
                }
                return "https://www.youtube.com/embed/" + link.substring(32);
            }
            return link;
        }
        else if (link.startsWith("https://youtu.be/")){
            return link.replaceAll("https://youtu.be/","https://www.youtube.com/embed/");
        }
        return null;
    }
}
