import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class GenerateReport {
    int totalAmount, totalRides, bikeRides, carRides, carIncome, bikeIncome;

    public GenerateReport(ArrayList<Driver> drivers, ArrayList<Rider> riders) throws Exception {
        LocalDate localDate = LocalDate.now();
        int day = localDate.getDayOfMonth();

        for (Driver d:drivers) {
            for (Ride r:d.getRides()) {
                if (r.getDate().getDay() == day) {
                    totalAmount += r.getAmount();
                    totalRides++;
                    carRides++;
                    carIncome += r.getAmount();
                }
            }
        }

        for (Rider rider:riders) {
            for (Ride r:rider.getRides()) {
                if (r.getDate().getDay() == day) {
                    totalAmount += r.getAmount();
                    totalRides++;
                    bikeRides++;
                    bikeIncome += r.getAmount();
                }
            }
        }


        PdfWriter pdfWriter = new PdfWriter("Reports/Report.pdf");
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();

        Document document = new Document(pdfDocument);

        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

        document.add(new Paragraph("Daily Report").setFont(font).setBold().setFontSize(28).
                setStrokeColor(Color.DARK_GRAY));
        document.add(new Paragraph(new java.util.Date().toString()));
        document.add(new Paragraph("-------------------------------------------------------------------------------------"));
        document.add(new Paragraph("INDRIVER").setFont(font).setBold().setFontSize(24).
                setStrokeColor(Color.DARK_GRAY));
        document.add(new Paragraph("--------------------------------------------------------------------------------------"));
        document.add(new Paragraph("--------------------------------------------------------------------------------------"));
        document.add(new Paragraph("--------------------------------------------------------------------------------------"));

        Table table = new Table(2);

        table.addCell(new Cell().add("Total Rides").setFont(font).setFontSize(16));
        table.addCell(new Cell().add(totalRides + "").setFont(font).setFontSize(16));

        table.addCell(new Cell().add("Total Income").setFont(font).setFontSize(16));
        table.addCell(new Cell().add(totalAmount + "").setFont(font).setFontSize(16));

        table.addCell(new Cell().add("Total Car Rides").setFont(font).setFontSize(16));
        table.addCell(new Cell().add(carRides + "").setFont(font).setFontSize(16));

        table.addCell(new Cell().add("Car Rides Income").setFont(font).setFontSize(16));
        table.addCell(new Cell().add(carIncome + "").setFont(font).setFontSize(16));

        table.addCell(new Cell().add("Total Bike Rides").setFont(font).setFontSize(16));
        table.addCell(new Cell().add(bikeRides + "").setFont(font).setFontSize(16));

        table.addCell(new Cell().add("Bike Rides Income").setFont(font).setFontSize(16));
        table.addCell(new Cell().add(bikeIncome + "").setFont(font).setFontSize(16));

        document.add(table);

        document.close();

    }
}
