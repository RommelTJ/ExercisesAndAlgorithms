### Breakdown

Let’s start with a simpler problem. What if we wanted to reverse all the characters in the message?

### Hint 1

Well, we could swap the first character with the last character, then the second character with the second 
to last character, and so on, moving towards the middle. Can you implement this in code?

### Hint 2

```
public static void reverseCharacters(char[] message) {

    int leftIndex = 0;
    int rightIndex = message.length - 1;

    // walk towards the middle, from both sides
    while (leftIndex < rightIndex) {

        // swap the left char and right char
        char temp = message[leftIndex];
        message[leftIndex] = message[rightIndex];
        message[rightIndex] = temp;
        leftIndex++;
        rightIndex--;

    }

    return;
}
```

Ok, looks good. Does this help us?

Can we use the same concept but apply it to words instead of characters? 

### Hint 3

Probably. We'll have to figure out a couple things:

1. How do we figure out where words begin and end?
2. Once we know the start and end indices of two words, how do we swap those two words?

We could attack either of those first, but I'm already seeing a potential problem in terms of runtime. 
Can you guess what it is?

### Hint 4

What happens when you swap two words that are not the same length?

```
// the eagle has landed
{ 't', 'h', 'e', ' ', 'e', 'a', 'g', 'l', 'e', ' ',
  'h', 'a', 's', ' ', 'l', 'a', 'n', 'd', 'e', 'd' };
```

Supposing we already knew the start and end indices of 'the' and 'landed', how long would it take to 
swap them?

### Hint 5

O(n) time, where n is the total length of the message!

Why? Notice that in addition to moving 'the' to the back and moving 'landed' to the front, we have to 
"scoot over" everything in between, since 'landed' is longer than 'the'.

So what'll be the total time cost with this approach? Assume we'll be able to learn the start and end 
indices of all of our words in just one pass (O(n) time).

### Hint 6

O(n​^2​​) total time. Why? In the worst case we have almost as many words as we have characters, and we're 
always swapping words of different lengths. For example:

```
// a bb c dd e ff g hh
{ 'a', ' ', 'b', 'b', ' ', 'c', ' ', 'd', 'd', ' ',
  'e', ' ', 'f', 'f', ' ', 'g', ' ', 'h', 'h' };
```

We take O(n) time to swap the first and last words (we have to move all n characters):

```
// input: a bb c dd e ff g hh
{ 'a', ' ', 'b', 'b', ' ', 'c', ' ', 'd', 'd', ' ',
  'e', ' ', 'f', 'f', ' ', 'g', ' ', 'h', 'h' };

// first swap: hh bb c dd e ff g a
{ 'h', 'h', ' ', 'b', 'b', ' ', 'c', ' ', 'd', 'd',
  ' ', 'e', ' ', 'f', 'f', ' ', 'g', ' ', 'a' };
```

Then for the second swap:
```
// input: a bb c dd e ff g hh
{ 'a', ' ', 'b', 'b', ' ', 'c', ' ', 'd', 'd', ' ',
  'e', ' ', 'f', 'f', ' ', 'g', ' ', 'h', 'h' };

// first swap: hh bb c dd e ff g a
{ 'h', 'h', ' ', 'b', 'b', ' ', 'c', ' ', 'd', 'd',
  ' ', 'e', ' ', 'f', 'f', ' ', 'g', ' ', 'a' };

// second swap: hh g c dd e ff bb a
{ 'h', 'h', ' ', 'g', ' ', 'c', ' ', 'd', 'd',
  ' ', 'e', ' ', 'f', 'f', ' ', 'b', 'b', ' ', 'a' };
```

We have to move all n characters except the first and last words, and a couple spaces. So we move 
n−5 characters in total.

For the third swap, we have another 5 characters we don't have to move. So we move n−10 in total. We'll 
end up with a series like this: 
n+(n−5)+(n−10)+(n−15)+...+6+1

This is a subsection of the common triangular series. We're just skipping 4 terms between each term!

So we have the sum of "every fifth number" from that triangular series. That means our sum will be 
about a fifth the sum of the original series! But in big O notation dividing by 5 is a constant, so 
we can throw it out. The original triangular series is O(n^2), and so is our series with every fifth 
element!

Okay, so O(n^2) time. That's pretty bad. It's possible that's the best we can do... but maybe we can 
do better?

### Hint 7

Let's try manipulating a sample input by hand.

And remember what we did for our character-level reversal...

### Hint 8

Look what happens when we do a character-level reversal:

```
// input: the eagle has landed
{ 't', 'h', 'e', ' ', 'e', 'a', 'g', 'l', 'e', ' ',
  'h', 'a', 's', ' ', 'l', 'a', 'n', 'd', 'e', 'd' };

// character-reversed: dednal sah elgae eht
{ 'd', 'e', 'd', 'n', 'a', 'l', ' ', 's', 'a', 'h', ' ',
  'e', 'l', 'g', 'a', 'e', ' ', 'e', 'h', 't' };
```

Notice anything? 

### Hint 9

What if we put it up against the desired output:

```
// input: the eagle has landed
{ 't', 'h', 'e', ' ', 'e', 'a', 'g', 'l', 'e', ' ',
  'h', 'a', 's', ' ', 'l', 'a', 'n', 'd', 'e', 'd' };

// character-reversed: dednal sah elgae eht
{ 'd', 'e', 'd', 'n', 'a', 'l', ' ', 's', 'a', 'h', ' ',
  'e', 'l', 'g', 'a', 'e', ' ', 'e', 'h', 't' };

// word-reversed (desired output): landed has eagle the
{ 'l', 'a', 'n', 'd', 'e', 'd', ' ', 'h', 'a', 's', ' ',
  'e', 'a', 'g', 'l', 'e', ' ', 't', 'h', 'e' };
```

### Hint 10

The character reversal reverses the words! It's a great first step. From there, we just have to 
"unscramble" each word.

More precisely, we just have to re-reverse each word!
