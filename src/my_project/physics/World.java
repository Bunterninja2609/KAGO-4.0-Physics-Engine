package my_project.physics;

import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;

public class World {
    private Vec2d gravity;
    private double pixelPerMeterRatio;
    private ArrayList<PhysicsObject> physicsObjects;
    private ArrayList<CollisionPoint> collisionPoints;
    public World(double pixelPerMeterRatio) {
        physicsObjects = new ArrayList<>();
        collisionPoints = new ArrayList<>();
        this.pixelPerMeterRatio = pixelPerMeterRatio;
    }
    public void update(double dt) {
        for (PhysicsObject physicsObject : physicsObjects) {
            physicsObject.physicsUpdate(dt);
        }
        handleCollisions();
        resolveCollisionPoints();
    }
    private void resolveCollisionPoints() {
        for (CollisionPoint collisionPoint : collisionPoints) {
            collisionPoint.resolveCollision();
        }
        collisionPoints.removeAll(collisionPoints);
    }
    public void createCollisionPoint(PhysicsObject po1, PhysicsObject po2) {
        collisionPoints.add(new CollisionPoint(po1, po2));
    }
    public void addPhysicsObject(PhysicsObject p) {
        //*
        physicsObjects.add(p);
        p.setMeter(pixelPerMeterRatio);
        //*/

    }
    private void handleCollisions(){
        ArrayList<PhysicsObject> physicsObjectsAlreadyCalculated = new ArrayList<PhysicsObject>();
        for (PhysicsObject physicsObject : physicsObjects) {
            for (PhysicsObject physicsObjectToCheck : physicsObjects) {
                if ( (! physicsObjectToCheck.equals(physicsObject)) && (! physicsObjectsAlreadyCalculated.contains(physicsObjectToCheck)) ) {
                    if (physicsObject.collidesWithPhysicsObject(physicsObjectToCheck)) {
                        createCollisionPoint(physicsObjectToCheck, physicsObject);
                        System.out.println("YAY");
                    }
                }
            }

            physicsObjectsAlreadyCalculated.add(physicsObject);
        }
    }

}
