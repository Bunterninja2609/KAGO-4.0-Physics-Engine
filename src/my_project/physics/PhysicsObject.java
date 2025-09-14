package my_project.physics;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;
import java.util.Objects;

public class PhysicsObject extends InteractiveGraphicalObject {
    protected boolean isStatic;
    protected double mass = 1;//kg
    protected Vec2d velocity;//in m/s
    protected Vec2d physicsPosition = new Vec2d(0, 0); //in m
    protected ArrayList<Vec2d> forces = new ArrayList<Vec2d>();

    private double pixelPerMeterRatio = 1.0;
    protected String hitboxShape = "rectangle";//circle, rectangle



    public void physicsUpdate(double dt){
        physicsPosition.x = physicsPosition.x + dt*velocity.x;
        physicsPosition.y = physicsPosition.y + dt*velocity.y;
        calculatePosition();
        calculateForces(dt);
        //add physics collision calculation
    }
    private void calculateForce(double fx, double fy, double dt){
        velocity.x = velocity.x + (fx/mass)*dt;
        velocity.y = velocity.y + (fy/mass)*dt;
    }
    private void calculateForces(double dt){
        for (Vec2d force : forces){
            calculateForce(force.x,force.y,dt);
        }
        forces.clear();
    }
    public void applyForce(double vx, double vy){
        forces.add(new Vec2d(vx,vy));
    }
    public void applyImpulse(double ix, double iy){
        //TODO Impulse
        velocity.x=(mass*velocity.x+ix)/mass;
        velocity.y=(mass*velocity.y+iy)/mass;
    }

    public void setVelocity(Vec2d velocity) {
        this.velocity = velocity;
    }

    public void setVelocity(double vx, double vy) {this.velocity = new Vec2d(vx, vy);}

    public Vec2d getVelocity() {
        return velocity;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }
    public double getMass() {
        return mass;
    }
    public double getVelocityX(){
        return velocity.x;
    }
    public double getVelocityY(){
        return velocity.y;
    }
    public void setHitboxShape(String hitboxShape) {
        this.hitboxShape = hitboxShape;
    }
    public String getHitboxShape() {
        return hitboxShape;
    }
    public void drawHitbox(DrawTool drawTool){
        if (hitboxShape.equals("rectangle")){
            drawTool.drawRectangle(x,y,width,height);
        }else if (hitboxShape.equals("circle")){
            drawTool.drawCircle(x,y,radius);
        }
    }
    public void setMeter(double pixelPerMeterRatio){
        this.pixelPerMeterRatio = pixelPerMeterRatio;
        calculatePhysicsPosition();
    }
    public double getMeter(){
        return pixelPerMeterRatio;
    }
    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
        calculatePhysicsPosition();
    }
    public void calculatePhysicsPosition(){
        physicsPosition.x = x/pixelPerMeterRatio;
        physicsPosition.y = y/pixelPerMeterRatio;
    }
    public void calculatePosition(){
        x = physicsPosition.x*pixelPerMeterRatio;
        y = physicsPosition.y*pixelPerMeterRatio;
    }
    public boolean collidesWithPhysicsObject(PhysicsObject otherPhysicsObject){
        if(Objects.equals(hitboxShape, "circle")){
            if(Objects.equals(otherPhysicsObject.getHitboxShape(), "circle")){
                //circle and circle
                if ( x < otherPhysicsObject.getX()+otherPhysicsObject.getWidth() && x + width > otherPhysicsObject.getX() && y < otherPhysicsObject.getY() + otherPhysicsObject.getHeight() && y + height > otherPhysicsObject.getY() ) return true;
            }else{
                //circle and rectangle
                if ( x < otherPhysicsObject.getX()+2*otherPhysicsObject.getRadius() && x + width > otherPhysicsObject.getX() && y < otherPhysicsObject.getY() + 2*otherPhysicsObject.getRadius() && y + height > otherPhysicsObject.getY() ) return true;
            }
        }else{
            if(Objects.equals(otherPhysicsObject.getHitboxShape(), "circle")){
                //rectangle and circle
                if ( otherPhysicsObject.getX() < x+2*radius && otherPhysicsObject.getX() + otherPhysicsObject.getWidth() > x && otherPhysicsObject.getY() < y + 2*radius && otherPhysicsObject.getY() + otherPhysicsObject.getHeight() > y ) return true;
            }else{
                //rectangle and rectangle
                if(getDistanceTo(otherPhysicsObject)<=radius+otherPhysicsObject.getRadius()) return true;
            }
        }
        return false || collidesWith(otherPhysicsObject);
    }
    public Vec2d getImpulse(){
        return new Vec2d(velocity.x * mass,velocity.y * mass);
    }

}
