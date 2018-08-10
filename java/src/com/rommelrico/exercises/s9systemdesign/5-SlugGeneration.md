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

