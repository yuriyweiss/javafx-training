package yuriy.weiss.javafx.training.view.element;

import javafx.scene.layout.Pane;

/**
 * Marker interface for Cell and Ship views, which are highest views for GridPane cells.
 */
public interface GridCellView {

    Pane getCellPane();
}
