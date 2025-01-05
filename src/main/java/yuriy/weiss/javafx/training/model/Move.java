package yuriy.weiss.javafx.training.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Move {

    private final Team team;

    // TODO add steps, there can be several steps in one move
}
