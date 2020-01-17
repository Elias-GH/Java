package store.business.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ErreurOuvertureXML extends Exception {
	
	public ErreurOuvertureXML(String error) {
		 JOptionPane.showMessageDialog(null,error);
		 gestion_log(error);
		 System.exit(1);
	}
	
	public void gestion_log(String text_error) {
		
		
		try {
			File files = new File("./files/log.txt");
			FileWriter fw = new FileWriter(files.getAbsoluteFile(),true);
			   BufferedWriter bw = new BufferedWriter(fw);
			   bw.write(text_error+"\n");
			   bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
