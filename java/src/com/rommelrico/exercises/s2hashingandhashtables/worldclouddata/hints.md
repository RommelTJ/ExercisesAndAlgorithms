### Breakdown

We'll have to go through the entire input string, and we're returning a hash map with every 
unique word. In the worst case every word is different, so our runtime and space cost will 
both be at least O(n). 

### Hint 1

This challenge has several parts. Let's break them down.
1. Splitting the words from the input string
2. Populating the hash map with each word
3. Handling words that are both uppercase and lowercase in the input string

How would you start the first part?

### Hint 2

We could use a built-in String.split() method

We'll check if each character is a letter with Character.isLetter().

### Hint 3

Now how can we split each word? Let's assume, for now, that our helper method will return a 
list of words. 

### Hint 4

We'll iterate over all the characters in the input string. How can we identify when we've 
reached the end of a word? 

### Hint 5

Here's a simple example. It doesn't work perfectly yet — you'll need to add code to handle 
the end of the input string, hyphenated words, punctuation, and edge cases.

```
public static List<String> splitWords(String inputString) {

    List<String> words = new ArrayList<>();

    int currentWordStartIndex = 0;
    int currentWordLength = 0;

    for (int i = 0; i < inputString.length(); i++) {
        char c = inputString.charAt(i);

        if (Character.isLetter(c)) {
            if (currentWordLength == 0) {
                currentWordStartIndex = i;
            }
            currentWordLength++;
        } else {
            String currentWord = inputString.substring(currentWordStartIndex,
                currentWordStartIndex + currentWordLength);
            words.add(currentWord);
            currentWordLength = 0;
        }
    }

    return words;
}
```

Now we've solved the first part of the challenge, splitting the words. The next part is 
populating our hash map with unique words. What do we do with each word? 

### Hint 6

If the word is in the hash map, we'll increment its count. Otherwise, we'll add it to the 
hash map with a count of 1.

```
private Map<String, Integer> wordsToCounts = new HashMap<>();

public void addWordToHashMap(String word) {
    if (wordsToCounts.containsKey(word)) {
        wordsToCounts.put(word, wordsToCounts.get(word) + 1);
    } else {
        wordsToCounts.put(word, 1);
    }
}
```

### Hint 7

Alright, last part! How should we handle words that are uppercase and lowercase?

### Hint 8

Consider these sentences:
```
"We came, we saw, we ate cake."
"Friends, Romans, countrymen! Let us eat cake."
"New tourists in New York often wait in long lines for cronuts."
```

Take some time to think of possible approaches. What are some other sentences you might run 
into. What are all your options?

### Hint 9

When are words that should be lowercase not?

### Hint 10

Here are a few options:
1. Only make a word uppercase in our hash map if it is always uppercase in the original string.
2. Make a word uppercase in our hash map if it is ever uppercase in the original string.
3. Make a word uppercase in our hash map if it is ever uppercase in the original string in a 
   position that is not the first word of a sentence.
4. Use an API or other tool that identifies proper nouns.
5. Ignore case entirely and make every word lowercase.

What are the pros and cons for each one?

### Hint 11

Pros and cons include:
1. Only make a word uppercase in our hash map if it is always uppercase in the original 
   string: this will have reasonable accuracy in very long strings where words are more 
   likely to be included multiple times, but words that only ever occur as the first word in 
   a sentence will always be included as uppercase.
2. Make a word uppercase in our hash map if it is ever uppercase in the original string: this 
   will ensure proper nouns are always uppercase, but any words that are ever at the start of 
   sentences will always be uppercase too.
3. Make a word uppercase in our hash map if it is ever uppercase in the original string in a 
   position that is not the first word of a sentence: this addresses the problem with 
   option (2), but proper nouns that are only ever at the start of sentences will be made 
   lowercase.
4. Use an API or other tool that identifies proper nouns: this has a lot of potential to give 
   us a high level of accuracy, but we'll give up control over decisions, we'll be relying on 
   code we didn't write, and our practical runtime may be significantly increased.
5. Ignore case entirely and make every word lowercase: this will give us simplicity and 
   consistency, but we'll lose all accuracy for words that should be uppercase.

Any of these could be considered reasonable. Importantly, none of them are perfect. They all 
have tradeoffs, and it is very difficult to write a highly accurate algorithm. Consider 
"cliff" and "bill" in these sentences:

```
"Cliff finished his cake and paid the bill."
"Bill finished his cake at the edge of the cliff."
```

You can choose whichever of the options you'd like, or another option you thought of. For 
this breakdown, we're going to choose option (1).

Now, how do we update our `addWordToHashMap()` method to avoid duplicate words?

### Hint 12

Think about the different possibilities:
1. The word is uppercase or lowercase.
2. The word is already in the hash map or not.
3. A different case of the word is already in the hash map or not.

### Hint 13

Moving forward, we can either:
1. Check for words that are in the hash map in both cases when we're done populating the 
   hash map. If we add "Vanilla" three times and "vanilla" eight times, we'll combine them 
   into one "vanilla" at the end with a value 11.
2. Avoid ever having a word in our hash map that's both uppercase and lowercase. As we add 
   "Vanilla"s and "vanilla"s, we'd always only ever have one version in our hash map.

We'll choose the second approach since it will save us a walk through our hash map. How 
should we start?

### Hint 14

If the word we're adding is already in the hash map in its current case, let's increment 
its count. What if it's not in the hash map?

### Hint 15

There are three possibilities:
1. A lowercase version is in the hash map (in which case we know our input word is uppercase, 
   because if it is lowercase and already in the hash map it would have passed our first 
   check and we'd have just incremented its count).
2. An uppercase version is in the hash map (so we know our input word is lowercase)
3. The word is not in the hash map in any case

Let's start with the first possibility. What do we want to do?

### Hint 16 

Because we only include a word as uppercase if it is always uppercase, we simply increment 
the lowercase version's count.

```
// current hash map
// {blue=3}

// adding
// "Blue"

// code
int lowerCaseCount = wordsToCounts.get(word.toLowerCase());
wordsToCounts.put(word.toLowerCase(), lowerCaseCount + 1);

// updated hash map
// {blue=4}
```

What about the second possibility? 

### Hint 17

This is a little more complicated. We need to remove the uppercase version from our hash map 
if we encounter a lowercase version. But we still need the uppercase version's count!

```
// current hash map
// {Yellow=6}

// adding
// "yellow"

// code (we will write our "capitalize()" method later)
int capitalizedCount = wordsToCounts.get(capitalize(word));
wordsToCounts.put(word, capitalizedCount + 1);
wordsToCounts.remove(capitalize(word));

// updated hash map
// {yellow=7}
```

Finally, what if the word is not in the hash map at all? 

### Hint 18

Easy — we add it and give it a count of 1.

```
// current hash map
// {purple=2}

// adding
// "indigo"

// code
wordsToCounts.put(word, 1);

// updated hash map
// {purple=2, indigo=1}
```

Now we have all our pieces! We can split words, add them to a hash map, and track the number 
of times each word occurs without having duplicate words of the same case. Can we improve 
our solution?
 
### Hint 19

Let's look at our runtime and space cost. We iterate through every character in the input 
string once and then every word in our list once. That's a runtime of O(n), which is the best 
we can achieve for this challenge (we have to look at the entire input string). The space 
we're using includes a list for each word and a hash map for every unique word. Our worst 
case is that every word is different, so our space cost is also O(n), which is also the best 
we can achieve for this challenge (we have to return a hash map of words).

But we can still make some optimizations!

How can we make our space cost even smaller?

### Hint 20

We're storing all our split words in a separate list. That at least doubles the memory we 
use! How can we eliminate the need for that list?

### Hint 21

Right now, we store each word in our list as we split them. Instead, let's just immediately 
populate each word in our hash map!

