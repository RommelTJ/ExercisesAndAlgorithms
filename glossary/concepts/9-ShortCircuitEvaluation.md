# Short Circuit Evaluation

Short-circuit evaluation is a strategy most programming languages (including Java) use to avoid unnecessary work. For 
example, say we had a conditional like this:

```
if (itIsFriday && itIsRaining) {
    System.out.println("board games at my place!");
}
```

Let's say itIsFriday is false. Because Java short-circuits evaluation, it wouldn't bother checking the value of 
itIsRaining — it knows that either way the condition is false and we won't print the invitation to board game night.

We can use this to our advantage. For example, say we have a check like this:

```
if (friends.get("Becky").isFreeThisFriday()) {
    inviteToBoardGameNight(friends.get("Becky"));
}
```

What happens if "Becky" isn't in our friends hash map? Since friends.get("Becky") is null, when we try to call 
isFreeThisFriday() we'll get a NullPointerException.

Instead, we could first confirm that Becky and I are still on good terms:

```
if (friends.containsKey("Becky") && friends.get("Becky").isFreeThisFriday()) {
    inviteToBoardGameNight(friends.get("Becky"));
}
```

This way, if "Becky" isn't in friends, Java will skip the second check about Becky being free and avoid throwing the 
NullPointerException.

This is all hypothetical, of course. It's not like things with Becky are weird or anything. We're totally cool. She's 
still in my friends hash map for sure and I hope I'm still in hers and Becky if you're reading this I just want you to 
know you're still in my friends hash map. 
