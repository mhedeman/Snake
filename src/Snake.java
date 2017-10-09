
import java.awt.*;
import java.util.ArrayList;

public class Snake {

    ArrayList<Point> snakeOnderdelen;

    private int xRichting, yRichting;
    final int SCHAAL = 10, STARTGROOTTE = 15, STARTX = 200, STARTY = 200;

    private boolean isMoving, maakLanger;

    public Snake() {
        snakeOnderdelen = new ArrayList<>();
        xRichting = 0;
        yRichting = 0;
        isMoving = false;
        maakLanger = false;

        snakeOnderdelen.add(new Point(STARTX, STARTY));
        for (int i = 1; i < STARTGROOTTE; i++) {
            snakeOnderdelen.add(new Point(STARTX - i * SCHAAL, STARTY));
        }

        setxRichting(1);
        isMoving = true;
    }

    public void teken(Graphics g) {
        g.setColor(Color.WHITE);
        for (Point punt : snakeOnderdelen) {
            g.fillRect(punt.x, punt.y, SCHAAL, SCHAAL);
        }

        g.setColor(Color.GREEN);
        g.fillRect(getX(), getY(), SCHAAL, SCHAAL);
    }

    public void beweeg() {
        if (isMoving) {
            Point staart = snakeOnderdelen.get(snakeOnderdelen.size() - 1);
            Point nieuwHoofd = new Point(getX() + xRichting * SCHAAL, getY() + yRichting * SCHAAL);
            for (int i = snakeOnderdelen.size() - 1; i >= 1; i--) {
                snakeOnderdelen.set(i, snakeOnderdelen.get(i - 1));
            }
            snakeOnderdelen.set(0, nieuwHoofd);
            if (maakLanger) {
                snakeOnderdelen.add(staart);
                maakLanger = false;
            }
        }
    }

    public void reset() {
        isMoving = false;
        maakLanger = false;
        snakeOnderdelen.clear();
        snakeOnderdelen.add(new Point(STARTX, STARTY));
        for (int i = 1; i < STARTGROOTTE; i++) {
            snakeOnderdelen.add(new Point(STARTX - i * SCHAAL, STARTY));
        }

        setxRichting(1);
        setyRichting(0);
        isMoving = true;
    }

    public boolean snakeCollision() {
        for (int i = 1; i < snakeOnderdelen.size(); i++) {
            if (snakeOnderdelen.get(i).x == getX() && snakeOnderdelen.get(i).y == getY()) {
                return true;
            }
        }

        return false;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean b) {
        isMoving = b;
    }

    public void setMaakLanger(boolean b) {
        maakLanger = b;
    }

    public int getxRichting() {
        return xRichting;
    }

    public int getyRichting() {
        return yRichting;
    }

    public void setxRichting(int x) {
        xRichting = x;
    }

    public void setyRichting(int y) {
        yRichting = y;
    }

    public int getX() {
        return snakeOnderdelen.get(0).x;
    }

    public int getY() {
        return snakeOnderdelen.get(0).y;
    }
}