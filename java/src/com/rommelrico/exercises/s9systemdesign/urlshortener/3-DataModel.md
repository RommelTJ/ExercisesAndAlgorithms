### Step 3 - Data Model

It's worthwhile to be careful about how we name things. This'll help us communicate clearly with our 
interviewer, and it'll show that we care about using descriptive and consistent names! Many 
interviewers look for this.

Let's call our main entity a Link. A Link is a mapping between a shortLink on our site, and a 
longLink, where we redirect people when they visit the shortLink.
```
Link
- shortLink
- longLink
```

The shortLink could be one we've randomly generated, or one a user chose.

Of course, we don't need to store the full ShortLink URL (e.g. bit.ly/mysite), we just need to store 
the "slug" - the part at the end (e.g. "mysite").

So let's rename the shortLink field to "slug."
```
Link
- slug
- longLink
```

Now the name longLink doesn't make as much sense without shortLink. So let's change it to destination.
```
Link
- slug
- destination
```

And let's call this whole model/table ShortLink, to be a bit more specific.
```
ShortLink
- slug
- destination
```

Investing time in carefully naming things from the beginning is always impressive in an interview. A 
big part of code readability is how well things are named!

