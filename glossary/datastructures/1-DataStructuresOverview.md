# Data Structures Overview

To really understand how data structures work, we're going to derive each of them from scratch. 
Starting with bits.

Don't worry — we'll skip the convoluted academic jargon and proofs.

We'll cover:
* Random Access Memory
* Binary Numbers
* Fixed-Width Integers
* Arrays
* Strings
* Pointers
* Dynamic Arrays
* Linked Lists
* Hash Tables

## Random Access Memory (RAM)

When a computer is running code, it needs to keep track of variables (numbers, strings, arrays, etc.).
   
Variables are stored in random access memory (RAM). We sometimes call RAM "working memory" or just 
"memory." RAM is not where mp3s and apps get stored. In addition to "memory," your computer has 
storage (sometimes called "persistent storage" or "disc"). While memory is where we keep the 
variables our methods allocate as they crunch data for us, storage is where we keep files like 
mp3s, videos, Word documents, and even executable programs or apps.

Memory (or RAM) is faster but has less space, while storage (or "disc") is slower but has more 
space. A modern laptop might have ~500GB of storage but only ~16GB of RAM.
   
Think of RAM like a really tall bookcase with a lot of shelves. Like, billions of shelves. The 
shelves are numbered. We call a shelf's number its address. Each shelf holds 8 bits. A bit is a 
tiny electrical switch that can be turned "on" or "off." But instead of calling it "on" or "off" 
we call it 1 or 0.

8 bits is called a byte. So each shelf has one byte (8 bits) of storage.

Of course, we also have a processor that does all the real work inside our computer. It's connected 
to a memory controller. The memory controller does the actual reading and writing to and from RAM. 
It has a direct connection to each shelf of RAM.

That direct connection is important. It means we can access address 0 and then immediately access 
address 918,873 without having to "climb down" our massive bookshelf of RAM.

That's why we call it Random Access Memory (RAM) — we can Access the bits at any Random address in 
Memory right away.

Even though the memory controller can jump between far-apart memory addresses quickly, programs 
tend to access memory that's nearby. So computers are tuned to get an extra speed boost when 
reading memory addresses that're close to each other. Here's how it works:

The processor has a cache where it stores a copy of stuff it's recently read from RAM.

This cache is much faster to read from than RAM, so the processor saves time whenever it can read 
something from cache instead of going out to RAM.

When the processor asks for the contents of a given memory address, the memory controller also 
sends the contents of a handful of nearby memory addresses. And the processor puts all of it in 
the cache.

So if the processor asks for the contents of address 951, then 952, then 953, then 954... it'll go 
out to RAM once for that first read, and the subsequent reads will come straight from the 
super-fast cache.

But if the processor asks to read address 951, then address 362, then address 419... then the 
cache won't help, and it'll have to go all the way out to RAM for each read.

So reading from sequential memory addresses is faster than jumping around.

## Binary numbers

Let's put those bits to use. Let's store some stuff. Starting with numbers.

The number system we usually use (the one you probably learned in elementary school) is called 
base 10, because each digit has ten possible values (1, 2, 3, 4, 5, 6, 7, 8, 9, and 0).

But computers don't have digits with ten possible values. They have bits with two possible values. 
So they use base 2 numbers.

Base 10 is also called decimal. Base 2 is also called binary.

To understand binary, let's take a closer look at how decimal numbers work. Take the number "101" 
in decimal:
In base 10, the digits 101 represent 1 hundred, 0 tens, and 1 one.

Notice we have two "1"s here, but they don't mean the same thing. The leftmost "1" means 100, and 
the rightmost "1" means 1. That's because the leftmost "1" is in the hundreds place, while the 
rightmost "1" is in the ones place. And the "0" between them is in the tens place.

So this "101" in base 10 is telling us we have "1 hundred, 0 tens, and 1 one."

Notice how the places in base 10 (ones place, tens place, hundreds place, etc.) are sequential 
powers of 10:
* 10^0 = 1
* 10^1 = 10
* 10^2 = 100
* 10^3 = 1000
* etc.

The places in binary (base 2) are sequential powers of 2:
* 2^0 = 1 
* 2^1 = 2 
* 2^2 = 4 
* 2^3 = 8
* etc.

So let's take that same "101" but this time let's read it as a binary number:
Reading this from right to left: we have a 1 in the ones place, a 0 in the twos place, and a 1 in 
the fours place. So our total is 4 + 0 + 1 which is 5.

Here's how we'd count up to 12 in binary:

| Decimal   | Binary        |
| --------- |:------------- |
| 0         | 0000          |
| 1         | 0001          |
| 2         | 0010          |
| 3         | 0011          |
| 4         | 0100          |
| 5         | 0101          |
| 6         | 0110          |
| 7         | 0111          |
| 8         | 1000          |
| 9         | 1001          |
| 10        | 1010          |
| 11        | 1011          |
| 12        | 1100          |

So far we've been talking about unsigned integers ("unsigned" means non-negative, and 
"integer" means a whole number, not a fraction or decimal). Storing other numbers isn't hard 
though. Here's how some other numbers could be stored:

**Fractions**: Store two numbers: the numerator and the denominator.

**Decimals**: Also two numbers: 1) the number with the decimal point taken out, and 
2) the position where the decimal point goes (how many digits over from the leftmost digit).

**Negative Numbers**: Reserve the leftmost bit for expressing the sign of the number. 0 for 
positive and 1 for negative.

In reality we usually do something slightly fancier for each of these. But these approaches work, 
and they show how we can express some complex stuff with just 1s and 0s.  


## Fixed-width integers

How many different numbers can we express with 1 byte (8 bits)?

2^8 = 256 different numbers. How did we know to take 2^8​​?

The 256 possibilities we get with 1 byte are pretty limiting. So we usually use 4 or 8 bytes (32 
or 64 bits) for storing integers.

* 32-bit integers have 2^32​ possible values — more than 4 billion
* 64-bit integers have 2^64 possible values — more than 10 billion billion (10^19).

Most integers are fixed-width or fixed-length, which means the number of bits they take up doesn't 
change.

It's usually safe to assume an integer is fixed-width unless you're told otherwise. Variable-size 
numbers exist, but they're only used in special cases.

If we have a 64-bit fixed-length integer, it doesn't matter if that integer is 0 or 193,457 — it 
still takes up the same amount of space in RAM: 64 bits.

In big O notation, we say fixed-width integers take up constant space or O(1) space.

And because they have a constant number of bits, most simple operations on fixed-width integers 
(addition, subtraction, multiplication, division) take constant time (O(1) time).

So fixed-width integers are very space efficient and time efficient.

But that efficiency comes at a cost—their values are limited. Specifically, they're limited to 
2^n​​ possibilities, where n is the number of bits.

So there's a tradeoff. As we'll see, that's a trend in data structures—to get a nice property, 
we'll often have to lose something.

## Arrays

Ok, so we know how to store individual numbers. Let's talk about storing several numbers.

That's right, things are starting to heat up.

Suppose we wanted to keep a count of how many bottles of kombucha we drink every day.

Let's store each day's kombucha count in an 8-bit, fixed-width, unsigned integer. That should be 
plenty—we're not likely to get through more than 256 (2^8​​) bottles in a single day, right?

And let's store the kombucha counts right next to each other in RAM, starting at memory address 0. 
Bam. That's an array. RAM is basically an array already.

Just like with RAM, the elements of an array are numbered. We call that number the index of the 
array element (plural: indices). In this example, each array element's index is the same as its 
address in RAM.

But that's not usually true. Suppose another program like Spotify had already stored some 
information at memory address 2. 

We'd have to start our array below it, for example at memory address 3. So index 0 in our array 
would be at memory address 3, and index 1 would be at memory address 4, etc.

Suppose we wanted to get the kombucha count at index 4 in our array. How do we figure out what 
address in memory to go to? Simple math:

Take the array's starting address (3), add the index we're looking for (4), and that's the address 
of the item we're looking for. 3 + 4 = 7. In general, for getting the nth item in our array:

*address of nth item in array = address of array start + n*

This works out nicely because the size of the addressed memory slots and the size of each 
kombucha count are both 1 byte. So a slot in our array corresponds to a slot in RAM.

But that's not always the case. In fact, it's usually not the case. We usually use 64-bit integers.

So how do we build an array of 64-bit (8 byte) integers on top of our 8-bit (1 byte) memory slots?

We simply give each array index 8 address slots instead of 1.

So we can still use simple math to grab the start of the nth item in our array—just gotta throw in 
some multiplication:

*address of nth item in array = address of array start + (n * size of each item in bytes)*

Don't worry — adding this multiplication doesn't really slow us down. Remember: addition, 
subtraction, multiplication, and division of fixed-width integers takes O(1) time. So all the math 
we're using here to get the address of the nth item in the array takes O(1) time.

And remember how we said the memory controller has a direct connection to each slot in RAM? That 
means we can read the stuff at any given memory address in O(1) time.

Together, this means looking up the contents of a given array index is O(1) time. This fast 
lookup capability is the most important property of arrays.

But the formula we used to get the address of the nth item in our array only works if:
1. Each item in the array is the same size (takes up the same number of bytes).
2. The array is uninterrupted (contiguous) in memory. There can't be any gaps in the array... like 
to "skip over" a memory slot Spotify was already using.

These things make our formula for finding the nth item work because they make our array 
predictable. We can predict exactly where in memory the nth element of our array will be.

But they also constrain what kinds of things we can put in an array. Every item has to be the same 
size. And if our array is going to store a lot of stuff, we'll need a bunch of uninterrupted free 
space in RAM. Which gets hard when most of our RAM is already occupied by other programs (like 
Spotify).

That's the tradeoff. Arrays have fast lookups (O(1) time), but each item in the array needs to be 
the same size, and you need a big block of uninterrupted free memory to store the array.

## Strings

Okay, let's store some words.

A series of characters (letters, punctuation, etc.) is called a string.

We already know one way to store a series of things — arrays. But how can an array store 
characters instead of numbers?

Easy. Let's define a mapping between numbers and characters. Let's say "A" is 1 (or 0000 0001 in 
binary), "B" is 2 (or 0000 0010 in binary), etc. Bam. Now we have characters.

This mapping of numbers to characters is called a character encoding. One common character 
encoding is "ASCII". 

So since we can express characters as 8-bit integers, we can express strings as arrays of 8-bit 
numbers characters.

##  Pointers
   
Remember how we said every item in an array had to be the same size? Let's dig into that a little 
more.

Suppose we wanted to store a bunch of ideas for baby names. Because we've got some really cute ones.

Each name is a string. Which is really an array. And now we want to store those arrays in an array.

Now, what if our baby names have different lengths? That'd violate our rule that all the items in 
an array need to be the same size!

We could put our baby names in arbitrarily large arrays (say, 13 characters each), and just use a 
special character to mark the end of the string within each array...

But look at all that wasted space after "Bill". And what if we wanted to store a string that was 
more than 13 characters? We'd be out of luck.

There's a better way. Instead of storing the strings right inside our array, let's just put the 
strings wherever we can fit them in memory. Then we'll have each element in our array hold the 
address in memory of its corresponding string. Each address is an integer, so really our outer 
array is just an array of integers. We can call each of these integers a pointer, since it points 
to another spot in memory.

Pretty clever, right? This fixes both the disadvantages of arrays:
1. The items don't have to be the same length — each string can be as long or as short as we want.
2. We don't need enough uninterrupted free memory to store all our strings next to each other — we 
can place each of them separately, wherever there's space in RAM.

We fixed it! No more tradeoffs. Right?
   
Nope. Now we have a new tradeoff:

Remember how the memory controller sends the contents of nearby memory addresses to the processor 
with each read? And the processor caches them? So reading sequential addresses in RAM is faster 
because we can get most of those reads right from the cache?

Our original array was very cache-friendly, because everything was sequential. So reading from the 
0th index, then the 1st index, then the 2nd, etc. got an extra speedup from the processor cache.

But the pointers in this array make it not cache-friendly, because the baby names are scattered 
randomly around RAM. So reading from the 0th index, then the 1st index, etc. doesn't get that 
extra speedup from the cache.

That's the tradeoff. This pointer-based array requires less uninterrupted memory and can 
accommodate elements that aren't all the same size, but it's slower because it's not 
cache-friendly. 

