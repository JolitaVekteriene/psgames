package lt.codeacademy.psgames.advice;

import lt.codeacademy.psgames.exception.GameNotExistException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.beans.PropertyEditor;
import java.util.Date;

@ControllerAdvice
public class GameAdvice {

    @ExceptionHandler(GameNotExistException.class)
    public String handlingGameNotFound(GameNotExistException exception, Model model) {
        model.addAttribute("gameId", exception.getGameId());

        return "gameNotFound";
    }
}