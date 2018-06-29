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

