package lt.codeacademy.psgames.controller;

import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.psgames.dto.Game;
import lt.codeacademy.psgames.service.GameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/games/save")
    public String openCreateGame(Model model) {
        model.addAttribute("game", new Game());

        return "form/game";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/psgames/save")
    public String createGame(@Valid Game game, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            return "form/game";
        }

        gameService.createGame(game);
        log.debug("Game {}", game);

        redirectAttributes.addFlashAttribute("message", "lt.codeacademy.psgames.game.create.message.success");

        return "redirect:/games/save";
    }

    @GetMapping("/public/games/search")
    public String search(Model model,
                         @RequestParam(required = false) String genre,
                         @RequestParam(required = false) BigDecimal price) {

        if(genre != null && !genre.isBlank()) {
            if(price != null && !price.equals(0)) {
                model.addAttribute("games", gameService.getGamesByGenreAndPrice(genre, price));
            } else {
                model.addAttribute("games", gameService.getGamesByGenre(genre));
            }
        }

        return "games";
    }

    @GetMapping("/public/games")
    public String showGames(Model model, @SortDefault(sort = {"name"}, caseSensitive = false) Pageable pageable) {
        Page<Game> page = gameService.getGames(pageable);

        log.debug("Total elements: " + page.getTotalElements());
        model.addAttribute("gamesByPage", page);

        return "games";
    }

    @GetMapping("/public/games/{id}")
    public String openDetailPage(@PathVariable UUID id, Model model) {
        model.addAttribute("game", gameService.getGame(id));

        return "gameDetails";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/games/{id}/update")
    public String showUpdateForm(@PathVariable UUID id, Model model) {
        model.addAttribute("game", gameService.getGame(id));
        return "form/game";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/games/{id}/update")
    public String updateGame(@Valid Game game, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "form/game";
        }

        gameService.updateGame(game);

        return "redirect:/public/games";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/games/{id}/delete")
    public String deleteProduct(@PathVariable UUID id) {
        gameService.delete(id);
        log.debug(String.format("Element %s deleted successfully" , id));

        return "redirect:/public/games";
    }
}