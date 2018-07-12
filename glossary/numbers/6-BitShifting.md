# Bit Shifting

A bit shift moves each digit in a number's binary representation left or right. The last bit 
in the direction of the shift is lost, and a 0 bit is inserted on the other end.

```
0010 << 1  →  0100
1011 >> 1  →  0101
```

Bit shifts take number of times to shift as the right argument:

```
1010110 << 2  →  1011000
1011010 >> 3  →  0001011
```

A single left shift multiplies a binary number by 2:

```
0010 << 1  →  0100

0010 is 2
0100 is 4
```

Two left shifts multiplies by 4. Three left shifts multiplies by 8.

And similarly, shifting right divides by 2, throwing out any remainders.

