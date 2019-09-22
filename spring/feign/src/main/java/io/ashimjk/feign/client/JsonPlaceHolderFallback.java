package io.ashimjk.feign.client;

import io.ashimjk.feign.model.Post;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class JsonPlaceHolderFallback implements JsonPlaceholderClient {

    @Override
    public List<Post> getPosts() {
        return Collections.emptyList();
    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }
}
