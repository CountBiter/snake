import java.util.ArrayList;
import java.util.Iterator;
class Snake implements Iterable<Square> {

    private ArrayList<Square> snakeList;
    private int size = 1;
    private Square tail;

    Snake () {
        this(Properties.START_X, Properties.START_Y);
    }

    Snake (int startX, int startY) {
        snakeList = new ArrayList<>();
        snakeList.add(new Square(Square.Entity.Snake, startX, startY));
    }
    int getSize () {
        return size;
    }

    boolean moveLeft () {
        return move(Direction.LEFT);
    }

    boolean moveRight () {
        return move(Direction.RIGHT);
    }

    boolean moveUp () {
        return move(Direction.UP);
    }

    boolean moveDown () {
        return move(Direction.DOWN);
    }

    private synchronized boolean move (Direction direction) {

        int xOffset = 0;
        int yOffset = 0;

        if (direction == Direction.LEFT) {
            xOffset = -1;
        } else if (direction == Direction.RIGHT) {
            xOffset = 1;
        } else if (direction == Direction.UP) {
            yOffset = -1;
        } else if (direction == Direction.DOWN) {
            yOffset = 1;
        }
        Square currentHead = getHead();
        int oldX = currentHead.getX();
        int oldY = currentHead.getY();

        Square head = new Square(Square.Entity.Snake, oldX + xOffset, oldY + yOffset);

        if (contains(head)) return false;
        snakeList.add(0, head);
        removeTail();

        return true;
    }

    private synchronized void removeTail () {

        tail = snakeList.get(snakeList.size() - 1);

        if (snakeList.size() > size) {
            snakeList.remove(snakeList.size() - 1);
        }
    }

    Square getTail () {
        return tail;
    }

    Square getHead () {
        return snakeList.get(0);
    }

    void grow () {
        size++;
    }

    public void grow (int x) {
        size += x;
    }

    public synchronized Iterator<Square> iterator () {
        return snakeList.iterator();
    }
    boolean contains (Square sq) {
        for (Square element : this) {
            if (element.equals(sq)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();

        for (Square sq : this) {
            sb.append(sq);
            sb.append("\n");
        }

        return new String(sb);
    }

}