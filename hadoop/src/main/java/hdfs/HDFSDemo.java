package hdfs;

import java.io.IOException;

public class HDFSDemo {

    private static String  USER_FILE = "/user/ambo/test.txt";

	/**
	 * @param args
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException {
		HDFSOprator.listDatanodes();

		if (HDFSOprator.isExist(USER_FILE))
        {
            System.out.println(HDFSOprator.readFile(USER_FILE));
            HDFSOprator.deleteFile(USER_FILE);
        }
		HDFSOprator.createFile(USER_FILE, "ambo-cloud");
		HDFSOprator.getFileBlocks(USER_FILE);
	}

}
