package hangman.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class GameModel {
    @NotBlank(message = "Please make a guess!")
    @Size(max = 1, message = "Please input only 1 character at a time!")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Please input only letters!")
    private String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
