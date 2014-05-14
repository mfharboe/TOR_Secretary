/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.BEFireman;
import BE.BESalary;
import BE.BEWage;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morten
 */
public class DALPDF {

    private static DALPDF m_instance;

    private DALPDF() {
    }

    public static DALPDF getInstance() {
        if (m_instance == null) {
            m_instance = new DALPDF();
        }
        return m_instance;

    }

    public void printPDFFireman(ArrayList<BEWage> be, BEFireman befireman, String from, String to, ArrayList<BESalary> salary) {
        Document document = new Document();
        try {

            PdfWriter.getInstance(document,
                    new FileOutputStream(be.get(0).getM_lastName() + ", " + be.get(0).getM_firstName() + " " + from + " - " + to + ".pdf"));

            document.open();
            document.add(new Phrase("Navn : " + be.get(0).getM_lastName() + ", "));
            document.add(new Phrase(be.get(0).getM_firstName() + " "));
            document.add(new Paragraph("Addresse : " + befireman.getM_address() + ", " + befireman.getM_zipCode().getM_city() + " " + befireman.getM_zipCode().getM_zipCode()));
            document.add(new Paragraph("Løn Nr. : " + befireman.getM_paymentNumber()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Dato: Fra " + from + " Til " + to));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(6);

            table.setWidthPercentage(100);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Indsats Navn"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Dato"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Indsats Type"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Funktion"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Timer"));
            PdfPCell cell6 = new PdfPCell(new Paragraph("LønKode"));

            cell1.setBorderWidth(1f);
            cell2.setBorderWidth(1f);
            cell3.setBorderWidth(1f);
            cell4.setBorderWidth(1f);
            cell5.setBorderWidth(1f);
            cell6.setBorderWidth(1f);

            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell3.setBackgroundColor(BaseColor.GRAY);
            cell4.setBackgroundColor(BaseColor.GRAY);
            cell5.setBackgroundColor(BaseColor.GRAY);
            cell6.setBackgroundColor(BaseColor.GRAY);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);

            table.completeRow();

            for (BEWage wage : be) {
                PdfPCell cell7 = new PdfPCell(new Paragraph(wage.getM_incidentName()));
                PdfPCell cell8 = new PdfPCell(new Paragraph("" + wage.getM_date()));
                PdfPCell cell9 = new PdfPCell(new Paragraph(wage.getM_incidentType()));
                PdfPCell cell10 = new PdfPCell(new Paragraph(wage.getM_roleType()));
                PdfPCell cell11 = new PdfPCell(new Paragraph("" + wage.getM_hours()));
                PdfPCell cell12 = new PdfPCell(new Paragraph("" + wage.getM_salaryId()));

                cell7.setBorderWidth(1f);
                cell8.setBorderWidth(1f);
                cell9.setBorderWidth(1f);
                cell10.setBorderWidth(1f);
                cell11.setBorderWidth(1f);
                cell12.setBorderWidth(1f);

                cell7.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell9.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell10.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell11.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell12.setBackgroundColor(BaseColor.LIGHT_GRAY);

                table.addCell(cell7);
                table.addCell(cell8);
                table.addCell(cell9);
                table.addCell(cell10);
                table.addCell(cell11);
                table.addCell(cell12);
            }
            int[] relativeWidths = {3, 2, 2, 2, 1, 2};
            table.setWidths(relativeWidths);

            document.add(table);

            PdfPTable tableOverwiev = new PdfPTable(2);
            tableOverwiev.setWidthPercentage(50);

            PdfPCell cellO1 = new PdfPCell(new Paragraph("Lønkode"));
            PdfPCell cellO2 = new PdfPCell(new Paragraph("Timer Total"));

            tableOverwiev.addCell(cellO1);
            tableOverwiev.addCell(cellO2);

             tableOverwiev.completeRow();
            
            int wage439 = 0;
            int wage442 = 0;
            int wage443 = 0;
            int wage446 = 0;
            int wage447 = 0;
            int wage449 = 0;
            
            for (BEWage wageTotal : be) {
                if (wageTotal.getM_salaryId() == 439) {
                    wage439 = wage439 + wageTotal.getM_hours();
                }
                if (wageTotal.getM_salaryId() == 442) {
                    wage442 = wage442 + wageTotal.getM_hours();
                }
                if (wageTotal.getM_salaryId() == 443) {
                    wage443 = wage443 + wageTotal.getM_hours();
                }
                if (wageTotal.getM_salaryId() == 446) {
                    wage446 = wage446 + wageTotal.getM_hours();
                }
                if (wageTotal.getM_salaryId() == 447) {
                    wage447 = wage447 + wageTotal.getM_hours();
                }
                if (wageTotal.getM_salaryId() == 449) {
                    wage449 = wage449 + wageTotal.getM_hours();
                }
            }
            ArrayList<Integer> totals = new ArrayList<>();
            totals.add(wage439);
            totals.add(wage442);
            totals.add(wage443);
            totals.add(wage446);
            totals.add(wage447);
            totals.add(wage449);
            
           //System.out.println("" + wage439 +" "+ wage442 +" "+ wage443 +" "+ wage446 +" "+ wage447 +" " +wage449);
            

            for (BESalary besalary : salary) {
                int i = 0;
                i++;
                PdfPCell cellO3 = new PdfPCell(new Paragraph("" + besalary.getM_id()));
                PdfPCell cellO4 = new PdfPCell(new Paragraph("" + totals.get(i)));

                tableOverwiev.addCell(cellO3);
                tableOverwiev.addCell(cellO4);
                
                tableOverwiev.completeRow();
            }



            int[] relativeWidthsOverview = {1, 1};
            tableOverwiev.setWidths(relativeWidthsOverview);

            document.add(tableOverwiev);

            document.close(); // no need to close PDFwriter?

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void printPDFRooster(ArrayList<BEWage> be, String from, String to) {
        Document document = new Document();
        try {

            PdfWriter.getInstance(document,
                    new FileOutputStream("Dato " + from + " - " + to + ".pdf"));

            document.open();
            document.add(new Paragraph("Dato: Fra " + from + " Til " + to));
            document.add(new Paragraph(" "));

            PdfPTable tableSalary = new PdfPTable(7);

            tableSalary.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell(new Paragraph("Navn"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Indsats Navn"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Dato"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Indsats Type"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Funktion"));
            PdfPCell cell6 = new PdfPCell(new Paragraph("Timer"));
            PdfPCell cell7 = new PdfPCell(new Paragraph("LønKode"));

            cell1.setBorderWidth(1f);
            cell2.setBorderWidth(1f);
            cell3.setBorderWidth(1f);
            cell4.setBorderWidth(1f);
            cell5.setBorderWidth(1f);
            cell6.setBorderWidth(1f);
            cell7.setBorderWidth(1f);

            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell3.setBackgroundColor(BaseColor.GRAY);
            cell4.setBackgroundColor(BaseColor.GRAY);
            cell5.setBackgroundColor(BaseColor.GRAY);
            cell6.setBackgroundColor(BaseColor.GRAY);
            cell7.setBackgroundColor(BaseColor.GRAY);

            tableSalary.addCell(cell1);
            tableSalary.addCell(cell2);
            tableSalary.addCell(cell3);
            tableSalary.addCell(cell4);
            tableSalary.addCell(cell5);
            tableSalary.addCell(cell6);
            tableSalary.addCell(cell7);

            tableSalary.completeRow();

            for (BEWage wage : be) {
                PdfPCell cell8 = new PdfPCell(new Paragraph(wage.getM_lastName() + ", " + wage.getM_firstName()));
                PdfPCell cell9 = new PdfPCell(new Paragraph(wage.getM_incidentName()));
                PdfPCell cell10 = new PdfPCell(new Paragraph("" + wage.getM_date()));
                PdfPCell cell11 = new PdfPCell(new Paragraph(wage.getM_incidentType()));
                PdfPCell cell12 = new PdfPCell(new Paragraph(wage.getM_roleType()));
                PdfPCell cell13 = new PdfPCell(new Paragraph("" + wage.getM_hours()));
                PdfPCell cell14 = new PdfPCell(new Paragraph("" + wage.getM_salaryId()));

                cell8.setBorderWidth(1f);
                cell9.setBorderWidth(1f);
                cell10.setBorderWidth(1f);
                cell11.setBorderWidth(1f);
                cell12.setBorderWidth(1f);
                cell13.setBorderWidth(1f);
                cell14.setBorderWidth(1f);

                cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell9.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell10.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell11.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell12.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell13.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell14.setBackgroundColor(BaseColor.LIGHT_GRAY);

                tableSalary.addCell(cell8);
                tableSalary.addCell(cell9);
                tableSalary.addCell(cell10);
                tableSalary.addCell(cell11);
                tableSalary.addCell(cell12);
                tableSalary.addCell(cell13);
                tableSalary.addCell(cell14);
            }
            int[] relativeWidthsSalary = {3, 3, 2, 2, 2, 1, 2};
            tableSalary.setWidths(relativeWidthsSalary);

            document.add(tableSalary);


            document.close(); // no need to close PDFwriter?

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
