package BusinessLayer;
import BusinessLayer.Item;
import BusinessLayer.Order;
import BusinessLayer.Product;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InvoiceGenerator{

    public static void generateInvoice(Order order) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("invoice" + order.getId()+ ".pdf"));
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24);
            Paragraph title = new Paragraph("Invoice", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph customer = new Paragraph("Customer: " + order.getCustomer());
            document.add(customer);

            Paragraph time = new Paragraph("Time: " + order.getTime());
            document.add(time);

            Paragraph spacing = new Paragraph("\n");
            document.add(spacing);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            addTableHeader(table);
            addRows(table, order.getItems());
            document.add(table);

            Paragraph total = new Paragraph("Total Price: " + order.total());
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void addTableHeader(PdfPTable table) {
        String[] headers = {"ID", "Name", "Price", "Quantity", "Total"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(BaseColor.GRAY);
            cell.setPhrase(new Phrase(header));
            table.addCell(cell);
        }
    }

    private static void addRows(PdfPTable table, List<Item> items) {
        for (Item item : items) {
            table.addCell(String.valueOf(item.getProduct().getCode()));
            table.addCell(item.getProduct().getName());
            table.addCell(String.valueOf(item.getProduct().getPrice()));
            table.addCell(String.valueOf(item.getQuantityOrdered()));
            table.addCell(String.valueOf(item.total()));
        }
    }

//
//
//    public static void main(String[] args) {
//        // Example usage
//
//        Product product1 = new Product();
//        product1.setCode("1");
//        product1.setName("Product 1");
//        product1.setPrice(10.0);
//        product1.setStockQuantity(100);
//        product1.setDescription("aaaaaa");
//
//
//        Product product2 = new Product();
//        product2.setCode("2");
//        product2.setName("Poko");
//        product2.setPrice(15.0);
//        product2.setStockQuantity(50);
//        product2.setDescription("oooooo");
//
//        Item item1 =new Item();
////        item1.setId("1");
//        item1.setProduct(product1);
//        item1.setQuantityOrdered(7);
//        item1.setPrice();
//
//        Item item2 =new Item();
////        item2.setId("2");
//        item2.setProduct(product2);
//        item2.setQuantityOrdered(5);
//        item2.setPrice();
//
//        Order itemContainer=new Order();
//        itemContainer.add(item1);
//        itemContainer.add(item2);
//        itemContainer.setTime();
//        itemContainer.setCustomer("Muhammad Ahmad");
//        //itemContainer.setId();
//        generateInvoice(itemContainer);
//    }
}
