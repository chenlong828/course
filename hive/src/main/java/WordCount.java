 /**
 * User: ChenLong
 * Created Date: 2/3/13 6:02 下午
 * Description:
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;

import java.io.IOException;


public class WordCount {


    public static void main(String[] args) throws IOException
    {
        JobConf conf = new JobConf(WordCount.class);
        conf.setJobName("wordcount");
 
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);
    }

}
