package lt.codeacademy.psgames.service;


import lt.codeacademy.psgames.dto.Game;
import lt.codeacademy.psgames.entity.GameEntity;
import lt.codeacademy.psgames.exception.GameNotExistException;
import lt.codeacademy.psgames.repository.GameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void createGame(Game game) {
        gameRepository.save(GameEntity.convert(game));
    }

    public Page<Game> getGames(Pageable pageable) {
        return gameRepository.findAll(pageable)
                .map(Game::convert);
    }

    public List<Game> getGames() {
        return gameRepository.findAll()
                .stream()
                .map(Game::convert)
                .toList();
    }

    public Game getGame(UUID id) {
        return gameRepository.findById(id)
                .map(Game::convert)
                .orElseThrow(() -> new GameNotExistException(id));
    }

    public void updateGame(Game game) {
        gameRepository.save(GameEntity.convert(game));
    }

    public void delete(UUID id) {
        gameRepository.deleteById(id);
    }

    public List<Game> getGamesByGenre(String genre) {
        return gameRepository.findAllByGenre(genre).stream()
                .map(Game::convert)
                .toList();
    }

    public List<Game> getGamesByGenreAndPrice(String genre, BigDecimal price) {
        return gameRepository.getGamesByGenreAndPrice(genre, price).stream()
                .map(Game::convert)
                .toList();
    }
}