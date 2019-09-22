package io.ashimjk.feign;

import lombok.Data;

@Data
class Post {

    private String userId;
    private Long id;
    private String title;
    private String body;

}
