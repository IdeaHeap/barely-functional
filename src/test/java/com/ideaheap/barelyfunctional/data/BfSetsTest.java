package com.ideaheap.barelyfunctional.data;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static com.ideaheap.barelyfunctional.data.BfSets.*;
import static org.junit.Assert.*;

/**
 * Created by nwertzberger on 4/29/16.
 */
public class BfSetsTest {

    @Test
    public void sets_justDoSetStuff() throws Exception {
        assertEquals(ImmutableSet.of(1, 2, 3), set(1, 2, 3)); // (1, 2, 3)
        assertEquals(set(1, 2, 3, 4, 5), assoc(set(1, 2, 3), 4, 5));
        assertEquals(set(1, 2, 3, 4, 5), assoc(set(1, 2, 3), 3, 4, 5));
        assertEquals(set(1, 2, 3, 4, 5), union(set(1, 2, 3), set(4, 5)));
        assertEquals(set(1, 2), intersection(set(1, 2, 3), set(1, 2, 4, 5)));
        assertEquals(set(1, 2), intersection(set(1, 2, 3, 4), set(1, 2, 4, 5), set(1, 2, 3, 5)));
        assertEquals(set(1, 3), xor(set(1, 2), set(2, 3)));
        assertEquals(set(1, 3, 4), xor(set(1, 2), set(2, 3), set(4)));
        assertEquals(set(1), not(set(1, 2, 3), set(2, 3)));
        assertEquals(set(1), dissoc(set(1, 2, 3), 2, 3, 4));
    }

}