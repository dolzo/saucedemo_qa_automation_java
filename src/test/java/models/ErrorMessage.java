package models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;

@ExcelSheet("mensajes")
public class ErrorMessage {

    @ExcelCellName("NOMBRE")
    private String nombre;

    @ExcelCellName("MENSAJE")
    private String mensaje;

    public String getNombre() {
        return nombre;
    }

    public String getMensaje() {
        return mensaje;
    }
}
