### Breakdown

Notice that a boatload of URLs start with "www."

### Hint 1

We could make visited a nested hash map where the outer key is the subdomain and the inner key is the rest of the 
URL, so for example visited["www."]["google.com"] = true and visited["www."]["rommelrico.com"] = true. Now instead of 
storing the "www." for each of these URLs, we've just stored it once in memory. If we have 1,0001,0001,000 URLs and 
half of them start with "www." then we've replaced 500∗4 characters with just 4 characters in memory.

But we can do even better.

### Hint 2

What if we used this same approach of separating out shared prefixes recursively? How long should we make the prefixes?

### Hint 3

Most file systems store the time a file was last edited as metadata on each file. The more recently 
What if we made the prefixes just one character?

