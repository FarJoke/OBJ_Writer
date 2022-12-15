package OBJ.Writer;

import com.company.Model;
import com.company.Polygon;

import java.io.*;

public class ObjWriter {

    public static void createObjFile(String absoluteFilePath) throws IOException {
        String fileSeparator = System.getProperty("file.separator");
        absoluteFilePath += fileSeparator + "file.obj";
        File file = new File(absoluteFilePath);
    }

    public static void writeToFile(Model model, File file) throws IOException {
        String str = "";

        str += writeVertexes(model);
        str += writeTextureVertexes(model);
        str += writeNormals(model);
        str += writePolygons(model);

        toFile(str, file.getAbsolutePath());
    }

    protected static void toFile(String line, String fileName) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(fileName);
        printWriter.print(line);
        printWriter.close();
    }

    protected static String writeVertexes(final Model model){
        String str = "";
        for (int i = 0; i < model.vertices.size(); i++){
            final String vx = String.format("%.4f", model.vertices.get(i).x).replace(',', '.');
            final String vy = String.format("%.4f", model.vertices.get(i).y).replace(',', '.');
            final String vz = String.format("%.4f", model.vertices.get(i).z).replace(',', '.');
            str = str + "v  " + vx + " " + vy + " " + vz + "\n";
        }
        str = str + "# " + model.vertices.size() + " vertices";
        str+="\n";
        str+="\n";
        return str;
    }

    protected static String writeTextureVertexes(final Model model){
        String str = "";
        for (int i = 0; i < model.textureVertices.size(); i++){
            final String vtx = String.format("%.4f", model.textureVertices.get(i).x).replace(',', '.');
            final String vty = String.format("%.4f", model.textureVertices.get(i).y).replace(',', '.');
            str = str + "vt " + vtx + " " + vty + " " + "0.0000" + "\n";
        }
        str = str + "# " + model.textureVertices.size() + " texture coords";
        str+="\n";
        str+="\n";
        return str;
    }

    protected static String writeNormals(final Model model){
        String str = "";
        for (int i = 0; i < model.normals.size(); i++){
            final String vx = String.format("%.4f", model.normals.get(i).x).replace(',', '.');
            final String vy = String.format("%.4f", model.normals.get(i).y).replace(',', '.');
            final String vz = String.format("%.4f", model.normals.get(i).z).replace(',', '.');
            str = str + "vn  " + vx + " " + vy + " " + vz + "\n";
        }
        str = str + "# " + model.normals.size() + " normals";
        str+="\n";
        str+="\n";
        return str;
    }

    protected static String writePolygons(final Model model){
        String str = "";
        for (int i = 0; i < model.polygons.size(); i++){
            str = str + "f ";
            final Polygon pol = model.polygons.get(i);
            for (int j = 0; j < pol.getVertexIndices().size(); j++){
                if (!pol.getTextureVertexIndices().isEmpty() && pol.getNormalIndices().isEmpty()){
                    str = str  + (pol.getVertexIndices().get(j) + 1) + "/"
                           + (pol.getTextureVertexIndices().get(j) + 1) + " ";
                }
                if (pol.getTextureVertexIndices().isEmpty() && pol.getNormalIndices().isEmpty()){
                    str = str  + (pol.getVertexIndices().get(j) + 1) +  " ";
                }
                if (!pol.getTextureVertexIndices().isEmpty() && !pol.getNormalIndices().isEmpty()){
                    str = str  + (pol.getVertexIndices().get(j) + 1) + "/"
                            + (pol.getTextureVertexIndices().get(j) + 1) + "/"
                            + (pol.getNormalIndices().get(j) + 1) + " ";
                }
                if (pol.getTextureVertexIndices().isEmpty() && !pol.getNormalIndices().isEmpty()){
                    str = str  + (pol.getVertexIndices().get(j) + 1) + "//"
                            + (pol.getNormalIndices().get(j) + 1) + " ";
                }
            }
            str = str  + "\n";
        }
        str = str + "# " + model.polygons.size() + " polygons";
        str+="\n";
        str+="\n";
        return str;
    }

}
