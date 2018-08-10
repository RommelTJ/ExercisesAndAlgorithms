### Step 6 - Generating Slugs

Ok, but how do we generate a random slug?

We could just make a random choice for each character:
```
private static final int NUM_CHARS_IN_SLUG = 7;
private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

private Random random = new Random();

public String generateRandomSlug() {
    char[] result = new char[NUM_CHARS_IN_SLUG];

    for (int i = 0; i < NUM_CHARS_IN_SLUG; i++) {
        int randomIndex = random.nextInt(ALPHABET.length() - 1);
        result[i] = ALPHABET.charAt(randomIndex);
    }

    return new String(result);
}
```

But how do we ensure slugs are unique? Two general strategies:
1. "Re-roll" when we hit an already-used slug
2. Adjust our slug generation strategy to only ever give us un-claimed slugs.

If we're serious about our first 2 design goals (short slugs, and accommodating many different 
slugs), option (2) is clearly better than option (1). Why? As we have more and more slugs in our 
database, we'll get more and more collisions. For example, when we're 3/4 of the way through our set 
of possible 7-character slugs, we'd expect to have to make four "rolls" before arriving at a slug 
that isn't taken already. And it'll just keep going up from there.

So let's try to come up with a strategy for option (2). How could we do it?

The answer is base conversion.

**Using base conversion to generate slugs**

We usually use base-10 numbers, which allow 10 possible numerals: 0, 1, 2, 3, 4, 5, 6, 7, 8, and 9.

Binary is base-2 and has 2 possible numerals: 0 and 1.

Our random slug alphabet has 62 possible numerals (A-Z, a-z, and 0-9). So we can think of each of our 
possible "random" slugs as a unique number, expressed in base-62.

So let's keep track of a global currentRandomSlugId. When a request for a new random slug comes in, 
we simply convert that number to base-62 (using our custom numeral set) and return it. Oh, and we 
increment the currentRandomSlugId, in preparation for the next request for a random slug.
```
private long currentRandomSlugId = 0;

public String generateRandomSlug() {
    long newId = currentRandomSlugId++;
    return baseConversion(newId, base62Alphabet);
}
```

Where should we store our currentRandomSlugId? We can keep it in memory on our web server, perhaps 
with a regular writethrough to the database, to make it persistent even if the web server crashes. 
But what if we have multiple front-end web servers?

How do we do the base conversion? This is easiest to show by example.

Take the number 125 in base 10.

It has a 1 in the 100s place, a 2 in the 10s place, and a 5 in the 1s place. To convert 125 to 
base-62, we distribute that 125 across these base-62 "places." The highest "place" that can take 
some is 62^1​​, which is 62. 125/62 is 2, with a remainder of 1. So we put a 2 in the 62's place and a 
1 in the 1's place. So our answer is 21.

What about a higher number—say, 7,912?

Now we have enough to put something in the 3,844's place. 7,912 / 3,844 is 2 with a remainder of 224. 
So we put a 2 in the 3,844's place, and we distribute that remaining 224 across the remaining places 
- the 62's place and the 1's place. 224 / 62 is 3 with a remainder of 38. So we put a 3 in the 62's 
place and a 38 in the 1's place. We have this three-digit number: 2 3 38.

Now, that "38" represents one numeral in our base-62 number. So we need to convert that 38 into a 
specific choice from our set of numerals: a-z, A-Z, and 0-9.

Let's number each of our 62 numerals, like so:
```
0: 0,
1: 1,
2: 2,
3: 3,
...
10: a,
11: b,
12: c,
...
36: A,
37: B,
38: C,
...
61: Z
```

As you can see, our "38th" numeral is "C." So we convert that 38 to a "C." That gives us 23C.

Can we convert from slugs back to numbers? Yep, easy. Take 23C, for example. Translate the numerals 
back to their id numbers, so we get 2 3 38. That 2 is in the 3844's place, so we take 2 * 3844. That 
3 is in the 62's place, so we take 3 * 62. That 38 is in the 1's place, so we take 38 * 1. We add up 
all those results to get our original 7,912.

One potential issue: the currentRandomSlugId could be shorter than 7 digits in base-62. We could pad 
the generate slug with zeros to force it to be exactly 7 characters. Or we could simply accept 
shorter random slugs - we'd just need to make sure our method that converts slugs back to numbers 
doesn't choke when the slug is fewer than 7 characters.

Another issue is that the currentRandomSlugId could give us something that a user has already 
claimed as a user-generated slug. We'll need to check for that, and if it happens we'll just 
increment the currentRandomSlugId and try again (and again, potentially, until we hit a "random" 
slug that hasn't been used yet).

```
private long currentRandomSlugId = 0;

public String generateRandomSlug() {
    String slug;

    while (true) {
        long newId = currentRandomSlugId++;
        slug = baseConversion(newId, base62Alphabet);

        // make sure the slug isn't already used
        if (!DB.checkSlugExists(slug)) {
            break;
        }
    }
    return slug;
}
```

Okay, this'll work! What's next? Let's look back at our design goals!
1. We should be able to store a lot of links.
2. Our shortlinks should be as short as possible.
3. Following a shortlink should be fast.
4. The shortlink follower should be resilient to load spikes.

We're all set on (1) and (2)! Let's start tackling (3) and (4). How do we scale our link follower to 
be fast and resilient to load spikes?

Beware of premature optimization! That always looks bad. Don't just jump around random ideas for 
optimizations. Instead, focus on asking yourself which thing is likely to bottleneck first and 
optimizing around that.
