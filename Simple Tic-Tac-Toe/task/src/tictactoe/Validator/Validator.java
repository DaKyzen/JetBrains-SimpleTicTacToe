package tictactoe.Validator;

import java.util.List;

public interface Validator {
    boolean isValid();
    List<String> getErrorMessages();
    void setInput(String input);
    void resetErrorMessages();
}
