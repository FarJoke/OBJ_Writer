package com.company;

import java.io.*;
import java.text.DecimalFormat;

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
        String str = "";

        str = writeVertexes(str, model);
        str = writeTextureVertexes(str, model);
        str = writePolygons(str, model);

        toFile(str, file.getAbsolutePath());
    }

    private static String writeVertexes(String str, Model model){
        for (int i = 0; i < model.vertices.size(); i++){
            final String vx = String.format("%.4f", model.vertices.get(i).x).replace(',', '.');
            final String vy = String.format("%.4f", model.vertices.get(i).y).replace(',', '.');
            final String vz = String.format("%.4f", model.vertices.get(i).z).replace(',', '.');
            str = str + "v  " + vx + " " + vy + " " + vz + "\n";
            System.out.println("Т " + i);
        }
        System.out.println("Точки");
        str = str + "# " + model.vertices.size() + " vertices";
        str+="\n";
        str+="\n";
        return str;
    }

    private static String writeTextureVertexes(String str, Model model){
        for (int i = 0; i < model.textureVertices.size(); i++){
            final String vtx = String.format("%.4f", model.textureVertices.get(i).x).replace(',', '.');
            final String vty = String.format("%.4f", model.textureVertices.get(i).y).replace(',', '.');
            str = str + "vt " + vtx + " " + vty + " " + "0.0000" + "\n";
            System.out.println("Ткс " + i);
        }
        System.out.println("Текстуры");
        str = str + "# " + model.textureVertices.size() + " texture coords";
        str+="\n";
        str+="\n";
        return str;
    }

    private static String writePolygons(String str, Model model){
        for (int i = 0; i < model.polygons.size(); i++){
            str = str + "f ";
            for (int j = 0; j < model.polygons.get(i).getVertexIndices().size(); j++){
                str = str  + String.valueOf(model.polygons.get(i).getVertexIndices().get(j) + 1) + "/"
                        + String.valueOf(model.polygons.get(i).getTextureVertexIndices().get(j) + 1) + " ";
            }
            str = str  + "\n";
            System.out.println("П " + i);
        }
        str = str + "# " + model.polygons.size() + " polygons";
        str+="\n";
        str+="\n";
        System.out.println("Полигоны");
        return str;
    }

}
