### Step 2 - Design Goals

Here's what we came up with:
1. We should be able to store a lot of links, since we're not automatically expiring them.
2. Our shortlinks should be as short as possible. The whole point of a link shortener is to make short 
   links! Having shorter links than our competition could be a business advantage.
3. Following a shortlink should be fast.
4. The shortlink follower should be resilient to load spikes. One of our links might be the top story 
   on Reddit, for example.
   
It's worth taking a moment to really think about the order of our goals. Sometimes design goals are at 
odds with each other (to do a better job of one, we need to do a worse job of another). So it's 
helpful to know which goals are more important than others.

It's okay if your list wasn't just like ours. But to get on the same page, let's move forward with 
these design goals.

