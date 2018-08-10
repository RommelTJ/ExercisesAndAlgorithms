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

