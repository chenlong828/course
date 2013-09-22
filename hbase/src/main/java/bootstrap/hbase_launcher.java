package bootstrap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;

import java.io.IOException;

/**
 * User: ChenLong
 * Created Date: 9/22/13 6:38 下午
 * Description:
 */
public class hbase_launcher {
    public static void main(String[] args) throws IOException {
        Configuration myConf = new Configuration();
        myConf.set("hbase.master","101.227.253.67:44801");
        myConf.set("hbase.zookeeper.property.clientPort", "2181");
        myConf.set("hbase.zookeeper.quorum", "101.227.253.67");
        HTableInterface usersTable = new HTable(myConf, "users");
        usersTable.close();

    }

}
