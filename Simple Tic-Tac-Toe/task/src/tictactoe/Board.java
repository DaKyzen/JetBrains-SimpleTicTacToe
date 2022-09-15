package tictactoe;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Board {
    public enum MatchOutcome {
        X_WINS("X wins"),
        O_WINS("O wins"),
        DRAW("Draw"),
        IMPOSSIBLE("Impossible"),
        GAME_NOT_FINISHED("Game not finished");

        private final String description;

        MatchOutcome(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }
    }
    public enum Turn {
        X("X"),
        O("O");

        private final String description;

        Turn(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }
    }
    private final String[][] boardState;
    private Turn turn;

    public Board() {
        this.boardState = new String[3][3];
        this.turn = Turn.X;
        initialiseEmptyBoardState();
    }

    public void insertCoordinate(Coordinate coordinate, Turn turn) {
        boardState[coordinate.getRowCoordinateIndex()][coordinate.getColumnCoordinateIndex()] = turn.getDescription();
    }

    public boolean isOccupied(Coordinate coordinate) {
        return Objects.equals(boardState[coordinate.getRowCoordinateIndex()][coordinate.getColumnCoordinateIndex()], Turn.X.getDescription()) ||
                Objects.equals(boardState[coordinate.getRowCoordinateIndex()][coordinate.getColumnCoordinateIndex()], Turn.O.getDescription());
    }

    public Turn getCurrentTurn() {
        return turn;
    }
    public void changeTurn() {
        if (turn == Turn.X)
            turn = Turn.O;
        else
            turn = Turn.X;
    }


    public void printGrid() {
        printSeparator();

        for (String[] row : boardState) {
            System.out.print("| ");

            for (String column : row)
                System.out.print(column + " ");

            System.out.println("|");
        }
        printSeparator();
    }

    public MatchOutcome getMatchOutcome() {
        String boardStateAsString = Arrays.stream(boardState)
                .flatMap(Arrays::stream)
                .collect(Collectors.joining());
        Pattern hasXPattern = Pattern.compile("X");
        Pattern hasOPattern = Pattern.compile("O");
        Pattern hasSpacesPattern = Pattern.compile("_");

        boolean xWin = isWinner('X', boardStateAsString);
        boolean oWin = isWinner('O', boardStateAsString);
        boolean hasAtLeastFiveX = Utility.countMatches(hasXPattern, boardStateAsString) > 5;
        boolean hasAtLeastFiveO = Utility.countMatches(hasOPattern, boardStateAsString) > 5;
        boolean hasSpaces = Utility.countMatches(hasSpacesPattern, boardStateAsString) > 0;
        boolean hasCorrectNumMoves = Math.abs(Utility.countMatches(hasXPattern, boardStateAsString) - Utility.countMatches(hasOPattern, boardStateAsString)) <= 1;

        MatchOutcome matchOutcome;

        if (hasSpaces && !xWin && !oWin && hasCorrectNumMoves)
            matchOutcome = MatchOutcome.GAME_NOT_FINISHED;
        else if ((xWin && oWin) || (hasAtLeastFiveO && hasAtLeastFiveX) || !hasCorrectNumMoves)
            matchOutcome = MatchOutcome.IMPOSSIBLE;
        else if (xWin)
            matchOutcome = MatchOutcome.X_WINS;
        else if (oWin)
            matchOutcome = MatchOutcome.O_WINS;
        else  matchOutcome = MatchOutcome.DRAW;
        return matchOutcome;
    }

    private void printSeparator() {
        System.out.println("---------");
    }

    private boolean isWinner(char character, String input) {
        return hasThreeInRow(character, input) || hasThreeInColumn(character, input) || hasThreeInDiagonal(character, input);
    }
    private boolean hasThreeInRow(char character, String input) {
        /*
         * Three in a row can only form at the starting index of 0, 3, 6
         * */
        for (int i = 0; i < input.length(); i += 3) {
            if (input.charAt(i) == character && input.charAt(i + 1) == character && input.charAt(i + 2) == character)
                return true;
        }

        return false;
    }
    private boolean hasThreeInColumn(char character, String input) {
        /*
         * Three in a column can only form as follows (where numbers refer to index): 0, 3, 6 | 1, 4, 7 | 2, 5, 8
         * */
        for (int i = 0; i < 3; i ++) {
            if (input.charAt(i) == character && input.charAt(i + 3) == character && input.charAt(i + 6) == character)
                return true;
        }

        return false;
    }
    private boolean hasThreeInDiagonal(char character, String input) {
        return hasThreeInBackDiagonal(character, input) || hasThreeInForwardsDiagonal(character, input);
    }
    private boolean hasThreeInBackDiagonal(char character, String input) {
        /*
         * Three in a backwards diagonal can only form as follows (where numbers refer to index): 0, 4, 8
         * */
        return input.charAt(0) == character && input.charAt(4) == character && input.charAt(8) == character;
    }
    private boolean hasThreeInForwardsDiagonal(char character, String input) {
        /*
         * Three in a backwards diagonal can only form as follows (where numbers refer to index): 2, 4, 6
         * */
        return input.charAt(2) == character && input.charAt(4) == character && input.charAt(6) == character;
    }

    private void initialiseBoardState(String startingBoardState) {
        for (int i = 0; i < startingBoardState.length(); i++) {
            int row = i / 3;
            int column = i % 3;
            this.boardState[row][column] = String.valueOf(startingBoardState.charAt(i));
        }
    }
    private void initialiseEmptyBoardState() {
        initialiseBoardState("_________");
    }
}
