# Bitwise AND

The **AND** operation takes two bits and returns 1 if both bits are 1. Otherwise, it returns 0.

```
1 & 1  →  1
1 & 0  →  0
0 & 1  →  0
0 & 0  →  0
```

Think of it like a hose with two knobs. Both knobs must be set to on for water to come out.

When performing AND on two integers, the AND operation is calculated on each pair of bits (the 
two bits at the same index in each number).

```
5 & 6  // gives 4

// at the bit level:
//     0101  (5)
//   & 0110  (6)
//   = 0100  (4)
```

