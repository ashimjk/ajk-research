package io.ashimjk.subtype;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
class View {

    private List<Shape> shapes;
}
