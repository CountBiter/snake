import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Window extends JFrame {

    private Engine engine;

    private Window() {
        engine = createEngine();
        setWindowProperties();
    }

    private Engine createEngine () {

        Container cp = getContentPane();
        GameBoard gameBoard = new GameBoard();
        Engine engine = new Engine(gameBoard);

        int canvasWidth = Properties.SQUARE_SIZE * Properties.BOARD_COLUMNS;
        int canvasHeight = Properties.SQUARE_SIZE * Properties.BOARD_ROWS;
        engine.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        addKeyListener(new MyKeyAdapter());

        cp.add(engine);

        return engine;
    }

    private void setWindowProperties () {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Змейка - Счет: 0");
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void startGame (Engine engine) {
        Thread th = new Thread(engine);
        th.start();
    }
    private class Engine extends JPanel implements Runnable {

        private GameBoard gameBoard;
        private boolean running = false;

        private Engine(GameBoard gameBoard) {
            this.gameBoard = gameBoard;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            setBackground(Properties.backgroundColor);
            gameBoard.paint(graphics);
        }

        public void run () {

            long lastTime = System.nanoTime();
            double elapsedTime = 0.0;
            double FPS = 15.0;

            while (true) {

                long now = System.nanoTime();
                elapsedTime += ((now - lastTime) / 1_000_000_000.0) * FPS;
                lastTime = System.nanoTime();

                if (elapsedTime >= 1) {
                    gameBoard.update();
                    setTitle("Змейка - Счет: " + gameBoard.getScore());
                    elapsedTime--;

                }

                sleep();

                repaint();
            }
        }

    }
    private void sleep () {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent keyEvent) {

            if (!engine.running) {
                startGame(engine);
                engine.running = true;
            }

            if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                engine.gameBoard.directionLeft();
            } else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                engine.gameBoard.directionRight();
            } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
                engine.gameBoard.directionUp();
            } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                engine.gameBoard.directionDown();
            }

        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Window());
    }
}