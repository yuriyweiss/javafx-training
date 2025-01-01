package yuriy.weiss.javafx.training.util;

public class IdGenerator {

    private static int nextId = 1;

    public static synchronized int getNextId() {
        int result = nextId;
        nextId++;
        return result;
    }

    private IdGenerator() {
    }
}
