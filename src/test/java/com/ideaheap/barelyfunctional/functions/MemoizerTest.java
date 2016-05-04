package com.ideaheap.barelyfunctional.functions;

import com.google.common.base.Suppliers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.ideaheap.barelyfunctional.functions.Memoizers.memoize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;

/**
 * Created by nwertzberger on 5/3/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MemoizerTest {

    @Test
    public void functions_memoize() throws Exception {
        Function<String, String> mockFun = mock(Function.class);
        Function<String, String> memo = memoize(mockFun);
        memo.apply("a");
        memo.apply("a");
        Mockito.verify(mockFun, times(1)).apply("a");

    }

    @Test
    public void memoize_doesntBlowUpHeap() throws Exception {
        AtomicInteger increment = new AtomicInteger(0);
        Function<String, Integer> memo = memoize((v) -> increment.incrementAndGet());

        String val = "a";
        for (int i = 0; i < 26; i++) {
            memo.apply(val);
            val = val + val;
        }
        for (int i = 0; i < 16; i++) {
            memo.apply(val + i);
        }

        int start = increment.get();
        memo.apply(new String("a"));
        int end = increment.get();

        assertNotEquals(start, end);
    }

    @Test
    public void memoize_doesEqualityOperator() throws Exception {
        Function<String, String> function = mock(Function.class);

        Function<String, String> memo = memoize(function);

        memo.apply(new String("a"));
        memo.apply(new String("b"));
        memo.apply(new String("a"));
        memo.apply("a");

        Mockito.verify(function, times(1)).apply("a");
    }

    @Test
    public void memoize_doesntHaveWeirdSideEffects() throws Exception {
        Function<String, String> fun1 = memoize((s)-> "a");
        Function<String, String> fun2 = memoize((s)-> "b");

        fun1.apply("a");
        String val = fun2.apply("a");
        assertEquals("b", val);
    }

    @Test
    public void memoize_memoizesBiFunctions() throws Exception {
        BiFunction<String, String, String> mockFunction = mock(BiFunction.class);
        BiFunction<String, String, String> memo = memoize(mockFunction);

        memo.apply("a", "b");
        memo.apply("a", "b");
        memo.apply("a", "b");

        Mockito.verify(mockFunction, times(1)).apply("a", "b");

    }

    @Test
    public void memoize_worksWithBiFunctions() throws Exception {
        AtomicInteger integer = new AtomicInteger(0);
        BiFunction<String, String, Integer> memo = memoize((a, b) -> integer.getAndIncrement());

        assertEquals(memo.apply("a", "b"), memo.apply("a", "b"));
        assertEquals((Integer)0, memo.apply("a", "b"));


    }

}