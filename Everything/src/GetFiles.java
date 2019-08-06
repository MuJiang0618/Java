import java.util.ArrayList;
import java.util.List.*;
import java.io.File;
import java.io.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class GetFiles {
	static ArrayList<Product> visited_files = new ArrayList<Product>();
	public static int id_count = 1;
	public static File start_dir = new File("D:\\study");   // ��ʼĿ¼
	
	public static void main(String[] args) {
		ArrayList<Product> all_file_name = null;
		try {
			all_file_name = new GetFiles().getFiles(start_dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(all_file_name != null) {
			System.out.println("���� "+all_file_name.size()+" ���ļ�");
		}
		
		return;
	}

	public static ArrayList<Product> getFiles(File start_dir) throws IOException{
		Queue<File> unvisited_folds = new LinkedList<File>();
		unvisited_folds.add(start_dir);
		
		while(!unvisited_folds.isEmpty()) {
			File cur_dir = unvisited_folds.poll();
//			System.out.println("��ǰ����Ŀ¼:  " + cur_dir.getName());
			
			for(File f : cur_dir.listFiles()) {
				if(f.isDirectory()) {
					unvisited_folds.offer(f);
//					System.out.println("�ɹ����Ŀ¼: " + f.getName());
				} else {
					Product cur = new Product();
					cur.setName(f.getName());
					cur.setId(id_count);
					id_count = id_count + 1;
					visited_files.add(cur);           // һ�α���̫��ʱ, ���Ǵ�����ݿ�.
					System.out.println("�ɹ�����ļ�: " + f.getName());
				}
			}
		}

		return visited_files;
	}
}