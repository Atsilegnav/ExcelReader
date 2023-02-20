import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

    static void creaStampaLista() {
        String filePath = "C:\\Users\\g.morin\\Developer\\Allitude\\ReprPartyReference.xlsx";
        File file = new File(filePath);
        FileInputStream inputStream;
        ArrayList<Req> list = new ArrayList<>();
        try{
            inputStream = new FileInputStream(file);
            // Creiamo un'istanza della classe Workbook per gestire il file Excel
            Workbook workbook = WorkbookFactory.create(inputStream);
            // salto la prima riga


            //workbook.getNumberOfSheets();


            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    // salta la prima riga
                    continue;
                }
                Req req = new Req();
                for (Cell cell : row) {
                    req.add(cell.getColumnIndex(),cell.getStringCellValue());
                }
                list.add(req);
            }
            workbook.close();
            inputStream.close();

        } catch(FileNotFoundException e){
            System.out.println("Il file non è stato trovato: ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Writer.writeList(list);
    }

}
