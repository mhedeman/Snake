import java.awt.*;
import java.util.ArrayList;

public class Rots {

    ArrayList<Point> rotsen;
    private Snake snake;
    private int x, y, aantal = 10;
    private final int SCHAAL = 15;

    public Rots(Snake snake) {
        this.snake = snake;
        rotsen = new ArrayList<Point>();

        x = (int) (Math.random() * 389);
        y = (int) (Math.random() * 389);

        for (int i = 1; i <= aantal; i++) {
            rotsen.add(new Point(x, y));
            veranderPositie();
        }
    }

    public void veranderPositie() {
        x = (int) (Math.random() * 389);
        y = (int) (Math.random() * 389);
    }

    public void teken(Graphics g) {
        g.setColor(new Color(157, 103, 0));
        for (Point point : rotsen) {

            g.fillRect(point.x, point.y, SCHAAL, SCHAAL);

        }
    }

    public boolean snakeCollision() {
        int snakeX = snake.getX() + snake.SCHAAL / 2;
        int snakeY = snake.getY() + snake.SCHAAL / 2;

        for (Point point : rotsen) {
            if (snakeX >= point.x - 1 && snakeX <= (point.x + SCHAAL + 1)) {
                if (snakeY >= point.y - 1 && snakeY <= (point.y + SCHAAL + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void reset() {
        rotsen.clear();

        for (int i = 1; i <= aantal; i++) {
            rotsen.add(new Point(x, y));
            veranderPositie();
        }
    }

    public void addRots() {
        aantal++;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
}

