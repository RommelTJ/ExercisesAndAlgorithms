package com.rommelrico.exercises.s9systemdesign.findduplicatefiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Solution
 *
 * We walk through our whole file system iteratively. As we go, we take a "fingerprint" of each file in constant time
 * by hashing the first few, middle few, and last few bytes. We store each file's fingerprint in a hash map as we go.
 *
 * If a given file's fingerprint is already in our hash map, we assume we have a duplicate. In that case, we assume the
 * file edited most recently is the one created by our friend.
 *
 * Complexity
 *
 * Each "fingerprint" takes O(1) time and space, so our total time and space costs are O(n) where n is the number of
 * files on the file system.
 *
 * If we add the last-minute check to see if two files with the same fingerprints are actually the same files (which
 * we probably should), then in the worst case all the files are the same and we have to read their full contents to
 * confirm this, giving us a runtime that's order of the total size of our files on disc.
 *
 * What We Learned
 *
 * The main insight was to save time and space by "fingerprinting" each file.
 *
 * This question is a good example of a "messy" interview problem. Instead of one optimal solution, there's a big knot
 * of optimizations and trade-offs. For example, our hashing-based approach wins us a faster runtime, but it can give
 * us false positives.
 *
 * For messy problems like this, focus on clearly explaining to your interviewer what the trade-offs are for each
 * decision you make. The actual choices you make probably don't matter that much, as long as you show a strong ability
 * to understand and compare your options.
 *
 */
public class FindDuplicateFiles {
    private static final int SAMPLE_SIZE = 4000;

    public static List<FilePaths> findDuplicateFiles(Path startingDirectory) {
        Map<String, FileInfo> filesSeenAlready = new HashMap<>();
        Stack<Path> stack = new Stack<>();
        stack.push(startingDirectory);

        List<FilePaths> duplicates = new ArrayList<>();

        while (!stack.empty()) {

            Path currentPath = stack.pop();
            File currentFile = new File(currentPath.toString());

            // if it's a directory,
            // put the contents in our stack
            if (currentFile.isDirectory()) {
                for (File file : currentFile.listFiles()) {
                    stack.add(file.toPath());
                }

                // if it's a file
            } else {

                // get its hash
                String fileHash;
                try {
                    fileHash = sampleHashFile(currentPath);
                } catch (IOException | NoSuchAlgorithmException e) {

                    // show error and skip this file
                    e.printStackTrace();
                    continue;
                }

                // get its last edited time
                long currentLastEditedTime = currentFile.lastModified();

                // if we've seen it before
                if (filesSeenAlready.containsKey(fileHash)) {

                    FileInfo fileInfo = filesSeenAlready.get(fileHash);
                    long existingLastEditedTime = fileInfo.timeLastEdited;
                    Path existingPath = fileInfo.path;

                    if (currentLastEditedTime > existingLastEditedTime) {

                        // current file is the dupe!
                        duplicates.add(new FilePaths(currentPath, existingPath));

                    } else {

                        // old file is the dupe!
                        duplicates.add(new FilePaths(existingPath, currentPath));

                        // but also update filesSeenAlready to have the new file's info
                        filesSeenAlready.put(fileHash, new FileInfo(currentLastEditedTime, currentPath));
                    }

                    // if it's a new file, throw it in filesSeenAlready
                    // and record its path and last edited time,
                    // so we can tell later if it's a dupe
                } else {
                    filesSeenAlready.put(fileHash, new FileInfo(currentLastEditedTime, currentPath));
                }
            }
        }
        return duplicates;
    }

    private static String sampleHashFile(Path path) throws IOException, NoSuchAlgorithmException {
        final long totalBytes = new File(path.toString()).length();

        try(InputStream inputStream = new FileInputStream(path.toString())) {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            DigestInputStream digestInputStream = new DigestInputStream(inputStream, digest);

            // if the file is too short to take 3 samples, hash the entire file
            if (totalBytes < SAMPLE_SIZE * 3) {
                byte[] bytes = new byte[(int) totalBytes];
                digestInputStream.read(bytes);
            } else {
                byte[] bytes = new byte[SAMPLE_SIZE * 3];
                long numBytesBetweenSamples = (totalBytes - SAMPLE_SIZE * 3) / 2;

                // read first, middle and last bytes
                for (int n = 0; n < 3; n++) {
                    digestInputStream.read(bytes, n * SAMPLE_SIZE, SAMPLE_SIZE);
                    digestInputStream.skip(numBytesBetweenSamples);
                }
            }
            return new BigInteger(1, digest.digest()).toString(16);
        }
    }

    public static void main(String[] args) {
        // Starting directory
        Path myPath = FileSystems.getDefault().getPath("."); // Working directory
        List<FilePaths> filePathsList = findDuplicateFiles(myPath); // Duplicate files
        System.out.println(String.format("Duplicate files in path: %s: ", myPath));
        System.out.println(filePathsList.toString());
    }
}
