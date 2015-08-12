package util.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dto.ContractDTO;
import util.NumberSplitter;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Set;

/**
 * Created by Alexey on 03.08.2015.
 */
public class PdfUtil {
    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

    //todo move font to resources
    private static final String FONT_FILE_NAME = "C:/windows/fonts/calibri.ttf";

//    public String getFileName(){
//        return "Table.pdf";
//    }
//
//    public String getFilePath(){
//        return "E:\\Java_Dev\\jsf\\JSF-2-Dropdown-Box-Example\\";
//    }

    public static void uploadPDFToClient(File pdfFile) throws IOException {
        // Prepare.
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        System.out.println(pdfFile.getAbsolutePath() + " " + pdfFile.getName());
        File file = new File(pdfFile.getName());
//		File file = new File(getFileName());
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            // Open file.
            input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);

            // Init servlet response.
            response.reset();
            response.setHeader("Content-Type", "application/pdf");
            response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setHeader("Content-Disposition", "inline; filename=\"" + pdfFile.getName() + "\"");
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

            // Write file contents to response.
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            // Finalize task.
            output.flush();
        } finally {
            // Gently close streams.
            close(output);
            close(input);
        }

        // Inform JSF that it doesn't need to handle response.
        // This is very important, otherwise you will get the following exception in the logs:
        // java.lang.IllegalStateException: Cannot forward after response has been committed.
        facesContext.responseComplete();
    }

    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static File createPDF(Set<ContractDTO> contracts, String tariffTitle) {
        String pdfFileName = "table"+(int)(1000000*Math.random()) + ".pdf";
        File pdfFile = new File(pdfFileName);

        try {
            Document document = new Document();
            BaseFont bf = BaseFont.createFont(FONT_FILE_NAME,
                    BaseFont.IDENTITY_H , BaseFont.EMBEDDED);
            Font f_title = new Font(bf, 14);
            Font f_text = new Font(bf);

            PdfWriter.getInstance(document,
                    new FileOutputStream(pdfFileName));
            document.open();

            PdfPTable table = new PdfPTable(4); // 4 columns.

            // ----------------Table Header "Title"----------------
            // font
            Font font = new Font(bf, 14, Font.BOLD, BaseColor.WHITE);
            // create header cell
            PdfPCell cell = new PdfPCell(new Phrase("Контракты, подключенные "
                    +"к тарифу \""+ tariffTitle + "\"",font));
            // set Column span "1 cell = 6 cells width"
            cell.setColspan(4);
            // set style
            Style.headerCellStyle(cell);
            // add to table
            table.addCell(cell);

            table.addCell(createColumnNameCell("Номер"));
            table.addCell(createColumnNameCell("ФИО"));
            table.addCell(createColumnNameCell("Баланс"));
            table.addCell(createColumnNameCell("e-mail"));

            for (ContractDTO contractDTO : contracts){
                table.addCell(createGreyCell(
                        NumberSplitter.getBeautifulNumber(contractDTO.getPhoneNumber().toString())));
                table.addCell(createWhiteCell(contractDTO.getUserDTO().getLastname()+ " "
                        + contractDTO.getUserDTO().getName()));

                PdfPCell balanceCell = createGreyCell(contractDTO.getBalance().toString());
                balanceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                balanceCell.setPaddingRight(40f);
                table.addCell(balanceCell);

                table.addCell(createWhiteCell(contractDTO.getUserDTO().getEmail()));
            }

            document.add(table);
            document.close();

        } catch(Exception e){
            e.printStackTrace();
        }
        return pdfFile;
    }

    // create cells
    private static PdfPCell createWhiteCell(String text) throws IOException, DocumentException {
        // font
        BaseFont bf = BaseFont.createFont(FONT_FILE_NAME, BaseFont.IDENTITY_H , BaseFont.EMBEDDED);

        Font font = new Font(bf, 10, Font.NORMAL, BaseColor.DARK_GRAY);

        // create cell
        PdfPCell cell = new PdfPCell(new Phrase(text,font));

        // set style
        Style.labelCellStyle(cell);
        return cell;
    }

    // create cells
    private static PdfPCell createGreyCell(String text) throws IOException, DocumentException {

            BaseFont bf = BaseFont.createFont(FONT_FILE_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);

            // create cell
            PdfPCell cell = new PdfPCell(new Phrase(text, font));

            // set style
            Style.valueCellStyle(cell);
            return cell;
    }
    // create cells

    private static PdfPCell createColumnNameCell(String text) throws IOException, DocumentException {

        BaseFont bf = BaseFont.createFont(FONT_FILE_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf, 12, Font.NORMAL, new BaseColor(60,60,60));

        // create cell
        PdfPCell cell = new PdfPCell(new Phrase(text, font));

        // set style
        Style.columnNameCellStyle(cell);
        return cell;
    }


}
