package tictactoe.Validator;

import tictactoe.Board;
import tictactoe.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class CoordinateValidator implements Validator {
    private final List<String> errorMessages = new ArrayList<>();
    private final Board boardState;
    private String coordinateInput;
    private int rowCoordinate = -1;
    private int columnCoordinate = -1;

    public CoordinateValidator(Board boardState) {
        this.boardState = boardState;
    }

    @Override
    public List<String> getErrorMessages() {
        return this.errorMessages;
    }

    @Override
    public void setInput(String input) {
        this.coordinateInput = input;
    }

    @Override
    public boolean isValid() {
        String[] coordinates = coordinateInput.split(" ");
        checkCoordinatesAreNumbers(coordinates);
        checkCoordinatesAreInRange();
        if (errorMessages.size() == 0)
            checkCellOccupied();
        return errorMessages.size() == 0;
    }

    @Override
    public void resetErrorMessages() {
        this.errorMessages.clear();
    }

    public int getRowCoordinate() {
        return rowCoordinate;
    }

    public int getColumnCoordinate() {
        return columnCoordinate;
    }

    private void checkCoordinatesAreInRange() {
        boolean isCoordinatesInValidRange = rowCoordinate > 0 && rowCoordinate < 4 && columnCoordinate > 0 && columnCoordinate < 4;
        if (!isCoordinatesInValidRange)
            errorMessages.add("Coordinates should be from 1 to 3");
    }

    private void checkCoordinatesAreNumbers(String[] coordinates) {
        try {
            rowCoordinate = Integer.parseInt(coordinates[0]);
            columnCoordinate = Integer.parseInt(coordinates[1]);
        } catch (Exception e) {
            errorMessages.add("You should enter numbers!");
        }
    }

    private void checkCellOccupied() {
        if (this.boardState.isOccupied(new Coordinate(rowCoordinate, columnCoordinate)))
            errorMessages.add("This cell is occupied! Choose another one!");
    }


}
