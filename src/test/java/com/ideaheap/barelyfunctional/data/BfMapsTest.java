package com.ideaheap.barelyfunctional.data;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static com.ideaheap.barelyfunctional.data.BfMaps.*;
import static org.junit.Assert.*;

public class BfMapsTest {

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



}