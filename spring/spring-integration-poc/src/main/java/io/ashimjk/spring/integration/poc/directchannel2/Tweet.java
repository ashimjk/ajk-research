package io.ashimjk.spring.integration.poc.directchannel2;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Tweet {

    private long tid;
    private String text;
    private Date time;
    private String hashTag;

    @Override
    public String toString() {
        return "Tweet{" +
                "tid=" + tid +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", hashTag='" + hashTag + '\'' +
                '}';
    }

}