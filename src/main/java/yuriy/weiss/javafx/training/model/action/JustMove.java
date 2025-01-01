package yuriy.weiss.javafx.training.model.action;

import yuriy.weiss.javafx.training.model.Position;

public record JustMove(Position fromPosition, Position toPosition) implements Action {
}
