### Breakdown

No idea where to start? Try writing something that just walks through a file system and prints all the 
file names. If you're not sure how to do that, look it up! Or just make it up. Remember, even if you 
can't implement working code, your interviewer will still want to see you think through the problem.

One brute force solution is to loop over all files in the file system, and for each file look at every 
other file to see if it's a duplicate. This means n^2​​ file comparisons, where n is the number of files. 
That seems like a lot.

Let's try to save some time. Can we do this in one walk through our file system?

### Hint 1

Instead of holding onto one file and looking for files that are the same, we can just keep track of 
all the files we've seen so far. What data structure could help us with that?

### Hint 2

We'll use a hash map. When we see a new file, we first check to see if it's in our hash map. If it's not, 
we add it. If it is, we have a duplicate!

Once we have two duplicate files, how do we know which one is the original? It's hard to be sure, but 
try to come up with a reasonable heuristic that will probably work most of the time.

### Hint 3

Most file systems store the time a file was last edited as metadata on each file. The more recently 
edited file will probably be the duplicate!

One exception here: lots of processes like to regularly save their state to a file on disc, so that 
if your computer suddenly crashes the processes can pick up more or less where they left off (this is 
how Word is able to say "looks like you had unsaved changes last time, want to restore them?"). If 
your friend duplicated some of those files, the most-recently-edited one may not be the duplicate. But 
at the risk of breaking our system (we'll make a backup first, obviously.) we'll run with this 
"most-recently-edited copy of a file is probably the copy our friend made" heuristic.

So our method will walk through the file system, store files in a hash map, and identify the more 
recently edited file as the copied one when it finds a duplicate. Can you implement this in code?

### Hint 4

Here's a start. We'll initialize:

1. a hash map to hold the files we've already seen
2. a stack to hold directories and files as we go through them
3. a list to hold our output FilePaths objects

```
import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public static class FilePaths {

    private Path duplicatePath;
    private Path originalPath;

    public FilePaths(Path duplicatePath, Path originalPath) {
        this.duplicatePath = duplicatePath;
        this.originalPath  = originalPath;
    }

    public Path getDuplicatePath() {
        return duplicatePath;
    }

    public Path getOriginalPath() {
        return originalPath;
    }

    @Override
    public String toString() {
        return String.format("(duplicate: %s, original: %s)", duplicatePath, originalPath);
    }
}

public static List<FilePaths> findDuplicateFiles(Path startingDirectory) {

    Map<String, FileInfo> filesSeenAlready = new HashMap<>();
    Stack<Path> stack = new Stack<>();
    stack.push(startingDirectory);

    List<FilePaths> duplicates = new ArrayList<>();

    while (!stack.empty()) {

        Path currentPath = stack.pop();

    }

    return duplicates;
}
```

(We're going to make our method iterative instead of recursive to avoid stack overflow.)
 
### Hint 5

Here's one solution:

```
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public static class FilePaths {

    private Path duplicatePath;
    private Path originalPath;

    public FilePaths(Path duplicatePath, Path originalPath) {
        this.duplicatePath = duplicatePath;
        this.originalPath  = originalPath;
    }

    public Path getDuplicatePath() {
        return duplicatePath;
    }

    public Path getOriginalPath() {
        return originalPath;
    }

    @Override
    public String toString() {
        return String.format("(duplicate: %s, original: %s)", duplicatePath, originalPath);
    }
}


private static class FileInfo {

    long timeLastEdited;
    Path path;

    FileInfo(long timeLastEdited, Path path) {
        this.timeLastEdited = timeLastEdited;
        this.path = path;
    }
}

public List<FilePaths> findDuplicateFiles(Path startingDirectory) {
    Map<String, FileInfo> filesSeenAlready = new HashMap<>();
    Stack<Path> stack = new Stack<>();
    stack.push(startingDirectory);

    List<FilePaths> duplicates = new ArrayList<>();

    while (!stack.empty()) {

        Path currentPath = stack.pop();
        File currentFile = currentPath.toFile();

        // if it's a directory,
        // put the contents in our stack
        if (currentFile.isDirectory()) {
            for (File file : currentFile.listFiles()) {
                stack.add(file.toPath());
            }

        // if it's a file
        } else {

            // get its contents
            String fileContents = null;
            try {
                byte[] encodedFile = Files.readAllBytes(currentPath);
                fileContents = new String(encodedFile, "UTF-8");
            } catch (IOException e) {

                // show error and skip this file
                System.out.println(e);
                continue;
            }

            // get its last edited time
            long currentLastEditedTime = currentFile.lastModified();

            // if we've seen it before
            if (filesSeenAlready.containsKey(fileContents)) {

                FileInfo existingFileInfo = filesSeenAlready.get(fileContents);

                if (currentLastEditedTime > existingFileInfo.timeLastEdited) {

                    // current file is the dupe!
                    duplicates.add(new FilePaths(currentPath, existingFileInfo.path));

                } else {

                    // old file is the dupe!
                    duplicates.add(new FilePaths(existingFileInfo.path, currentPath));

                    // but also update filesSeenAlready to have the new file's info
                    filesSeenAlready.put(fileContents, new FileInfo(currentLastEditedTime, currentPath));
                }

            // if it's a new file, throw it in filesSeenAlready
            // and record its path and last edited time,
            // so we can tell later if it's a dupe
            } else {
                filesSeenAlready.put(fileContents, new FileInfo(currentLastEditedTime, currentPath));
            }
        }
    }

    return duplicates;
}
```

Okay, this'll work! What are our time and space costs? 

### Hint 6

We're putting the full contents of every file in our hash map! This costs O(b) time and space, where b 
is the total amount of space taken up by all the files on the file system.

That space cost is pretty unwieldy — we need to store a duplicate copy of our entire filesystem (like, 
several gigabytes of cat videos alone) in working memory!

Can we trim that space cost down? What if we're okay with losing a bit of accuracy (as in, we do a more 
"fuzzy" match to see if two files are the same)?

### Hint 7

What if instead of making our hash map keys the entire file contents, we hashed those contents first? 
So we'd store a constant-size "fingerprint" of the file in our hash map, instead of the whole file 
itself. This would give us O(1) space per file (O(n) space overall, where n is the number of files)!

That's a huge improvement. But we can take this a step further! While we're making the file matching 
"fuzzy," can we use a similar idea to save some time? Notice that our time cost is still order of the 
total size of our files on disc, while our space cost is order of the number of files.

### Hint 8

For each file, we have to look at every bit that the file occupies in order to hash it and take a 
"fingerprint." That's why our time cost is high. Can we fingerprint a file in constant time instead?

### Hint 9

What if instead of hashing the whole contents of each file, we hashed three fixed-size "samples" from 
each file made of the first x bytes, the middle x bytes, and the last x bytes? This would let us 
fingerprint a file in constant time!

How big should we make our samples?

### Hint 10

When your disc does a read, it grabs contents in constant-size chunks, called "blocks."

How big are the blocks? It depends on the file system. My super-hip Macintosh uses a file system 
called HFS+, which has a default block size of 4Kb (4,000 bytes) per block.

So we could use just 100 bytes each from the beginning middle and end of our files, but each time we 
grabbed those bytes, our disc would actually be grabbing 4000 bytes, not just 100 bytes. We'd just be 
throwing the rest away. We might as well use all of them, since having a bigger picture of the file 
helps us ensure that the fingerprints are unique. So our samples should be the the size of our file 
system's block size.
