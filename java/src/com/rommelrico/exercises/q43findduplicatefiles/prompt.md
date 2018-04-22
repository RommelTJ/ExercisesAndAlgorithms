### Find Duplicate Files

You left your computer unlocked and your friend decided to troll you by copying a lot of your files to 
random spots all over your file system. Even worse, she saved the duplicate files with random, 
embarrassing names.

Write a method that returns a list of all the duplicate files. We'll check them by hand before actually 
deleting them, since programmatically deleting files is really scary. To help us confirm that two files 
are actually duplicates, return a list of FilePaths objects with variables for the original and duplicate 
paths:

```
import java.nio.file.Path;

public class FilePaths {

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
```

For example:
```
[(duplicate: /tmp/parker_is_dumb.mpg, original: /home/parker/secret_puppy_dance.mpg),
(duplicate: /home/trololol.mov, original: /etc/apache2/httpd.conf)]
```

You can assume each file was only duplicated once. 
