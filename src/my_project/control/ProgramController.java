package my_project.control;

import KAGO_framework.control.DatabaseController;
import KAGO_framework.control.ViewController;

import javax.swing.*;
import java.awt.event.MouseEvent;

import my_project.model.TestObject;
import my_project.physics.*;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute
    TestObject testObject1;
    TestObject testObject2;
    TestObject testObject3;
    World world = new World(64);

    // Referenzen
    private ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.

    /**
     * Konstruktor
     * Dieser legt das Objekt der Klasse ProgramController an, das den Programmfluss steuert.
     * Damit der ProgramController auf das Fenster zugreifen kann, benötigt er eine Referenz auf das Objekt
     * der Klasse viewController. Diese wird als Parameter übergeben.
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen. Achtung: funktioniert nicht im Szenario-Modus
     */
    public void startProgram() {
        //Hier wird eine lokale Referenz für ein House-Objekt angelegt.
        testObject1 = new TestObject(100, 200, 100, 100, 1, 1, false);
        testObject2 = new TestObject(300, 200, 10, 10, 0, 0, false);
        testObject3 = new TestObject(000, 600, 1000, 300, 0, 0, true);
        testObject1.setMass(10000);
        viewController.draw(testObject1);
        viewController.draw(testObject2);
        viewController.draw(testObject3);
        viewController.register(testObject1);
        viewController.register(testObject2);
        viewController.register(testObject3);
        world.addPhysicsObject(testObject1);
        world.addPhysicsObject(testObject2);
        world.addPhysicsObject(testObject3);
        //Damit die draw-Methode des Objekts hinter firstHouse aufgerufen wird,
        //muss dem ViewController-Objekt mitgeteilt werden, dass es das House-Objekt zeichnen soll.

    }

    /**
     * Sorgt dafür, dass zunächst gewartet wird, damit der SoundController die
     * Initialisierung abschließen kann. Die Wartezeit ist fest und damit nicht ganz sauber
     * implementiert, aber dafür funktioniert das Programm auch bei falscher Java-Version
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){
        System.out.println("-------");
        testObject1.applyForce(0,0);
        System.out.println(testObject1.getWidth());
        world.update(dt);
        
    }


    /**
     * Verarbeitet einen Mausklick.
     * @param e das Objekt enthält alle Informationen zum Klick
     */
    public void mouseClicked(MouseEvent e){

    }
}
