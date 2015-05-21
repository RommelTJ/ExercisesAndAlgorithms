enum Move {
    ROCK, PAPER, SCISSORS;

    boolean beats(Move another) {
        switch (this) {
            case ROCK:
                return another == SCISSORS;
            case PAPER:
                return another == ROCK;
            case SCISSORS:
                return another == PAPER;
            // note: see alternative below
            default:
                throw new IllegalStateException();
        }
        // alternatively, just throw here without the default case
        // throw new IllegalStateException();
    }
}