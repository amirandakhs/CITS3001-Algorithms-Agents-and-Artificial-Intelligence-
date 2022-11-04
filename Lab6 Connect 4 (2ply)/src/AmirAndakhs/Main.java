package AmirAndakhs;

public class Main {

    public static void main(String[] args) {
     //
    }
}
class Connect4Agent extends Agent{

    /**
     * The move method selects the column to drop the disc into, given the board and the symbol.
     * @param board an array of Strings where each string represents a column of the board.
     * @param symbol the symbol representing the agent's tokens.
     * @param lastMove the lastMove your opponent played, or -1 if this is the first move of the game.
     * @return, the column the player chooses, given the game state.
     * **/
    public static int utilityFunction(char[][] board, char symbol) {

        char symbol1;
        if(symbol == 'X'){
            symbol1 = 'O';
        }else {
            symbol1 = 'X';
        }
        int Xpoint = 0;
        int Opoint = 0;
        //checking for the winner
        char winner = win(board);
        if (winner != ' ') {
            if(winner == symbol) {
                Xpoint += 90;
            } else {
                Opoint += 90;
            }
        }
        // Evaluating state of the game
        Xpoint = Xpoint + checking3(board, symbol)*10 + checking2(board, symbol)*4;
        Opoint  = Opoint + checking3(board, symbol1)*5 + checking2(board, symbol1)*2;
        return  Xpoint- Opoint;
    }
    //end utilityFunction
    //checking 4 (winning state)
    public static char win(char[][] board){
        char winner = ' ';
        //checking horizontally
        for (int j=0; j<6; j++) {
            for (int i=0; i<4; i++) {
                if (board[i][j] == board[i+1][j] && board[i+1][j] == board[i+2][j] && board[i+2][j] == board[i+3][j] && board[i][j] != ' ') {
                    winner = board[i][j];
                    return winner;
                }
            }
        }
        // checking vertically

        for (int i=0; i<7; i++) {
            for (int j=0; j<=2; j++) {
                if (board[i][j] == board[i][j+1] && board[i][j+1] == board[i][j+2] && board[i][j+2] == board[i][j+3] && board[i][j] != ' ') {
                    winner = board[i][j];
                    return winner;
                }
            }
        }

        // checking positive diagonal
        for (int i=0; i<4; i++) {
            for (int j=0; j<3; j++) {
                if (board[i][j] == board[i+1][j+1] && board[i+1][j+1] == board[i+2][j+2] && board[i+2][j+2] == board[i+3][j+3] && board[i][j] != ' ') {
                    winner = board[i][j];
                    return winner;
                }
            }
        }
        //checking negative diagonal
        for (int i=0; i<4; i++) {
            for (int j=5; j>2; j--) {
                {
                    if (board[i][j] == board[i+1][j-1] && board[i+1][j-1] == board[i+2][j-2] && board[i+2][j-2] == board[i+3][j-3]  && board[i][j] != ' ') {
                        winner = board[i][j];
                        return winner;
                    }
                }
            }
        }
        return winner;

    }

    //checking 3
    public static int checking3(char[][] board, char symbol) {
        int num = 0;
        //In row
        for (int j=0; j<6; j++) {
            for (int i=0; i<5; i++) {
                if (board[i][j] == board[i+1][j] && board[i+1][j] == board[i+2][j] &&  board[i][j] == symbol) {
                    num++;
                }
            }
        }
        // checking vertically
        for (int i=0; i<7; i++) {
            for (int j=0; j<=3; j++) {
                if (board[i][j] == board[i][j+1] && board[i][j+1] == board[i][j+2] &&  board[i][j] == symbol) {
                    num++;
                }
            }
        }

        // checking positive diagonal
        for (int i=0; i<5; i++) {
            for (int j=0; j<4; j++) {
                if (board[i][j] == board[i+1][j+1] && board[i+1][j+1] == board[i+2][j+2] && board[i][j] == symbol) {
                    num++;
                }
            }
        }
        //checking negative diagonal
        for (int i=0; i<5; i++) {
            for (int j=5; j>1; j--) {
                {
                    if (board[i][j] == board[i+1][j-1] && board[i+1][j-1] == board[i+2][j-2] && board[i][j] == symbol) {
                        num++;
                    }
                }
            }
        }
        return num;
    }
    //checking twos
    public static int checking2(char[][] board, char symbol){
        int num = 0;
        //In row
        for (int j=0; j<6; j++) {
            for (int i=0; i<6; i++) {
                if (board[i][j] == board[i+1][j] &&  board[i][j] == symbol) {
                    num++;
                }
            }
        }
        // checking vertically
        for (int i=0; i<7; i++) {
            for (int j=0; j<=4; j++) {
                if (board[i][j] == board[i][j+1] &&  board[i][j] == symbol) {
                    num++;
                }
            }
        }

        // checking positive diagonal
        for (int i=0; i<6; i++) {
            for (int j=0; j<5; j++) {
                if (board[i][j] == board[i+1][j+1] &&  board[i][j] == symbol) {
                    num++;
                }
            }
        }
        //checking negative diagonal
        for (int i=0; i<6; i++) {
            for (int j=5; j>0; j--) {
                {
                    if (board[i][j] == board[i+1][j-1] &&  board[i][j] == symbol) {
                        num++;
                    }
                }
            }
        }
        return num;
    }

    //adding a mark to specific col
    public static char[][] adding(char[][] board, char mark, int col, int row){

        char[][] temp = new char[7][6];
        for(int i =0; i < 7;i++){
            for(int j = 0; j < 6; j++){
                temp[i][j] = board[i][j];
            }
        }


        temp[col][row] = mark;

        return temp;
    }
    public static int min(char[][] board,int depth, char symbol){
        char symbol1;
        if(symbol == 'X'){
            symbol1 = 'O';
        }else {
            symbol1 = 'X';
        }
        if (depth == 4){
            int score = Integer.MAX_VALUE;
            for(int i =0; i < 7;i++){
                int row = canMove(board, i);
                if(row != -1){
                    char[][] temp = adding(board,symbol1,i,row);
                    int point = utilityFunction(temp, symbol);
                    if(point < score){
                        score =point;
                    }
                }
            }
            return score;
        }
        else {
            int point = Integer.MAX_VALUE;
            for (int i =0; i<7; i++){
                int row = canMove(board, i);
                if(row != -1){
                    char[][] temp = adding(board,symbol1,i,row);
                    int Max = max(temp, depth+1,symbol);
                    if (Max < point){
                        point = Max;
                    }
                }


            }
            return point;

        }

    }
    public static int max(char[][] board,int depth, char symbol){
        int col = 0;
        int point = Integer.MIN_VALUE;
        for (int i =0; i<7; i++){
            int row = canMove(board, i);
            if(row != -1){
                char[][] temp = adding(board,symbol,i,row);
                int Max = min(temp, depth+1,symbol);
                if (Max > point){
                    point = Max;
                    col = i;
                }
            }


        }
        if(depth ==1){
            return col;
        }else {
            return point;
        }


    }
    public static int canMove(char[][] board, int col){
        int lastRow= -1;// -1 means it's full
        if (col < 0 || col>6){
            return -1;
        }

        for (int j =0; j < 6; j++ ){
            if(board[col][j] == ' '){
                lastRow = j;
                break;
            }
        }
        return lastRow;

    }

    public int move(String[] board, char symbol, int lastMove){
        char[][] boardArray = new char[7][6];
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 6; j++) {
                int maxIndex = board[i].length() - 1;
                if(j <= maxIndex){
                    boardArray[i][j] = board[i].charAt(j);
                }else {
                    boardArray[i][j] = ' ';
                }

            }
        }
        if(lastMove == -1){
            return 3;
        }

        return max(boardArray,1,symbol);
    }
}

