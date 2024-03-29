package pkg18424023_hibernate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileData {
	public FileData() {
		super();
	}
	
	public static LopHoc readDSSV(String path) throws Exception{
		LopHoc lopHoc = new LopHoc();
		File file = new File(path);
		if(file.exists()) {
			Scanner s = new Scanner(file);
			lopHoc.setTenLop(file.getName().replaceFirst("[.][^.]+$", ""));
			while(s.hasNextLine()) {
				String line = s.nextLine();
				String[] data = line.split(",");
				SinhVien sv = new SinhVien(data[0],data[1],data[2],data[3]);
				if(!lopHoc.ThemSV(sv)) {
					return null;
				}
			}
		}

		return lopHoc;
	}
	
	public static <T> boolean writeData(String pathFolder,String fileName, boolean isAppend, 
			ArrayList<T> DataList) throws IOException {
		FileWriter fw;
		try 
		{
			fw = new FileWriter(pathFolder+fileName+".csv", isAppend);
		}
		catch(IOException exc) 
		{
			return false;
		}
		
		for(T data : DataList) {
			String str = data.toString() + "\r\n";
			fw.write(str);
		}
		
		fw.close();
		return true;
	}
	
	public static String getFileName(String fileName) {
		return fileName.replaceFirst("[.][^.]+$", "");
		
	}
	
	public static ThoiKhoaBieu readTKB(String path) throws Exception {
		ThoiKhoaBieu tkb = new ThoiKhoaBieu();
		File file = new File(path);
		if(file.exists()) {
			Scanner s = new Scanner(file);
			tkb.setTenLop(file.getName().replaceFirst("[.][^.]+$", ""));
			while(s.hasNextLine()) {
				String line = s.nextLine();
				String[] data = line.split(",");
				MonHoc mh = new MonHoc(data[0],data[1],data[2]);
				if(!tkb.ThemMH(mh)) {
					return null;
				}
			}
		}
		
		return tkb;
	}
	
	public static ArrayList<String> getFilenameInFolder(String path) {
		ArrayList<String> fileNames = new ArrayList<String>();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	fileNames.add(FileData.getFileName(file.getName()));
		    }
		}
		
		return fileNames;
	}
	
	public static ArrayList<Account> readUsersInfo(String path) throws FileNotFoundException{
		ArrayList<Account> users = new ArrayList<Account>();
		File file = new File(path);
		if(file.exists()) {
			Scanner s = new Scanner(file);
			while(s.hasNextLine()) {
				String line = s.nextLine();
				String[] data = line.split(",");
				Account user = new Account(data[0],data[1],Integer.parseInt(data[2]));
				users.add(user);
			}
		}
		return users;
	}
	
	public static BangDiem readBangDiem(String path) throws FileNotFoundException {
		BangDiem bangDiem = new BangDiem();
		File file = new File(path);
		if(file.exists()) {
			Scanner s = new Scanner(file);
			bangDiem.setTenLop(file.getName().replaceFirst("[.][^.]+$", ""));
			while(s.hasNextLine()) {
				String line = s.nextLine();
				String[] data = line.split(",");
				Diem diem = new Diem(data[0],data[1],Float.parseFloat(data[2]),
						Float.parseFloat(data[3]),Float.parseFloat(data[4]),
						Float.parseFloat(data[5]));
				if(!bangDiem.ThemDiem(diem)) {
					return null;
				}
			}
		}

		return bangDiem;
	}
}
