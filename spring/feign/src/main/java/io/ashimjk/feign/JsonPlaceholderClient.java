package io.ashimjk.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "jplaceholder", url = "https://jsonplaceholder.typicode.com/")
public interface JsonPlaceholderClient {

    @GetMapping("/posts")
    List<Post> getPosts();

    @GetMapping("/posts/{postId}")
    Post getPostById(@PathVariable Integer postId);

}
