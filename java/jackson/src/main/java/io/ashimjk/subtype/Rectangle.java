package io.ashimjk.subtype;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonTypeName("rectangle")
class Rectangle extends Shape {
    private int w;
    private int h;

    static Rectangle of(int w, int h) {
        Rectangle r = new Rectangle();
        r.setW(w);
        r.setH(h);
        return r;
    }
}
