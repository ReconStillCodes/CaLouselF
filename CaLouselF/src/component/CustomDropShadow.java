package component;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class CustomDropShadow extends DropShadow { // Create a custom drop shadow for container' effect
	public CustomDropShadow() {
		setRadius(10);
		setOffsetX(0);
		setOffsetX(0);
		setColor(Color.GRAY);
	}
}
