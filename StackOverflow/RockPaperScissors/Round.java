import java.util.Optional;
import java.util.NoSuchElementException;

final class Round {

    private final Move aMove;
    private final Move bMove;

    private Round(Move aMove, Move bMove) {
        this.aMove = aMove;
        this.bMove = bMove;
    }

    public Optional<Player> getWinner() {
        return aMove == bMove ? Optional.empty() :
                Optional.of(aMove.beats(bMove) ? Player.A : Player.B);
    }

    public static Round of(Player aPlayer, Player bPlayer) {
        if (aPlayer.hasNext() && bPlayer.hasNext()) {
            return new Round(aPlayer.next(), bPlayer.next());
        }
        throw new NoSuchElementException("No more moves by player "
                + (aPlayer.hasNext() ? Player.B : Player.A));
    }
}