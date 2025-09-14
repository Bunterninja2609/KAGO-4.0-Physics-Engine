package my_project.physics;

import com.sun.javafx.geom.Vec2d;
import com.sun.javafx.geom.Vec4d;

import java.util.Vector;

public class CollisionPoint {
    private Vec2d position;
    private double precisionFactor;
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
        this.precisionFactor = 0.001;
    }
    public void resolveCollision() {
        //TODO Impulserhaltungssatz
        removeOverlap();
        Vec2d i1 = object1.getImpulse();
        Vec2d i2 = object2.getImpulse();
        conserveImpulse(object1, object2);
    }
    private void removeOverlap() {

        while (object1.collidesWithPhysicsObject(object2)) {
            double oX1 = object1.getX() - object1.getVelocityX() * precisionFactor;
            double oY1 = object1.getY() - object1.getVelocityY() * precisionFactor;
            double oX2 = object2.getX() - object2.getVelocityX() * precisionFactor;
            double oY2 = object2.getY() - object2.getVelocityY() * precisionFactor;

            if (!(object1.isStatic || object2.isStatic)) {
                object1.setPosition(oX1, oY1);
                object2.setPosition(oX2, oY2);
            }else if(!object1.isStatic){
                object1.setPosition(oX1, oY1);
            }else if(!object2.isStatic){
                object2.setPosition(oX2, oY2);
            }else{
                object1.setPosition(oX1, oY1);
                object2.setPosition(oX2, oY2);
            }
        }
    }
    private void conserveMomentum(){

    }
    private Vec2d[] conserveMomentumXY(double vx1, double vy1, double vx2, double vy2, double m1, double m2) {
        return new Vec2d[] {};
    }
    private Vec2d rotateAroundCenter(double x, double y, double cX, double cY, double angle) {
        double nX = x - cX;
        double nY = y - cY;
        return new Vec2d(nX * Math.cos(angle)+cY, nY * Math.sin(angle)+cY);
    }
    private void conserveImpulse(PhysicsObject object1, PhysicsObject object2) {
        if (!(object1.isStatic || object2.isStatic)) {
            double m1 = object1.getMass();
            double m2 = object2.getMass();
            double vx1 = object1.getVelocityX();
            double vy1 = object1.getVelocityY();
            double vx2 = object2.getVelocityX();
            double vy2 = object2.getVelocityY();

            double nvx1 = (2*m2*vx2+(m1-m2)*vx1)/(m1+m2);
            double nvx2 = (2*m1*vx1+(m2-m1)*vx2)/(m1+m2);
            double nvy1 = (2*m2*vy2+(m1-m2)*vy1)/(m1+m2);
            double nvy2 = (2*m1*vy1+(m2-m1)*vy2)/(m1+m2);
            object1.setVelocity(nvx1, nvy1);
            object2.setVelocity(nvx2, nvy2);
        }else if(object1.isStatic){
            object2.setVelocity(-object2.getVelocityX(), -object2.getVelocityY());
        }else if(object2.isStatic){
            object1.setVelocity(-object1.getVelocityX(), -object1.getVelocityY());
        }
    }
}
