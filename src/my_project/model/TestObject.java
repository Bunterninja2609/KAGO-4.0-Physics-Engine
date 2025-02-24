package my_project.model;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.physics.PhysicsObject;

import java.util.Vector;

public class TestObject extends PhysicsObject {

    public TestObject(double x, double y, double width, double height, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitboxShape = "rectangle";
        this.velocity = new Vec2d(vx, vy);

    }

    @Override
    public void draw(DrawTool drawTool) {
        drawHitbox(drawTool);
    }
    @Override
    public void update(double dt){

    }
}
