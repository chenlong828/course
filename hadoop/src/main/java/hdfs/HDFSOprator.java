package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

import java.io.IOException;

public class HDFSOprator {

	private static DistributedFileSystem hdfs;

	static {
		Configuration conf = new Configuration();
        //conf.set("fs.default.name","hdfs://hadoop-pd:9000");
        //conf.set("dfs.replication", "1");
        //conf.set("dfs.permissions", "false");
		conf.reloadConfiguration();

		try {
			FileSystem fs = FileSystem.get(conf);
			hdfs = (DistributedFileSystem) fs;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void listDatanodes() throws IOException {
		System.out.println("*** List Datanodes ***");
		DatanodeInfo[] datanodes = hdfs.getDataNodeStats();
		for (DatanodeInfo datanode : datanodes) {
			String info = datanode.getDatanodeReport();
			System.out.println(info);
		}
	}

	public static boolean isExist(String file) throws IOException {
		Path path = new Path(file);
		return hdfs.exists(path);
	}

	public static void copyFromLocalFile(String srcFile, String dstFile)
			throws IOException {
		Path srcPath = new Path(srcFile);
		Path dstPath = new Path(dstFile);
		hdfs.copyFromLocalFile(srcPath, dstPath);
	}

	public static boolean mkdir(String dir) throws IOException {
		Path path = new Path(dir);
		return hdfs.mkdirs(path);
	}

	public static void createFile(String fileName, String content)
			throws IOException {
		Path path = new Path(fileName);
		FSDataOutputStream outputStream = hdfs.create(path);
//		byte[] buffer = content.getBytes();
//		outputStream.write(buffer, 0, buffer.length);
        outputStream.writeChars(content);
        outputStream.writeChars("\n");
		outputStream.close();
	}

    public static String readFile(String fileName) throws IOException {
        Path path = new Path(fileName);
        FSDataInputStream inputStream = hdfs.open(path);
        byte[] buf = new byte[10];
//        inputStream.read(buf,0,10);
        String fileContent = inputStream.readLine();
        inputStream.close();
        return fileContent;

    }

    public static void deleteFile(String fileName) throws IOException {
        Path path = new Path(fileName);
        hdfs.delete(path, true);
        System.out.println(fileName + " deleted");
    }

	public static void getFileBlocks(String fileName) throws IOException {
		Path path = new Path(fileName);
		FileStatus fileStatus = hdfs.getFileStatus(path);
		BlockLocation[] blocks = hdfs.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
		int length = blocks.length;
		for(int i = 0; i < length; i++) {
			System.out.println(blocks[i].toString());
		}
	}

}
