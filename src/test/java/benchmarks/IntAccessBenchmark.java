package benchmarks;

import encoding.IntToBit;
import org.openjdk.jmh.annotations.*;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

@State(Scope.Thread)
@BenchmarkMode(Mode.SampleTime)
@OutputTimeUnit(NANOSECONDS)
public class IntAccessBenchmark {

    public static final int NUMBER_OF_ELEMENTS = 3000000;
    private int position = 2092309;
    private final IntToBit vector = setup();

    public IntToBit setup() {
        IntToBit vector = new IntToBit(NUMBER_OF_ELEMENTS, 4);
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            vector.add(i % 15);
        }
        return vector;
    }

    @Benchmark
    public int measureGet() {
        return vector.get(position);
    }
}
