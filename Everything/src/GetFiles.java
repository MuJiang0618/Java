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
	public static File start_dir = new File("D:\\study");   // 起始目录
	
	public static void main(String[] args) {
		ArrayList<Product> all_file_name = null;
		try {
			all_file_name = new GetFiles().getFiles(start_dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(all_file_name != null) {
			System.out.println("共有 "+all_file_name.size()+" 个文件");
		}
		
		return;
	}

	public static ArrayList<Product> getFiles(File start_dir) throws IOException{
		Queue<File> unvisited_folds = new LinkedList<File>();
		unvisited_folds.add(start_dir);
		
		while(!unvisited_folds.isEmpty()) {
			File cur_dir = unvisited_folds.poll();
//			System.out.println("当前遍历目录:  " + cur_dir.getName());
			
			for(File f : cur_dir.listFiles()) {
				if(f.isDirectory()) {
					unvisited_folds.offer(f);
//					System.out.println("成功添加目录: " + f.getName());
				} else {
					Product cur = new Product();
					cur.setName(f.getName());
					cur.setId(id_count);
					id_count = id_count + 1;
					visited_files.add(cur);           // 一次遍历太费时, 考虑存进数据库.
					System.out.println("成功添加文件: " + f.getName());
				}
			}
		}

		return visited_files;
	}
}