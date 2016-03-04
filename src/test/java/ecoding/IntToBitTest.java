package ecoding;

import encoding.IntToBit;
import org.junit.Test;

import java.util.BitSet;

import static org.fest.assertions.api.Assertions.assertThat;

public class IntToBitTest {

    private IntToBit bitEncoding;

    @Test
    public void
    it_can_add_a_value() {
        int bitsPerElement = 3;
        bitEncoding = aBitSetWith(3, 3);
        assertBitSet(0, 1, 1);
    }

    @Test
    public void
    it_can_add_several_values() {
        int bitsPerElement = 3;
        bitEncoding = aBitSetWith(bitsPerElement, 1, 2, 3, 4);
        assertBitSet(0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0);
    }

    @Test
    public void
    it_can_get_a_value() {
        bitEncoding = aBitSetWith(4, 2, 10, 15, 1, 0);

        assertThat(bitEncoding.get(2)).isEqualTo(15);
        assertThat(bitEncoding.get(4)).isEqualTo(0);
    }

    private IntToBit aBitSetWith(int bitsPerElement, int... values) {
        IntToBit bitEncoding = new IntToBit(values.length, bitsPerElement);
        for (int value : values) {
            bitEncoding.add(value);
        }
        return bitEncoding;
    }

    private void assertBitSet(int... bits) {
        BitSet expected = new BitSet(3 * 32);

        for (int i = 0; i < bits.length; i++) {
            if (bits[i] != 0) {
                expected.set(i);
            }
        }
        assertThat(bitEncoding.bitSet()).isEqualTo(expected);
    }
}