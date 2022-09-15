package tictactoe;

import tictactoe.Validator.CoordinateValidator;
import java.util.List;

public class Coordinate {
    private int rowCoordinate = -1;
    private int columnCoordinate = -1;

    public Coordinate(int rowCoordinate, int columnCoordinate) {
        this.rowCoordinate = rowCoordinate;
        this.columnCoordinate = columnCoordinate;
    }

    public Coordinate(CoordinateValidator validator) throws InvalidCoordinateInputException {
        if (!validator.isValid())
            throw new InvalidCoordinateInputException(validator.getErrorMessages());
        else {
            this.rowCoordinate = validator.getRowCoordinate();
            this.columnCoordinate = validator.getColumnCoordinate();
        }
    }

    public int getRowCoordinateIndex() {
        return rowCoordinate - 1;
    }
    public int getColumnCoordinateIndex() {
        return columnCoordinate - 1;
    }


    static class InvalidCoordinateInputException extends Exception {
        private final List<String> messages;
        public InvalidCoordinateInputException(List<String> messages) {
            this.messages = messages;
        }

        @Override
        public String getMessage() {
            return messages.size() > 0 ? messages.get(0) : "";
        }
    }


}
