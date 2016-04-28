package com.ideaheap.barelyfunctional;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.Arrays;

import static com.ideaheap.barelyfunctional.BarelyFunctional.*;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by nwertzberger on 4/27/16.
 */
public class BarelyFunctionalTest {

    @Test
    public void lists_justWorkLikeLists() throws Exception {
        assertEquals(Arrays.asList(1, 2, 3, 4), list(1, 2, 3, 4));
        assertEquals(Arrays.asList(1, 2, 3, 4), push(list(1, 2, 3), 4));
        assertEquals(Arrays.asList(1, 2, 3, 4), unshift(list(2, 3, 4), 1));
        assertEquals(Arrays.asList(1, 2, 3, 4), assoc(list(1, 1, 3, 4), 1, 2));
        assertEquals(Arrays.asList(1, 2, 3, 4), assoc(list(1, 2, 3, 2), 3, 4));
        assertEquals(Arrays.asList(1, 2, 3, 4), merge(list(1, 2), list(3), list(4)));
        assertEquals(Arrays.asList(1, 2, 3, 4), merge(list(1, 2), list(3, 4)));
    }

    @Test
    public void maps_justWorkLikeMaps() throws Exception {
        assertEquals(ImmutableMap.of("a", 1), map(e("a", 1)));
        assertEquals(ImmutableMap.of("a", 1, "b", 2), map(e("a", 1), e("b", 2)));
        assertEquals(
            ImmutableMap.of("a", 1, "b", 2, "c", 3),
            assoc(map(e("a", 1), e("b", 2)), e("c", 3))
        );
        assertEquals(
            ImmutableMap.of("a", 1, "b", 2, "c", 3),
            assoc(map(e("a", 1), e("b", 2)), "c", 3)
        );
        assertEquals(
            ImmutableMap.of("a", 2, "b", 2),
            assoc(map(e("a", 1), e("b", 2)), "a", 2)
        );
        assertEquals(
            ImmutableMap.of("a", 1, "b", 2, "c", 3),
            merge(map(e("a", 1), e("b", 2)), map(e("c", 3)))
        );
        assertEquals(
            ImmutableMap.of("a", 1, "b", 2, "c", 3),
            assoc(
                map(e("a", 1), e("b", 1)),
                map(e("b", 2), e("c", 3))
            )
        );
        assertEquals(map(e("a", 1)), dissoc(map(e("a", 1), e("b", 2)), "b"));
    }

    @Test
    public void sets_justDoSetStuff() throws Exception {
        assertEquals(ImmutableSet.of(1, 2, 3), set(1, 2, 3)); // (1, 2, 3)
        assertEquals(set(1, 2, 3, 4, 5), assoc(set(1, 2, 3), 4, 5));
        assertEquals(set(1, 2, 3, 4, 5), assoc(set(1, 2, 3), 3, 4, 5));
        assertEquals(set(1, 2, 3, 4, 5), union(set(1, 2, 3), set(4, 5)));
        assertEquals(set(1, 2), intersection(set(1, 2, 3), set(1, 2, 4, 5)));
        assertEquals(set(1, 2), intersection(set(1, 2, 3, 4), set(1, 2, 4, 5), set(1, 2, 3, 5)));
        assertEquals(set(1, 3), xor(set(1,2), set(2, 3)));
        assertEquals(set(1, 3, 4), xor(set(1,2), set(2, 3), set(4)));
        assertEquals(set(1), not(set(1,2,3), set(2,3)));

    }

}