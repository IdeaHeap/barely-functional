# Barely Functional
A java library that takes all those really great other functional libraries
and gives their function calls better names.

### So let's be real

This library does little to nothing. It takes other libraries that i like,
and i wrapped what they did into function calls that i think read better.

We can write functional java code! We can be performant when we want and we can stop
writing such ugly code when we want to be immutable. Let's just be immutable!
Let's make it easy!

# Lists

I love immutable lists. Whenever I don't use immutable lists, that's when I get into
trouble, so here it is, I wrapped the calls to guava's immutable list builder to do the
lifting for me. I'm sick of typing ImmutableList.of(), and I want to use ImmutableMap.of(),
so this is just no good.

Here it is, I'm drawing a line in the Java sand. 

        final List<Integer> ints = list(1, 2, 3, 4)); // [1, 2, 3, 4]
        final List<Integer> moreInts = push(ints, 5); // [1, 2, 3, 4, 5]
        final List<Integer> evenMore = unshift(ints, -1, 0); // [ -1, 0, 1, 2, 3, 4, 5]
        final List<Integer> firstNumberIsTwo = assoc(ints, 0, 2); // [ 2, 0, 1, 2, 3, 4, 5]

Let's also actually make it easy to get new lists with changed elements! Why do we make
this hard?


# Maps

I love guava maps! But once again, I am sick of typing ImmutableMap.of()!  And i want arbitrary numbers of entries!
And I want those entries to be succinct! So here's the plan, we're taking the letter e.

    Map<String, Integer> vals = map(e("a",1), e("b", 2)); // { "a" : 1, "b" : 2 }
    Map<String, Integer> moreVals = assoc(vals, e("c", 3)); // { "a" : 1, "b" : 2, "c" : 3 }
    Map<String, Integer> changeVals = assoc(vals, e("a", 3)); // { "a" : 3, "b" : 2, "c" : 3 }
    Map<String, Integer> changeVals2 = assoc(vals, "b", 3); // { "a" : 3, "b" : 3, "c" : 3 }
    Map<String, Integer> changeVals3 = dissoc(vals, "b"); // { "a" : 3, "c" : 3 }


# Sets

This is just like everything else. 

    Set<Integer> ints = set(1, 2, 3); // (1, 2, 3)
    Set<Integer> ints2 = assoc(ints, 4, 5); // (1, 2, 3, 4, 5)
    Set<Integer> union = union(ints, ints2) // (1, 2, 3, 4, 5)
    Set<Integer> inter = intersection(ints, ints2) // (1, 2, 3)
    Set<Integer> xor = xor(ints, ints2) // (4, 5)
    Set<Integer> not1 = not(ints2, ints) // (4, 5)
    Set<Integer> not2 = not(ints, ints2) // ()

