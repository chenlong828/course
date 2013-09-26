package topn;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * User: ChenLong
 * Created Date: 9/18/13 6:50 下午
 * Description:
 */
public class Map extends Mapper<LongWritable, Text, Text, IntWritable>
{
    private Text word = new Text();

    long temp = Long.MIN_VALUE;
    long[] topN;


    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        String[] tokens = line.split("\t");
        ArrayList<WordPair> wordPairs = new ArrayList<WordPair>();

    }
}
