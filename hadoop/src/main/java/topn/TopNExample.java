package topn;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import wordcount.*;
import wordcount.Reduce;

import java.io.IOException;

/**
 * User: ChenLong
 * Created Date: 9/18/13 6:49 下午
 * Description:
 */
public class TopNExample {
    public static void main(String[] args) throws IOException
    {
        JobConf conf = new JobConf(WordCount.class);
        conf.setJobName("topn");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setMapperClass(Map.class);
        conf.setCombinerClass(Reduce.class);
        conf.setReducerClass(Reduce.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        JobClient.runJob(conf);
    }
}
