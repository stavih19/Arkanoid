import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.KeyboardSensor;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private String title;
    private List<T> menuReturnValues;
    private List<String> menuItemNames;
    private List<String> menuItemKeys;
    private List<Boolean> isSubMenu;
    private List<Menu<T>> subMenus;
    private T status;
    private boolean done;

    /**
     * Instantiates a new Menu.
     *
     * @param titleN          the title
     * @param runnerN         the runner
     * @param keyboardSensorN the keyboard sensor
     */
    public MenuAnimation(String titleN, AnimationRunner runnerN, KeyboardSensor keyboardSensorN) {
        this.title = titleN;
        this.runner = runnerN;
        this.keyboardSensor = keyboardSensorN;
        this.menuItemKeys = new ArrayList();
        this.menuItemNames = new ArrayList();
        this.menuReturnValues = new ArrayList();
        this.isSubMenu = new ArrayList();
        this.subMenus = new ArrayList();
        this.reset();
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.menuItemKeys.add(key);
        this.menuItemNames.add(message);
        this.menuReturnValues.add(returnVal);
        this.isSubMenu.add(false);
        this.subMenus.add(null);
    }

    @Override
    public T getStatus() {

        return this.status;
    }

    /**
     * Reset.
     */
    @Override
    public void reset() {
        this.status = null;
        this.done = false;
    }

    @Override
    public boolean shouldStop() {

        return this.status != null;
    }

    /**
     * Do one frame.
     *
     * @param d the ds
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(51, 50, this.title, 25);
        d.drawText(49, 50, this.title, 25);
        d.drawText(50, 51, this.title, 25);
        d.drawText(50, 49, this.title, 25);
        d.setColor(Color.red);
        d.drawText(50, 50, this.title, 25);

        int i;
        for (i = 0; i < this.menuItemNames.size(); ++i) {
            int optionX = 100;
            int optionY = 120 + i * 40;
            String optionText = "(" + this.menuItemKeys.get(i) + ") " + this.menuItemNames.get(i);
            d.setColor(Color.black);
            d.drawText(optionX, optionY, optionText, 24);
        }

        for (i = 0; i < this.menuReturnValues.size(); ++i) {
            if (keyboardSensor.isPressed(menuItemKeys.get(i))) {
                if (!isSubMenu.get(i)) {
                    status = menuReturnValues.get(i);
                    done = true;
                } else {
                    Menu<T> subMenu = subMenus.get(i);
                    runner.run(subMenu);
                    status = subMenu.getStatus();
                    done = true;
                }
                break;
            }
        }
    }

    /**
     * Add sub menu.
     *
     * @param key     the key
     * @param message the message
     * @param subMenu the sub menu
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        menuItemKeys.add(key);

        menuItemNames.add(message);

        menuReturnValues.add(null);

        isSubMenu.add(true);

        subMenus.add(subMenu);
    }
}