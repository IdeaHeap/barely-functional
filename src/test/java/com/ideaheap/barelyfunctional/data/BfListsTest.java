package com.ideaheap.barelyfunctional.data;

import org.junit.Test;

import java.util.Arrays;

import static com.ideaheap.barelyfunctional.data.BfLists.*;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by nwertzberger on 4/27/16.
 */
public class BfListsTest {

    @Test
    public void lists_justWorkLikeLists() throws Exception {
        assertEquals(Arrays.asList(1, 2, 3, 4), list(1, 2, 3, 4));
        assertEquals(Arrays.asList(1, 2, 3, 4), push(list(1, 2, 3), 4));
        assertEquals(Arrays.asList(1, 2, 3, 4), unshift(list(2, 3, 4), 1));
        assertEquals(Arrays.asList(1, 2, 3, 4), assoc(list(1, 1, 3, 4), 1, 2));
        assertEquals(Arrays.asList(1, 2, 3, 4), assoc(list(1, 2, 3, 2), 3, 4));
        assertEquals(Arrays.asList(1, 2, 3, 4), remove(list(1, 2, 3, 3, 4), 2));
        assertEquals(Arrays.asList(1, 2, 3, 4), insert(list(1, 2, 4), 2, 3));
        assertEquals(Arrays.asList(1, 2, 3, 4), merge(list(1, 2), list(3), list(4)));
        assertEquals(Arrays.asList(1, 2, 3, 4), merge(list(1, 2), list(3, 4)));
    }



}