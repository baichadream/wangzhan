#include <iostream>
#include <ncurses.h>
#include <deque>

using namespace std;

// 定义方向
enum Direction {UP, DOWN, LEFT, RIGHT};

int main() {
    // 初始化 ncurses
    initscr();
    clear();
    noecho();
    cbreak();
    keypad(stdscr, TRUE);
    nodelay(stdscr, TRUE);

    // 初始化游戏参数
    int maxY, maxX;
    getmaxyx(stdscr, maxY, maxX);
    int snakeX = maxX / 2, snakeY = maxY / 2;
    int score = 0;
    Direction dir = RIGHT;
    deque<pair<int, int>> snake = {{snakeY, snakeX}, {snakeY, snakeX-1}, {snakeY, snakeX-2}};

    // 游戏循环
    while (true) {
        clear();

        // 绘制蛇身
        for (auto const &segment: snake) {
            mvprintw(segment.first, segment.second, "o");
        }

        // 绘制食物
        mvprintw(y, x, "@");

        // 获取玩家输入并更改方向
        int input = getch();
        switch(input) {
            case KEY_UP:
                dir = UP;
                break;
            case KEY_DOWN:
                dir = DOWN;
                break;
            case KEY_LEFT:
                dir = LEFT;
                break;
            case KEY_RIGHT:
                dir = RIGHT;
                break;
        }

        // 根据方向移动蛇头
        pair<int, int> newHead = snake.front();
        switch(dir) {
            case UP:
                newHead.first--;
                break;
            case DOWN:
                newHead.first++;
                break;
            case LEFT:
                newHead.second--;
                break;
            case RIGHT:
                newHead.second++;
                break;
        }
        snake.push_front(newHead);

        // 检查蛇是否吃到食物

        // 检查游戏是否结束

        // 在终端上刷新
        refresh();

        // 控制游戏速度
        // usleep(100000);
    }

    // 清理并退出
    endwin();
    return 0;
}
