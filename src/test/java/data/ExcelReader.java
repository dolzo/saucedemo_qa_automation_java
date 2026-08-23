package data;

import com.poiji.bind.Poiji;
import models.ErrorMessage;
import models.ProductItem;

import java.io.File;
import java.util.List;

public class ExcelReader {

    private static final String excelPath = "src/test/resources/data/precios.xlsx";

    public static List<ProductItem> readProductListExcel(){
        return Poiji.fromExcel(new File(excelPath), ProductItem.class);
    }

    public static List<ErrorMessage> readErrorMessageExcel(){
        return Poiji.fromExcel(new File(excelPath), ErrorMessage.class);
    }

}
