/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.BESalary;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
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
    
    private DALPDF(){
        
    }
    
    public static DALPDF getInstance(){
        if(m_instance == null)
            m_instance = new DALPDF();
        return m_instance;
        
    }
     
     
     public void printPDF(ArrayList<BESalary> be){
         Document document = new Document();
         try {
            PdfWriter.getInstance(document,
                    new FileOutputStream("test.pdf"));

            document.open();
            for(int i = 0; i<1; i++){
            document.add(new Phrase(be.get(i).getM_lastName()+ ", " ));
            document.add(new Phrase(be.get(i).getM_firstName()+ " "));
            }
            document.add(new Paragraph(" "));
            for(BESalary salary : be){
            document.add(new Phrase("Dato : ".toUpperCase() + salary.getM_date()+ " "));
            document.add(new Phrase("Indsats navn : ".toUpperCase() +salary.getM_incidentName()+ " "));
            document.add(new Paragraph());
            document.add(new Phrase("Indsats Type : ".toUpperCase() +salary.getM_incidentType()+ " "));
            document.add(new Phrase("Funktion : ".toUpperCase() + salary.getM_roleType()+ " "));
            document.add(new Phrase("LønKode : ".toUpperCase() + salary.getM_salaryId()));
            document.add(new Paragraph(" "));
            
            }
            
            document.close(); // no need to close PDFwriter?

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
         
     }
        
            
   
    
    
    
        
        
        

       
        
    



 
    

