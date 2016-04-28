package com.ideaheap.barelyfunctional;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static com.ideaheap.barelyfunctional.Bf.*;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by nwertzberger on 4/27/16.
 */
public class BfTest {

    @Test
    public void lists_justWorkLikeLists() throws Exception {
        assertEquals(Arrays.asList(1, 2, 3, 4), l(1, 2, 3, 4));
        assertEquals(Arrays.asList(1, 2, 3, 4), push(l(1, 2, 3), 4));
        assertEquals(Arrays.asList(1, 2, 3, 4), unshift(l(2, 3, 4), 1));
        assertEquals(Arrays.asList(1, 2, 3, 4), assoc(l(1, 1, 3, 4), 1, 2));
        assertEquals(Arrays.asList(1, 2, 3, 4), assoc(l(1, 2, 3, 2), 3, 4));
        assertEquals(Arrays.asList(1, 2, 3, 4), merge(l(1, 2), l(3), l(4)));
        assertEquals(Arrays.asList(1, 2, 3, 4), merge(l(1, 2), l(3, 4)));
    }

    @Test
    public void maps_justWorkLikeMaps() throws Exception {
        assertEquals(ImmutableMap.of("a", 1), m(e("a", 1)));
        assertEquals(ImmutableMap.of("a", 1, "b", 2), m(e("a", 1), e("b", 2)));
        assertEquals(
            ImmutableMap.of("a", 1, "b", 2, "c", 3),
            assoc(m(e("a", 1), e("b", 2)), e("c", 3))
        );
        assertEquals(
            ImmutableMap.of("a", 1, "b", 2, "c", 3),
            assoc(m(e("a", 1), e("b", 2)), "c", 3)
        );
        assertEquals(
            ImmutableMap.of("a", 2, "b", 2),
            assoc(m(e("a", 1), e("b", 2)), "a", 2)
        );
        assertEquals(
            ImmutableMap.of("a", 1, "b", 2, "c", 3),
            merge(m(e("a", 1), e("b", 2)), m(e("c", 3)))
        );
        assertEquals(
            ImmutableMap.of("a", 1, "b", 2, "c", 3),
            assoc(
                m(e("a", 1), e("b", 1)),
                m(e("b", 2), e("c", 3))
            )
        );
        assertEquals(m(e("a", 1)), dissoc(m(e("a", 1), e("b", 2)), "b"));
    }

    @Test
    public void sets_justDoSetStuff() throws Exception {
        assertEquals(ImmutableSet.of(1, 2, 3), s(1, 2, 3)); // (1, 2, 3)
        assertEquals(s(1, 2, 3, 4, 5), assoc(s(1, 2, 3), 4, 5));
        assertEquals(s(1, 2, 3, 4, 5), assoc(s(1, 2, 3), 3, 4, 5));
        assertEquals(s(1, 2, 3, 4, 5), union(s(1, 2, 3), s(4, 5)));
        assertEquals(s(1, 2), intersection(s(1, 2, 3), s(1, 2, 4, 5)));
        assertEquals(s(1, 2), intersection(s(1, 2, 3, 4), s(1, 2, 4, 5), s(1, 2, 3, 5)));
        assertEquals(s(1, 3), xor(s(1,2), s(2, 3)));
        assertEquals(s(1, 3, 4), xor(s(1,2), s(2, 3), s(4)));
        assertEquals(s(1), not(s(1,2,3), s(2,3)));

    }

}