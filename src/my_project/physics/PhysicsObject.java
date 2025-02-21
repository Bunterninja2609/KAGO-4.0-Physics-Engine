package my_project.physics;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

public class PhysicsObject extends InteractiveGraphicalObject {
    protected double mass;
    protected Vec2d velocity;
    protected String hitboxShape = "rectangle";//circle, rectangle


    public void Physicsupdate(double dt){
        this.x = this.x + dt*velocity.x;
        this.y = this.y + dt*velocity.y;


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
}
