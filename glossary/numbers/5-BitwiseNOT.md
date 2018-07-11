# Bitwise NOT

The NOT bitwise operation takes one set of bits, and for each bit returns 0 if the bit is 1, 
and 1 if the bit is 0.

```
~ 0  →  -1
~ 1  →  -2
```

When performing NOT on an integer, each bit of the integer is inverted.

```
~ 5  // gives -6

// At the bit level:
//   ~ 0000 0101  (5)
//   = 1111 1010  (-6)
```

If you're unsure why the resulting number is negative in this example, it's because numbers 
are represented using two's complement.

 