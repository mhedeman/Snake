
import java.awt.*;

public class Appel {

    private Snake snake;
    private int x, y;
    private final int SCHAAL = 12;

    public Appel(Snake snake) {
        this.snake = snake;
        x = (int) (Math.random() * 350);
        y = (int) (Math.random() * 350);
    }

    public void veranderPositie(Rots rots) {
        x = (int) (Math.random() * 350);
        y = (int) (Math.random() * 350);

        for (Point point : rots.rotsen) {
            if (x >= point.x - 1 && x <= (point.x + SCHAAL + 1)) {
                if (y >= point.y - 1 && y <= (point.y + SCHAAL + 1)) {
                    veranderPositie(rots);
                }
            }
        }
    }

    public void teken(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, SCHAAL, SCHAAL);
    }

    public boolean snakeCollision() {
        int snakeX = snake.getX() + snake.SCHAAL / 2;
        int snakeY = snake.getY() + snake.SCHAAL / 2;

        if (snakeX >= x - 1 && snakeX <= (x + SCHAAL + 7))
            if (snakeY >= y - 1 && snakeY <= (y + SCHAAL + 7)) {
                return true;
            }

        return false;
    }
}
