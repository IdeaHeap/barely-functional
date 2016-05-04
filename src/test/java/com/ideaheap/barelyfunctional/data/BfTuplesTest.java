package com.ideaheap.barelyfunctional.data;

import org.junit.Test;

import static com.ideaheap.barelyfunctional.data.BfLists.list;
import static com.ideaheap.barelyfunctional.data.BfTuples.tuple;
import static org.junit.Assert.assertEquals;

/**
 * Created by nwertzberger on 5/3/16.
 */
public class BfTuplesTest {
    @Test
    public void tuples_justActLikeTuples() throws Exception {
        assertEquals("a", tuple("a").getValue0());
        assertEquals((Integer) 9, tuple("a", 9).getValue1());
        assertEquals((Long) 8L, tuple("a", 9, 8L).getValue2());
        assertEquals(list("a", 9, 8L), tuple("a", 9, 8L).toList());
        assertEquals(tuple("a", "b", 4, 5), tuple("a", "b").add(4, 5));
        assertEquals(tuple("b"), tuple("a", "b").removeFrom0());
        assertEquals("c", tuple("a", "b", "c").getValue2());
        assertEquals(list(1, 2), tuple("a", 9, 4L, list(1, 2)).getValue3());
        assertEquals((Integer) 11, tuple("a", 9, 8L, 10, 11).getValue4());
        assertEquals((Integer) 12, tuple("a", 9, 8L, 10, 11, 12).getValue5());
        assertEquals((Integer) 13, tuple("a", 9, 8L, 10, 11, 12, 13).getValue6());
        assertEquals("fourteen", tuple("a", 9, 8L, 10, 11, 12, 13, "fourteen").getValue7());
    }

}
