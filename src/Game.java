import javax.swing.*;

public class Game extends JFrame{

    public static void main(String[] args) {
        JPanel paneel = new GamePaneel();
        JFrame frame = new Game();
        frame.setSize(400, 410);
        frame.setTitle("Ultimate Snake 2K16");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(paneel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        paneel.requestFocusInWindow();
    }

}