package my_project.model;

import KAGO_framework.view.DrawTool;
import my_project.physics.PhysicsObject;

public class TestObject extends PhysicsObject {

    public TestObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitboxShape = "rectangle";
        //Ein leerer Konstruktor erscheint!
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawHitbox(drawTool);
    }
    @Override
    public void update(double dt){

    }
}
