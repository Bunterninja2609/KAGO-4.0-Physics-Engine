package my_project.physics;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

public class PhysicsObject extends InteractiveGraphicalObject {
    protected double mass = 1;//kg
    protected Vec2d velocity;//in m/s
    protected Vec2d physicsPosition = new Vec2d(0, 0); //in m

    private double pixelPerMeterRatio = 1.0;
    protected String hitboxShape = "rectangle";//circle, rectangle



    public void physicsUpdate(double dt){
        physicsPosition.x = physicsPosition.x + dt*velocity.x;
        physicsPosition.y = physicsPosition.y + dt*velocity.y;
        calculatePosition();

        //add physics collision calculation
    }
    public void applyForce(double fx, double fy){
        velocity.x = velocity.x + (fx/mass);
        velocity.y = velocity.y + (fy/mass);
    }
    public void applyImpulse(double ix, double iy){
        //TODO Impulse
        velocity.x=(mass*velocity.x+ix)/mass;
        velocity.y=(mass*velocity.y+iy)/mass;
    }

    public void setVelocity(Vec2d velocity) {
        this.velocity = velocity;
    }

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
}
