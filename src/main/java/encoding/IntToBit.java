package encoding;

import java.util.BitSet;

public class IntToBit {

    private final BitSet bitset;
    private final int bitsPerElement;

    private int nextBit;

    public IntToBit(int numberOfElements, int bitsPerElement) {
        this.bitsPerElement = bitsPerElement;
        this.bitset = new BitSet(numberOfElements * bitsPerElement);
    }

    public void add(int integer) {
        for (int i = 0; i < bitsPerElement; ++i) {
            final boolean isSet = (integer & (1 << i)) != 0;
            bitset.set(nextBit + bitsPerElement - (i + 1), isSet);
        }
        nextBit += bitsPerElement;
    }

    public int get(int position) {
        final int startPosition = position * bitsPerElement;

        final BitSet valueInBits = bitset.get(startPosition, startPosition + bitsPerElement);

        int result = 0;
        for (int i = 0; i < bitsPerElement; ++i) {
            final boolean isSet = valueInBits.get(i);
            result += isSet ? 1 << (bitsPerElement - 1 - i) : 0;
        }

        return result;
    }

    public BitSet bitSet() {
        return (BitSet) bitset.clone();
    }
}
