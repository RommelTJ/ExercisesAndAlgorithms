### Step 7 - Database Choice

The database read to get the destination for the given slug is certainly going to be our first 
bottleneck. In general, database operations usually bottleneck before business logic.

To figure out how to get these reads nice and fast, we should get specific about how we're storing our 
shortlinks. To start, what kind of database should we use?

Database choice is a very broad issue. And it's a contentious one. There are lots of different 
opinions about how to approach this. Here's how we'll do it:

Broadly (this is definitely a simplification), there are two main types of databases these days:
1. Relational databases (RDBMs) like MySQL and Postgres.
2. "NoSQL"-style databases like BigTable and Cassandra.

In general (again, this is a simplification), relational databases are great for systems where you 
expect to make lots of complex queries involving joins and such - in other words, they're good if 
you're planning to look at the relationships between things a lot. NoSQL databases don't handle these 
things quite as well, but in exchange they're faster for writes and simple key-value reads.

Looking at our app, it seems like relational queries aren't likely to be a big part of our app's 
functionality, even if we added a few of the obvious next features we might want. So let's go with 
NoSQL for this.

Which NoSQL database do we use? Lots of options, each with their own pros and cons. Let's keep our 
discussion and pseudocode generic for now.

We might consider adding an abstraction layer between our application and the database, so that we can 
change over to a new one if our needs change or if some new hotness comes out.

Okay, so we have our data in a NoSQL-type database. How do we un-bottleneck database reads?

The first step is to make sure we're indexing the right way. In a NoSQL context, that means 
carefully designing our keys. In this case, the obvious choice is right: making the key for each row 
in the ShortLink table be the slug.

If we used a SQL-type database like MySQL or Postgres, we usually default to having our key field be 
a standard auto-incrementing integer called "id" or "index." But in this case, because we know that 
slugs will be unique, there's no need for an integer id—the slug is enough of a unique identifier.

BUT here's where it gets clever: what if we represented the slug as an auto-incrementing integer 
field? We'd just have to use our base conversion method to convert them to slugs! This would also 
give us tracking of our global currentRandomSlugId for free — MySQL would keep track of the highest 
current id in the table when it auto increments. Careful though: user-generated slugs throw a pretty 
huge monkey wrench into things with this strategy! How can you maintain uniqueness across 
user-generated and randomly-generated slugs without breaking the auto-incrementing ids for 
randomly-generated slugs?

Anyway, how else can we speed up database reads?

We could put as much of the data in memory as possible, to avoid disc seeks.

This becomes especially important when we start getting a heavy load of requests to a single link, 
like if one of our links is on the front page of Reddit. If we have the redirect URL right there in 
memory, we can process those redirects quickly.

Depending on the database we use, it might already have an in-memory cache system. To get more links 
in memory, we may be able to configure our database to use more space for its cache.

If reads are still slow, we could research adding a caching layer, like memcached. Importantly, this 
might not save us time on reads, if the cache on the database is already pretty robust. It adds 
complexity - we now have two sources of truth, and we need to be careful to keep them in sync. For 
example, if we let users edit their links, we need to push those edits to both the database and the 
cache. It could also slow down reads if we have lots of cache misses.

If we did add a caching layer, there are a few things we could talk about:
1. The eviction strategy. If the cache is full, what do we remove to make space? The most common 
   answer is an LRU ("least recently used") strategy.
2. Sharding strategy. Sharding our cache lets us store more stuff in memory, because we can use more 
   machines. But how do we decide which things go on which shard? The common answer is a "hash and 
   mod strategy" - hash the key, mod the result by the number of shards, and you get a shard number 
   to send your request to. But then how do you add or remove a shard without causing an unmanageable 
   spike in cache misses?

Of course, we could shard our underlying database instead of, or in addition to caching. If the 
database has a built-in in-memory cache, sharding the data would allow us to keep more of our data in 
working memory without an additional caching layer! Database sharding has some of the same challenges 
as cache sharding. Adding and removing shards can be painful, as can migrating the schema without site 
downtime. That said, some NoSQL databases have great sharding systems built right in, like Cassandra.

This should get our database reads nice and fast.

The next bottleneck might be processing the actual web requests. To remedy this, we should set up 
multiple web server workers. We can put them all behind a load balancer that distributes incoming 
requests across the workers. Having multiple web servers adds some complexity to our database (and 
caching layers) that we'll need to consider. They'll need to handle more simultaneous connections, 
for example. Most databases are pretty good at this by default.

Okay, now our redirects should go pretty quick, and should be resilient to load spikes. We have a 
solid system that fits all of our design goals!
1. We can store a lot of links.
2. Our shortlinks are as short as possible.
3. Following a shortlink is fast.
4. The shortlink follower is resilient to load spikes.

