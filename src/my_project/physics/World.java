package my_project.physics;

import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;

public class World {
    private Vec2d gravity;
    private ArrayList<PhysicsObject> physicsObjects;
    private ArrayList<CollisionPoint> collisionPoints;
    public World() {}
    public void update(double dt) {

    }
    public void resolveCollisionPoints() {
        for (CollisionPoint collisionPoint : collisionPoints) {
            collisionPoint.resolveCollision();
        }
        collisionPoints.removeAll(collisionPoints);
    }
    public void createCollisionPoint(PhysicsObject po1, PhysicsObject po2) {
        collisionPoints.add(new CollisionPoint(po1, po2));
    }
    public void addPhysicsObject(PhysicsObject p) {
        physicsObjects.add(p);
    }

}
