package Views;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import dao.DataDAO;
import model.Data;

public class UserView {
	private String email;
	UserView(String email){
		this.email=email;
	}
	public void home() {
		do {
			System.out.println("Welcome"+email);
			System.out.println("Press 1 to show hidden files");
			System.out.println("Press 2 to add new files");
			System.out.println("Press 3 to remove hidden files");
			System.out.println("Press 0 to exit");
			
			Scanner sc= new Scanner(System.in);
		    int ch=Integer.parseInt(sc.nextLine());
		    
			switch (ch) {
			case 1:List<Data> files = null;
				try {
					files = DataDAO.getAllFiles(this.email);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			        System.out.println("ID-File Name");
				    for(Data file:files) {
				    	System.out.println(file.getId()+"-"+file.getFileName());
				    	
				    }
				break;
			case 2:
				    System.out.println("Enter the file path:");
				    String path=sc.nextLine();
				    File f=new File(path);
				    Data file=new Data(0,f.getName(), path,this.email);
				try {
					DataDAO.hideFile(file);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					files = DataDAO.getAllFiles(this.email);
				
			    System.out.println("ID-File Name");
			    for(Data file2:files) {
			    	System.out.println(file2.getId()+"-"+file2.getFileName());
			    	
			    }
			    System.out.println("Enter a Id to unhide:");
			    int id=Integer.parseInt(sc.nextLine());
			     boolean isValidId=false;
			     for(Data file1:files) {
			    	 if(file1.getId()==id) {
			    		 isValidId=true;
			    		 break;
			    	 }
			     }
			     if(isValidId) {
			    	 DataDAO.Unhide(id);
			    	 
			     }
			     else {
			    	 System.out.println("Wrong Id");
			     }
				break;
				}
				catch (SQLException e) {
					
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			case 0:System.exit(0);
			}
		}
		while(true);
	}

}

