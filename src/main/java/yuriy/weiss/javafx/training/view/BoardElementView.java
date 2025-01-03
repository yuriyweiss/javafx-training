package yuriy.weiss.javafx.training.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BoardElementView {

    private boolean focused = false;

    public abstract void updateView();
}
