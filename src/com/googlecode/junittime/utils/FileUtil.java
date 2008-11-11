package com.googlecode.junittime.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtil {
    public static File createTempFolder() {
        File file = removeableFile(tempFolder(), UUID.randomUUID().toString());
        file.mkdirs();
        return file;
    }

    private static File tempFolder() {
        return new File(tempFolderName());
    }

    public static File createFileInFolder(File folder, String fileName) throws IOException {
        File file = removeableFile(folder, fileName);
        file.createNewFile();
        return file;
    }

    public static File createTempFile() throws IOException {
        return createFileInFolder(tempFolder(), UUID.randomUUID().toString());
    }

    public static File aTempFile() {
        return removeableFile(tempFolder(), UUID.randomUUID().toString());
    }

    private static File removeableFile(File folder, String fileName) {
        File file = new File(folder, fileName);
        file.deleteOnExit();
        return file;
    }

    private static String tempFolderName() {
        return System.getProperty("java.io.tmpdir");
    }
}
