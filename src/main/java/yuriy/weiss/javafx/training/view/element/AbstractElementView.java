package yuriy.weiss.javafx.training.view.element;

import lombok.Getter;
import lombok.Setter;
import yuriy.weiss.javafx.training.view.element.ElementView;

@Getter
@Setter
public abstract class AbstractElementView implements ElementView {

    private boolean focused = false;
}
