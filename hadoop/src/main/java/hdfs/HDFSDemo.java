package hdfs;

import java.io.IOException;

public class HDFSDemo {

	/**
	 * @param args
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException {
		HDFSOprator.listDatanodes();
		System.out.println(HDFSOprator.isExist("/user/ambo/input"));
		HDFSOprator.createFile("/user/ambo/test.txt", "ambo-cloud");
		HDFSOprator.getFileBlocks("test.txt");
	}

}
