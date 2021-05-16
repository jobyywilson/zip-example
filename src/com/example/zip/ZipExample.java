package com.example.zip;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author joby.w
 *
 */
public class ZipExample {

    public static void main(String args[]) throws IOException {
        //compressZIP(Paths.get("test.zip"),Paths.get("test.json"),Paths.get("test.xml"));
        Files.createDirectory(Paths.get("files"));
        decompressZIP(Paths.get("test.zip"),Paths.get("files"));
    }

    public static void compressZIP(Path outputFile,Path... filesToZip) throws IOException {
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(outputFile))){
            for(Path file : filesToZip){
                ZipEntry zipEntry = new ZipEntry((file.getFileName().toString()));
                zipOutputStream.putNextEntry(zipEntry);
                Files.copy(file,zipOutputStream);
            }
        }
    }

    public static void decompressZIP(Path zipFile, Path outputDir) throws IOException {
        try(ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile))){
            ZipEntry entry;
            while((entry = zipInputStream.getNextEntry())!=null){
                Path outputFile = outputDir.resolve(entry.getName());
                Files.copy(zipInputStream,outputFile);
            }
            zipInputStream.closeEntry();
        }
    }


}
