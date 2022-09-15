package tictactoe;

import tictactoe.Validator.CoordinateValidator;
import java.util.Scanner;

public class Main {



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Board board = new Board();

        board.printGrid();
        while (board.getMatchOutcome() == Board.MatchOutcome.GAME_NOT_FINISHED) {
            enterCoordinate(board);
            board.printGrid();
        }

        System.out.println(board.getMatchOutcome().getDescription());

    }

    private static void enterCoordinate(Board board) {
        String userCoordinates;
        Coordinate coordinate = new Coordinate(0, 0);
        CoordinateValidator coordinateValidator = new CoordinateValidator(board);
        boolean isValidInput = false;

        do {
            userCoordinates = askInput();
            coordinateValidator.resetErrorMessages();
            coordinateValidator.setInput(userCoordinates);
            try {
                coordinate = new Coordinate(coordinateValidator);
                isValidInput = true;
            } catch (Coordinate.InvalidCoordinateInputException e) {
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                System.out.println("Unknown error. Ending program");
                break;
            }
        } while (!isValidInput);

        board.insertCoordinate(coordinate, board.getCurrentTurn());
        board.changeTurn();
    }

    private static String askInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }



}
