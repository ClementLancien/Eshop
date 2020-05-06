
public class ColorTool {
	public void lighter(Shape shape) {
		shape.red = shape.red + (255 - shape.red) / 10;
		shape.green = shape.green + (255 - shape.green) / 10;
		shape.blue = shape.blue + (255 - shape.blue) / 10;
	}
}
