# Mutable vs Immutable Objects

A mutable object can be changed after it's created, and an immutable object can't.

In Java, everything (except for strings) is mutable by default:

```
public class IntegerPair {
    int x;
    int y;

    IntegerPair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

IntegerPair p = new IntegerPair(5, 10);
// p.x = 5, p.y = 10

p.x = 50;
// p.x = 50, p.y = 10
```

There's no way to make existing objects immutable. Even if an object is declared final, its fields 
can still be changed:

```
public class IntegerPair {
    int x;
    int y;

    IntegerPair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

final IntegerPair p = new IntegerPair(5, 10);
// p.x = 5, p.y = 10

p.x = 50;
// p.x = 50, p.y = 10
```

That said, if you're defining your own class, you can make its objects immutable by making all 
fields final and private.

```
public class IntegerPair {
    private final int x;
    private final int y;

    IntegerPair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

IntegerPair p = new IntegerPair(5, 10);

p.x = 50;
// Compilation error: cannot assign a value to a final variable
```

Strings can be mutable or immutable depending on the language.

Strings are immutable in Java.

When you modify a string, you're actually creating a separate copy, leaving the first intact:

```
String first = "first";

System.out.println(first.hashCode());
// prints something

first = first + "!";

System.out.println(first.hashCode());
// prints something different
```

But in some other languages, like C++, strings can be mutable:

```
string testString("mutable?");

testString[7] = '!';
// testString is now "mutable!"
```

If you want mutable strings in Java, you can use a StringBuilder object:

```
StringBuilder mutableString = new StringBuilder("mutable?");

mutableString.setCharAt(7, '!');
// still the same object!
// mutableString is now "mutable!"

// convert to an immutable string
String immutableString = mutableString.toString();
```

Or, you can convert the string to an array of characters, which will be mutable.

Mutable objects are nice because you can make changes in-place, without allocating a new object. 
But be careful — whenever you make an in-place change to an object, all references to that object 
will now reflect the change.

 