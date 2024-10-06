package com.flappy.bird;
import java.util.List;

public class CollisionManager {
    public boolean checkCollision(Bird bird, List<Pipe> pipes) {
        for (Pipe pipe : pipes) {
            if (bird.getBounds().intersects(pipe.getBounds())) {
                return true;
            }
        }
        return false;
    }
}
