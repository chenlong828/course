package sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * User: ChenLong
 * Created Date: 9/18/13 6:50 下午
 * Description:
 */
public class Reduce extends MapReduceBase implements Reducer<IntWritable, Text, Text, IntWritable>
{
    public void reduce(IntWritable key, Iterator<Text> values, OutputCollector<Text, IntWritable> output,
                       Reporter reporter) throws IOException
    {
        while(values.hasNext())
        {
            output.collect(values.next(), key);
        }
    }
}
