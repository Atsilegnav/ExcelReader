import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

    static void writeList(ArrayList<Req> list){
        try {
            File file = new File("C:\\Users\\g.morin\\Developer\\Allitude\\prova.txt");
            FileWriter writer = new FileWriter(file);
            String header = list.get(0).getClasse();
            writer.write("package it.allitude.corebanking.custodyagreement." +
                    (header.contains("Repr") ? "Repr" : "Rep") +"; \n");
            String toImport;
            String toFill;

            toImport = "import com.fasterxml.jackson.databind.annotation.JsonDeserialize; \n" +
            "import com.fasterxml.jackson.databind.annotation.JsonSerialize; \n" +
            "import io.swagger.v3.oas.annotations.media.Schema; \n" +
            "import org.immutables.value.Value; \n";
            toFill = "@Value.Immutable \n" +
                    "@JsonDeserialize(as = Immutable" +
                    (header.contains("Rep") ? header.replace("Rep.java",".class") : header.replace("Repr.java",".class")) + ") \n" +
                    "@JsonSerialize(as = Immutable" +
                    (header.contains("Rep") ? header.replace("Rep.java",".class") : header.replace("Repr.java",".class")) + ") \n" +
                    "@Schema(name = \"" + header.replace(".java", "") + "\",\n" +
                    "        description = \" \") \n" + "\n" +
                    "public interface " + header.replace(".java", "") + " {\n";
            for(Req req : list){
                toFill = toFill + "\n" +
                        "@Schema(required = " + (req.obbligatori ? "true" : "false") +
                        ", example = \"" + req.esempio +
                        "\", description = \"" + req.descrizione + "\")\n" +
                        (req.obbligatori ? "Optional<" + req.tipo + ">" : req.tipo)+ " " + req.campo +"();\n";
                if(req.enumeration.equals("Si")){
                    toImport = toImport + "\n" + "import it.allitude.corebanking.custodyagreement.enumeration." + req.campo + "\n";
                }
            }
            toFill = toFill + "}";
            writer.write(toImport);
            writer.write(toFill);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
