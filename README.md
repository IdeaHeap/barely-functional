# Barely Functional
A java library that takes all those really great other functional libraries
and gives their function calls better names. It also documents examples of how
to use these data types (in this readme).

### So let's be real

This library does little to nothing. It takes other libraries that i like,
and i wrapped what they did into function calls that i think read better.

We can write functional java code! We can be performant when we want and we can stop
writing such ugly code when we want to be immutable. Let's just be immutable!
Let's make it easy!

# Maven coordinates

This is availabile through maven central. Check it out!

    <dependency>
        <groupId>com.ideaheap.barelyfunctional</groupId>
        <artifactId>barely-functional</artifactId>
        <version>0.0.2</version>
    </dependency>

# Data Types

## Lists

I love immutable lists. Whenever I don't use immutable lists, that's when I get into
trouble, so here it is, I wrapped the calls to guava's immutable list builder to do the
lifting for me. I'm sick of typing ImmutableList.of(), and I want to use ImmutableMap.of(),
so this is just no good.

Here it is, I'm drawing a line in the Java sand. 

        List<Integer> ints = list(1, 2, 3, 4); // [1, 2, 3, 4]
        List<Integer> moreInts = push(ints, 5); // [1, 2, 3, 4, 5]
        List<Integer> evenMore = unshift(ints, -1, 0); // [ -1, 0, 1, 2, 3, 4, 5]
        List<Integer> firstNumberIsTwo = assoc(ints, 0, 2); // [ 2, 0, 1, 2, 3, 4, 5]
        List<Integer> oneRemoved = remove(ints, 2); // [1, 2, 4]
        List<Integer> oneInserted = insert(ints, 1, 6); //  [1, 6, 2, 3, 4]

Let's also actually make it easy to get new lists with changed elements! Why do we make
this hard?


## Maps

I love guava maps! But once again, I am sick of typing ImmutableMap.of()!  And i want arbitrary numbers of entries!
And I want those entries to be succinct! So here's the plan, we're taking the letter e.

    Map<String, Integer> vals = map(e("a",1), e("b", 2)); // { "a" : 1, "b" : 2 }
    Map<String, Integer> moreVals = assoc(vals, e("c", 3)); // { "a" : 1, "b" : 2, "c" : 3 }
    Map<String, Integer> changeVals = assoc(vals, e("a", 3)); // { "a" : 3, "b" : 2, "c" : 3 }
    Map<String, Integer> changeVals2 = assoc(vals, "b", 3); // { "a" : 3, "b" : 3, "c" : 3 }
    Map<String, Integer> changeVals3 = dissoc(vals, "b"); // { "a" : 3, "c" : 3 }


## Sets

This is just like everything else. 

    Set<Integer> ints = set(1, 2, 3); // (1, 2, 3)
    Set<Integer> ints2 = assoc(ints, 4, 5); // (1, 2, 3, 4, 5)
    Set<Integer> ints3 = dissoc(ints, 2, 3); // (1)
    Set<Integer> union = union(ints, ints2); // (1, 2, 3, 4, 5)
    Set<Integer> inter = intersection(ints, ints2); // (1, 2, 3)
    Set<Integer> xor = xor(ints, ints2); // (4, 5)
    Set<Integer> not1 = not(ints2, ints); // (4, 5)
    Set<Integer> not2 = not(ints, ints2); // ()


## Tuples

There's exactly one tuple library out there, it's called "javatuples", and it has every conceivable
way to handle tuples in a typesafe manner up to 8 parameters. If you need more than 8 parameters,
you really should have written a class. Most people say that at about two, but sometimes that feels
like overkill to me. Tuples handle that case where you wish you could return more than one thing!
And just like everything else, this library made sure things are fully immutable. I added the
"tuple" function to frontend the creation of every tuple type.

    Tuple tup = tuple("a");
    Unit<String> tup1 = tuple("b");
    Pair<String, Integer> tup2 = tuple("three", 4);
    Triple<String, Integer, Long> tup3 = tuple("three", 4).add(5L);

# Memoization

The more specific application of cacheing, memoization is a technique for returning the value to
a function call if you already have it. This is especially useful if you are trying to improve
performance for an expensive call. Because memoization is usually to frontend expensive function
calls, the default implementation uses weak value references to do this. This is due to some
cleanup issues I was seeing with soft references.

    Function<String, Integer> myFunc = memoize((string) -> expensiveCalculation(string));
    // myFunc will only be called if there is not already an integer in memory.

If you want more control over how things are memoized, you can instantiate your own
memoizer. Everything in this tool uses Guava caches to back the memoization process, and each
call to memoize generates its own cache using the cache builder it was given.

    Memoizer memoizer = new Memoizer(CacheBuilder.newBuilder());
    // This memoizer holds onto everything
    memoizer.memoize((h) -> h + h);
