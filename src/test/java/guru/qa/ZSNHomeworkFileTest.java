package guru.qa;

import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.codeborne.pdftest.PDF;
import guru.qa.data.Products;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipFile;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;


public class ZSNHomeworkFileTest {

    private InputStream getFileFromZip(String fileName) throws Exception {
        ZipFile zip = new ZipFile(new File("src/test/resources/testFile.zip"));
        return zip.getInputStream(zip.getEntry(fileName));
    }

    @Test
    void pdfTest2() throws Exception {
        InputStream pdfFileStream = getFileFromZip("61.pdf");
        PDF pdf = new PDF(pdfFileStream);
        assertThat(pdf.text).contains("Шляпа лежала на комоде");
    }


    @Test
    void xlsxTest() throws Exception {
        XLS xlsx = new XLS(getFileFromZip("testxlsx.xlsx"));
        assertThat(xlsx.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue())
                .isEqualTo("silk");
    }

    @Test
    void csvTest() throws Exception {
        CSVReader csv = new CSVReader(
                new InputStreamReader(getFileFromZip("testcsv.csv"), UTF_8)
        );
        List<String[]> csvArray = csv.readAll();
        assertThat(csvArray).contains(
                new String[]{"silk;black;2"},
                new String[]{"cashmere;green;4"});
    }


    @Test
    void jsonTest() throws Exception {
        ClassLoader classLoader = ZSNHomeworkFileTest.class.getClassLoader();
        var fileName = "testJson.json";
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Products product = objectMapper.readValue(inputStream, Products.class);
            assertThat(product.getDesignerName()).isEqualTo("DIOR");
            assertThat(product.getColor()).isEqualTo("red");
            assertThat(product.getCost()).isEqualTo(129999);
            assertThat(product.getAvailability().getSize()).isEqualTo(38);
            assertThat(product.getAvailability().isInStock()).isEqualTo(true);
            assertThat(product.getStructure()).contains("viscose","polyamide","wool");
        }
    }

}
