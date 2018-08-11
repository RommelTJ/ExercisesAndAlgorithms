package com.rommelrico.exercises.s9systemdesign.findduplicatefiles;

import java.nio.file.Path;

public class FileInfo {

    long timeLastEdited;
    Path path;

    FileInfo(long timeLastEdited, Path path) {
        this.timeLastEdited = timeLastEdited;
        this.path = path;
    }

}
