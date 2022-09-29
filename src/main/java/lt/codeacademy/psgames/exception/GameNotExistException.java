package lt.codeacademy.psgames.exception;

import java.util.UUID;

public class GameNotExistException extends RuntimeException{
    private final UUID gameId;

    public GameNotExistException(UUID gameId) {
        this.gameId = gameId;
    }

    public UUID getGameId()
    {
        return gameId;
    }
}
