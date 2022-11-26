import java.awt.*;
class Properties {
    static final int BOARD_COLUMNS   = 40;
    static final int BOARD_ROWS      = 20;
    static final int SQUARE_SIZE     = 30;
    static Color backgroundColor    = new Color(53, 53, 53);
    static Color snakeColor         = new Color(0, 255, 255);
    static Color foodColor          = new Color(211, 87, 45);
    static final int START_X         = BOARD_COLUMNS / 2;
    static final int START_Y         = BOARD_ROWS / 2;


    static Theme theme = Theme.Dark;

    static Theme getTheme() {
        return theme;
    }


    static void Dark () {
        Properties.backgroundColor = new Color(53, 53, 53);
        Properties.snakeColor = new Color(0, 254, 254);
        Properties.foodColor = new Color(211, 87, 45);
        Properties.theme = Properties.Theme.Dark;
    }
    enum Theme {
        Dark,
    }
}