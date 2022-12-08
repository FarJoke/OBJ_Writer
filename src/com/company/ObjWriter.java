package com.company;

import java.io.*;

public class ObjWriter {

    public static void createObjFile(String absoluteFilePath) throws IOException {
        String fileSeparator = System.getProperty("file.separator");
        absoluteFilePath += fileSeparator + "file.obj";
        File file = new File(absoluteFilePath);
    }

    public static void toFile(String line, String fileName) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(fileName);
        printWriter.print(line);
        printWriter.close();
    }

    public static void writeToFile(Model model, File file) throws IOException {
        file.canWrite();
        FileWriter fw = new FileWriter(file, false);
        String str = "";
        for (int i = 0; i < model.vertices.size(); i++){
            str = str + "v " + String.valueOf(model.vertices.get(i).x) + " " +
                    String.valueOf(model.vertices.get(i).y) + " " +
                    String.valueOf(model.vertices.get(i).z) + "\n";
           toFile(str, file.getAbsolutePath());
        }


    }
}
