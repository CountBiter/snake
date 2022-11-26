
class Square {

    private Entity entity;
    private int x;
    private int y;


    public Square (int x, int y) {
        this(Entity.Empty, x, y);
    }

    Square (Entity entity, int x, int y) {
        this.entity = entity;
        this.x = x;
        this.y = y;
    }
    void setEntity (Entity entity) {
        this.entity = entity;
    }

    Entity getEntity () {
        return entity;
    }

    int getX () {
        return x;
    }

    int getY () {
        return y;
    }

    @Override
    public boolean equals (Object obj) {

        if (!(obj instanceof Square)) {
            return false;
        }

        Square sq = (Square) obj;
        return sq.x == x && sq.y == y;
    }

    @Override
    public String toString () {
        return entity + " at (" + x + ", " + y + ")";
    }
    enum Entity {
        Empty,
        Snake,
        Food
    }
}