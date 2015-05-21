import java.util.logging.Logger;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Optional;
import java.util.EnumMap;

public final class Game {

    private static final int NUMBER_OF_GAMES = 100;
    private static final Logger logger = Logger.getLogger(Game.class.getName());

    private static Map<Player, Long> play() {
        return Stream.generate(() -> Round.of(Player.A, Player.B))
                .limit(NUMBER_OF_GAMES)
                .map(Round::getWinner)
                .filter(Optional::isPresent)
                .collect(Collectors.groupingBy(Optional::get,
                        () -> new EnumMap<>(Player.class), Collectors.counting()));
    }

    public static void main(String[] args) {
        Map<Player, Long> results = Game.play();
        int wins = results.values().stream().mapToInt(Long::intValue).sum();
        results.forEach((k, v) -> logger.info("Player {"+k+"} wins {"+v+"} of {"+Integer.valueOf(NUMBER_OF_GAMES)+"} games"));
        Integer tie = Integer.valueOf(NUMBER_OF_GAMES - wins);
        Integer games = Integer.valueOf(NUMBER_OF_GAMES);
        logger.info("Tie: {"+tie+"} of {"+games+"} games");
    }
}