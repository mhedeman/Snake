
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePaneel extends JPanel implements ActionListener {

    private boolean startScherm, gameOver, gepauzeerd;
    private Snake snake;
    private Appel appel;
    private Rots rots;
    private Timer timer;
    private int score, appelTeller, level, vertraging;

    public GamePaneel() {
        addKeyListener(new InputHandler());

        startScherm = true;
        gameOver = false;
        gepauzeerd = false;


        snake = new Snake();
        appel = new Appel(snake);
        rots = new Rots(snake);

        score = 0;
        appelTeller = 0;
        level = 1;
        vertraging = 60;

        timer = new Timer(vertraging, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        String info = "Score: " + score + " | Appels Gesmikkeld: " +
                (snake.snakeOnderdelen.size() - snake.STARTGROOTTE + " | Level: " + level);

        if (startScherm) {
            snake.setMoving(false);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 400, 400);

            g.setColor(Color.YELLOW);
            g.setFont(new Font("Sanserif", Font.BOLD, 24));
            g.drawString("Ultimate Snake 2K16", (int) (g.getFontMetrics().stringWidth("Ultimate Snake 2K16") / 3.5), 100);
            g.setFont(new Font("Sanserif", Font.PLAIN, 18));
            g.drawString("*Druk op ENTER*", (int) (g.getFontMetrics().stringWidth("*Druk op ENTER*") / 1.2), 140);
        }

        if (!gameOver && !gepauzeerd && !startScherm) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 400, 400);
            snake.teken(g);
            appel.teken(g);
            rots.teken(g);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Sanserif", Font.BOLD, 14));
            g.drawString(info, 10, 15);

        }

        if (gameOver) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 400, 400);
            g.setColor(Color.RED);

            g.setFont(new Font("Sanserif", Font.BOLD, 24));
            g.drawString("Game Over", g.getFontMetrics().stringWidth("Game Over"), 400 / 2);

            g.setFont(new Font("Sanserif", Font.PLAIN, 14));
            g.setColor(Color.WHITE);
            g.drawString(info, (int) (400 / 2.5 - info.length() * 2.5f), 270);
            g.setFont(new Font("Sanserif", Font.PLAIN, 18));
            g.drawString("Druk op SPACE om opniew te beginnen", 400 / 6 - 32, 240);
        }

        if (gepauzeerd) {
            snake.setMoving(false);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 400, 400);
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Sanserif", Font.BOLD, 22));
            g.drawString("Gepauzeerd", g.getFontMetrics().stringWidth("Gepauzeerd"), 400 / 2);
        }

    }

    public void actionPerformed(ActionEvent e) {
        snake.beweeg();
        rots.veranderPositie();

        if (appel.snakeCollision()) {
            appel.veranderPositie(rots);
            score += 10;
            appelTeller++;
            snake.setMaakLanger(true);
        }

        if (appelTeller == 10) {
            appelTeller = 0;
            level++;
            vertraging -= 3;
            snake.setMoving(false);
            rots.addRots();
            rots.reset();
        }

        timer.setDelay(vertraging);

        checkGameOver();
        this.repaint();
    }

    public void checkGameOver() {
        if (snake.getX() < 0 || snake.getX() > 396 ||
                snake.getY() < 0 || snake.getY() > 396) {
            gameOver = true;
        }

        if (snake.snakeCollision()) {
            gameOver = true;
        }

        if (rots.snakeCollision()) {
            gameOver = true;
        }
    }

    class InputHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            if (!snake.isMoving()) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN ||
                        e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    snake.setMoving(true);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (snake.getyRichting() != 1) {
                    snake.setyRichting(-1);
                    snake.setxRichting(0);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (snake.getyRichting() != -1) {
                    snake.setyRichting(1);
                    snake.setxRichting(0);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (snake.getxRichting() != 1) {
                    snake.setxRichting(-1);
                    snake.setyRichting(0);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (snake.getxRichting() != -1) {
                    snake.setxRichting(1);
                    snake.setyRichting(0);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if(gameOver) {
                    gameOver = false;
                    score = 0;
                    appelTeller = 0;
                    level = 1;
                    vertraging = 60;
                    snake.reset();
                    rots.reset();
                    rots.setAantal(10);
                    appel.veranderPositie(rots);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (startScherm) {
                    startScherm = false;
                    snake.setMoving(true);
                } else if (!gepauzeerd && !gameOver) {
                    gepauzeerd = true;
                } else {
                    gepauzeerd = false;
                    snake.setMoving(true);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }
    }

}
