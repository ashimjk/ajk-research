package io.ashimjk.subtype;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonTypeName("circle")
class Circle extends Shape {
    private int radius;

    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = AreaFilter.class)
    private Area area;

    static Circle of(int radius, Area area) {
        Circle c = new Circle();
        c.setRadius(radius);
        c.setArea(area);
        return c;
    }

    @Getter
    @Setter
    @ToString
    static class Area {
        int x;
    }

    static class AreaFilter {

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Area)) return false;

            Area area = (Area) obj;
            return area.x > 0;
        }
    }
}
