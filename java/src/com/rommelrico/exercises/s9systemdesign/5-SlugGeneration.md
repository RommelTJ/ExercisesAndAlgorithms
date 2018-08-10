### Step 5 - Slug Generation

A note about methodology: Our default process for answering questions like this is often "make a 
reasonable guess, brainstorm potential issues, and revise." That's fine, but sometimes it feels more 
organized and impressive to do something more like "brainstorm design goals, then design around those 
goals." So we'll do that.

Let's look back up at the design goals we came up with earlier. The first two are immediately relevant 
to this problem:
1. We should be able to store a lot of links.
2. Our shortlinks should be as short as possible.

Looking at a few examples, we can quickly notice that the more characters we allow in our shortlinks, 
the more different ShortLinks we can have without making our ShortLinks longer. Specifically, if we 
allow c different characters, for n-character-long slugs we have c^n distinct possibilities.

So if we're trying to accommodate as many slugs as possible, we should allow as many characters as we 
can! So let's do this:
1. Figure out the max set of characters we can allow in our random shortlinks.
2. Figure out how many distinct shortlinks we want to accommodate.
3. Figure out how long our shortlinks must be to accommodate that many distinct possibilities.

Sketching a process like this before jumping in is hugely impressive. It shows organized, methodical 
thinking. Whenever you're not sure how to proceed, take a step back and try to write out a process 
for getting to the bottom of things. It's fine if you end up straying from your plan - it'll still 
help you organize your thinking. 

**What characters can we allow in our randomly-generated slugs?**

What are the constraints on c? Let's think about it:
1. We should only use characters that are actually allowed in URLs.
2. We should probably only pick characters that are relatively easy to type on a keyboard. Remember 
   the use case we talked about where people are typing in a ShortLink that they're reading off a 
   piece of paper?

So, what characters are allowed in URLs? It's okay to not know the answer off the top of your head. 
But you should be able to tell your interviewer that you know how to figure it out! Googling or 
searching on StackOverflow is a fine answer. It's even cooler to say, "I'm sure this is defined in an 
RFC somewhere."

It turns out the answer is "only alphanumerics, the special characters "$-_.+!*'(),", and reserved 
characters used for their reserved purposes may be used unencoded within a URL" (RFC 1738). 
"Reserved characters" with "reserved purposes" are characters like '?', which marks the beginning of 
a query string, and '#', which marks the beginning of a fragment/anchor. We definitely shouldn't use 
any of those. If we allowed '?' in the beginning of our slug, the characters after it would be 
interpreted as part of the query string and not part of the slug!

So just alphanumerics and the "special characters" $-_.+!*'(),. Are accented alphabetical characters 
allowed? No, according to RFC 3986.

What about uppercase and lowercase? Domains aren't case-sensitive (so google.com and Google.com will 
always go to the same place), but the path portion of a URL is case-sensitive. If I query 
rommelrico.com/foo and rommelrico.com/Foo, I'm requesting different documents (although, as a site 
owner, I may choose to return the same document in response to both requests). So yes, lowercase and 
capital versions of the same letter can be treated as different characters in our slugs.

Okay, so it seems like the set of allowed characters is A-Z, a-z, 0-9, and "$-_.+!*'(),". The 
apostrophe character seems a little iffy, since sometimes URLs are surrounded by single quotes in 
HTML documents. So let's pull that one.

In fact, in keeping with point (2) above about ease of typing, let's pull all the "special 
characters" from our list. It seems like a small loss on character count (8 characters) in exchange 
for a big win on readability and typeability. If we find ourselves wanting those extra characters, 
we can add add them back in.

Ah, but what if a user is specifying her own slug? She might want to use underscores, or dashes, or 
parentheses... so let's say for user-specified slugs, we allow "$-_.+!*()," (still no apostrophe).

While we're on the topic of making URLs easy to type, we might want to consider constraining our 
character set to clear up common ambiguities. For example, not allowing both uppercase letter O and 
number 0. Or lowercase letter l and number 1. Font choice can help reduce these ambiguities, but we 
don't have any control over the fonts people use to display our shortlinks. This is a worthwhile 
consideration, but at the moment it's adding complexity to a question we're still trying to figure 
out. So let's just mention it and say, "This is something we want to keep an eye on for later, but 
let's put it aside for now." Your interviewer understands that you can't accommodate everything in 
your initial design, but she'll appreciate you showing an ability to anticipate what problems may 
come up in the user experience.

Okay, so with a-z, A-Z, and 0-9, we have 26 + 26 + 10 = 62 possible characters in our 
randomly-generated slugs. And for user-generated slugs, we have another 10 characters 
("$-_.+!*(),"), for 72 total.

**How many distinct slugs do we need?**

About how many slugs do we need to be able to accommodate? This is a good question to ask your 
interviewer. She may want you to make a reasonable choice yourself. There's no one right answer; the 
important thing is to show some organized thinking.

Here's one way to come up with a ballpark estimate: about how many new slugs might we create on a 
busy day? Maybe 100,000 per minute? Hard to imagine more than that. That's 100,000∗60∗24 ≈ 145 
million new links a day. 52.5 billion a year. What's a number of years that feels like "almost 
forever"? I'd say 100. So that's 5.2 trillion slugs. That seems sufficiently large. It's pretty 
dependent on the accuracy of our estimate of 100,000 per minute. But it seems to be a pretty 
reasonable ceiling, and a purposefully high one. If we can accommodate that many slugs, we expect 
we'll be able to keep handing out random slugs effectively indefinitely.

**How short can we make our slugs while still getting enough distinct possibilities?**

Let's return to the formula we came up with before: with a c-character-long alphabet and slugs of 
length n, we get c^n possible slugs. We want ~5 trillion possible slugs. And we decided on a 
62-character alphabet. So 62^n ​​≈ 5 trillion. We just have to solve for n.

We might know that we need to take a logarithm to solve for n. But even if we know that, this is a 
tricky thing to eyeball. If we're in front of a computer or phone, we can just plug it in to 
wolfram alpha. Turns out the answer is ≈ 7.09. So 7 characters gets us most of the way to our 
target number of distinct possibilities.

It's worth checking how many characters we could save by allowing "$-_.+!*()," as well. So 72^n = 
5.2 trillion. We get n ≈ 6.8. Including the special characters would save us something like .3 
characters on our slug length.

Is it worth it? Of course, there's no such thing as a fraction of a character. If we really had to 
accommodate at least 5.2 trillion random slugs, we'd have to round up, which would mean 7.09 would 
round up to 8-character slugs for our 62-character alphabet (not including special characters) and 
6.8 would round up to 7-character slugs for our 72-character alphabet (including special characters).

But we don't really have to accommodate 5.2 trillion or more slugs. 5.2 trillion was just a ballpark 
estimate - and it was intended to be a high ceiling on how many slugs we expect to get. So let's 
stick with our first instinct to remove those special characters for readability purposes, and let's 
choose 7 characters for our slugs.
