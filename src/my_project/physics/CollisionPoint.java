package my_project.physics;

import com.sun.javafx.geom.Vec2d;

import java.util.Vector;

public class CollisionPoint {
    private Vec2d position;
    private double tangent;
    private PhysicsObject object1;
    private PhysicsObject object2;
    public CollisionPoint(Vec2d position, PhysicsObject object1, PhysicsObject object2) {
        this.position = position;
        this.object1 = object1;
        this.object2 = object2;
    }
    public CollisionPoint(PhysicsObject object1, PhysicsObject object2) {
        this.object1 = object1;
        this.object2 = object2;
    }
    public void resolveCollision() {
        //TODO Impulserhaltungssatz
        object1.setVelocity(new Vec2d(0, 0));
        object2.setVelocity(new Vec2d(0, 0));
    }
}
