import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Req {

    String classe;
    String campo;
    String tipo;
    String esempio;
    String descrizione;
    Boolean obbligatori;

    public void add(int index, String value){
        switch (index){
            case 0:
                this.classe = value;
                break;
            case 1:
                this.campo = value;
                break;
            case 2:
                this.tipo = value;
                break;
            case 3:
                this.esempio = value;
                break;
            case 4:
                this.descrizione = value;
                break;
            case 5:
                if(value!=null)
                    this.obbligatori = true;
                break;
        }
    }
}
