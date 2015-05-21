import java.util.Iterator;
import java.util.Random;

enum Player implements Iterator<Move> {
    A() {
        @Override
        public Move next() {
            return Move.PAPER;
        }
    }, B() {
        @Override
        public Move next() {
            return Move.values()[GENERATOR.nextInt(Move.values().length)];
        }
    };

    private static final Random GENERATOR = new Random();

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}