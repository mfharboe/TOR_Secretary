/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentDetails;

import BE.BESalary;
import BE.BEUsage;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
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

    public void printPDFFireman(ArrayList<BESalary> be, BEFireman befireman, String from, String to) {
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

            PdfPTable table = new PdfPTable(5);

            table.setWidthPercentage(100);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Indsats Navn"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Dato"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Indsats Type"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Funktion"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Timer"));


//            cell1.setBorderWidth(1f);
//            cell2.setBorderWidth(1f);
//            cell3.setBorderWidth(1f);
//            cell4.setBorderWidth(1f);
//            cell5.setBorderWidth(1f);
//            cell6.setBorderWidth(1f);

            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);


            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell3.setBackgroundColor(BaseColor.GRAY);
            cell4.setBackgroundColor(BaseColor.GRAY);
            cell5.setBackgroundColor(BaseColor.GRAY);


            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);


            table.completeRow();

            String function;
            if (befireman.isM_isTeamLeader()) {
                function = "Hold Leder";
            } else {
                function = "Brandmand";
            }

            for (BESalary wage : be) {
                PdfPCell cell7 = new PdfPCell(new Paragraph(wage.getM_incidentName()));
                PdfPCell cell8 = new PdfPCell(new Paragraph("" + wage.getM_date()));
                PdfPCell cell9 = new PdfPCell(new Paragraph(wage.getM_incidentType()));
                PdfPCell cell10 = new PdfPCell(new Paragraph(function));
                PdfPCell cell11 = new PdfPCell(new Paragraph("" + wage.getM_hours()));


                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell11.setHorizontalAlignment(Element.ALIGN_CENTER);


                cell7.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell9.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell10.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell11.setBackgroundColor(BaseColor.LIGHT_GRAY);


                table.addCell(cell7);
                table.addCell(cell8);
                table.addCell(cell9);
                table.addCell(cell10);
                table.addCell(cell11);
                ;
            }
            int[] relativeWidths = {3, 2, 2, 2, 1};
            table.setWidths(relativeWidths);

            document.add(table);

            document.add(new Paragraph(" "));

            PdfPTable tableOverwiev = new PdfPTable(2);
            tableOverwiev.setWidthPercentage(30);
            tableOverwiev.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell cellO1 = new PdfPCell(new Paragraph("Funktion"));
            PdfPCell cellO2 = new PdfPCell(new Paragraph("Timer Total"));

            cellO1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellO2.setHorizontalAlignment(Element.ALIGN_CENTER);

            cellO1.setBackgroundColor(BaseColor.GRAY);
            cellO2.setBackgroundColor(BaseColor.GRAY);

            tableOverwiev.addCell(cellO1);
            tableOverwiev.addCell(cellO2);



            PdfPCell cellO3 = new PdfPCell(new Paragraph(function));


            int totalHours = 0;

            for (BESalary wageTotal : be) {
                {
                    totalHours = totalHours + wageTotal.getM_hours();
                }

                PdfPCell cellO4 = new PdfPCell(new Paragraph("" + totalHours));

                cellO3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellO4.setHorizontalAlignment(Element.ALIGN_CENTER);

                cellO3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cellO4.setBackgroundColor(BaseColor.LIGHT_GRAY);

                tableOverwiev.addCell(cellO3);
                tableOverwiev.addCell(cellO4);

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

    public void printPDFRooster(ArrayList<BESalary> be, String from, String to) {
        Document document = new Document();
        try {

            PdfWriter.getInstance(document,
                    new FileOutputStream("Dato " + from + " - " + to + ".pdf"));

            document.open();
            document.add(new Paragraph("Dato: Fra " + from + " Til " + to));
            document.add(new Paragraph(" "));

            PdfPTable tableSalary = new PdfPTable(6);

            tableSalary.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell(new Paragraph("Navn"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Indsats Navn"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Dato"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Indsats Type"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Funktion"));
            PdfPCell cell6 = new PdfPCell(new Paragraph("Timer"));

            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell3.setBackgroundColor(BaseColor.GRAY);
            cell4.setBackgroundColor(BaseColor.GRAY);
            cell5.setBackgroundColor(BaseColor.GRAY);
            cell6.setBackgroundColor(BaseColor.GRAY);


            tableSalary.addCell(cell1);
            tableSalary.addCell(cell2);
            tableSalary.addCell(cell3);
            tableSalary.addCell(cell4);
            tableSalary.addCell(cell5);
            tableSalary.addCell(cell6);


            tableSalary.completeRow();

            for (BESalary wage : be) {
                PdfPCell cell8 = new PdfPCell(new Paragraph(wage.getM_lastName() + ", " + wage.getM_firstName()));
                PdfPCell cell9 = new PdfPCell(new Paragraph(wage.getM_incidentName()));
                PdfPCell cell10 = new PdfPCell(new Paragraph("" + wage.getM_date()));
                PdfPCell cell11 = new PdfPCell(new Paragraph(wage.getM_incidentType()));
                PdfPCell cell12 = new PdfPCell(new Paragraph(wage.getM_roleType()));
                PdfPCell cell13 = new PdfPCell(new Paragraph("" + wage.getM_hours()));


                cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell9.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell10.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell11.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell12.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell13.setBackgroundColor(BaseColor.LIGHT_GRAY);


                tableSalary.addCell(cell8);
                tableSalary.addCell(cell9);
                tableSalary.addCell(cell10);
                tableSalary.addCell(cell11);
                tableSalary.addCell(cell12);
                tableSalary.addCell(cell13);

            }
            int[] relativeWidthsSalary = {3, 3, 2, 2, 2, 1};
            tableSalary.setWidths(relativeWidthsSalary);

            document.add(tableSalary);


            document.close(); // no need to close PDFwriter?

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void printPdfUsage(ArrayList<BEIncidentDetails> beIncidentDetails, BEIncident beincident, ArrayList<BEUsage> usage) {
        Document document = new Document();
        try {

            PdfWriter.getInstance(document,
                    new FileOutputStream( beincident.getM_date() + " " +beincident.getM_incidentName()+  ".pdf"));

            document.open();
            Paragraph paragraphDate = new Paragraph("Dato : " + beincident.getM_date());

            paragraphDate.setAlignment(Element.ALIGN_RIGHT);
            document.add(paragraphDate);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Paragraph paragraphTitle = new Paragraph("Brand og Redning");
            paragraphTitle.setAlignment(Element.ALIGN_CENTER);
            Paragraph paragraphSubTitle = new Paragraph("Esbjerg");
            paragraphSubTitle.setAlignment(Element.ALIGN_CENTER);

            document.add(paragraphTitle);
            document.add(paragraphSubTitle);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Paragraph paragraphIncidentName = new Paragraph("Indsats Navn :  " + beincident.getM_incidentName());
            paragraphIncidentName.setAlignment(Element.ALIGN_LEFT);
            Paragraph paragraphIncidentType = new Paragraph("Alarm Type :  " + beIncidentDetails.get(0).getM_alarm().getM_description());
            paragraphIncidentType.setAlignment(Element.ALIGN_LEFT);

            document.add(paragraphIncidentName);
            document.add(paragraphIncidentType);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Paragraph paragraphUsage = new Paragraph("Brugt materiel : ");
            paragraphUsage.setAlignment(Element.ALIGN_LEFT);

            PdfPTable tableUsage = new PdfPTable(2);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Materiel"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Antal"));

            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);

            tableUsage.addCell(cell1);
            tableUsage.addCell(cell2);
            
            tableUsage.completeRow();

            for (BEUsage beusage : usage) {
                PdfPCell cell3 = new PdfPCell(new Paragraph(beusage.getM_material().getM_description()));
                PdfPCell cell4 = new PdfPCell(new Paragraph("" +beusage.getM_amount()));
                
                cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                
                tableUsage.addCell(cell3);
                tableUsage.addCell(cell4);
            }
            tableUsage.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            document.add(tableUsage);



            document.close(); // no need to close PDFwriter?

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
