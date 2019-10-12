package io.ashimjk.jsondiff.domain;

import io.ashimjk.jsondiff.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
public class ChangeLogString {
    private String op;
    private String path;
    private String value;

    @SneakyThrows
    public void setValue(Object object) {
        this.value = JsonUtil.MAPPER.writeValueAsString(object);
    }

    @SneakyThrows
    public Object getValue() {
        return JsonUtil.MAPPER.readValue(this.value, Object.class);
    }
}
