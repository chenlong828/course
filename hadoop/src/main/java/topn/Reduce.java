package topn;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * User: ChenLong
 * Created Date: 9/18/13 6:50 下午
 * Description:
 */
public class Reduce extends Reducer<Text, IntWritable, Text, IntWritable>
{
    public void reduce(Text key, Iterator<IntWritable> values,Context context) throws IOException, InterruptedException {
        int sum = 0;
        while (values.hasNext())
        {
            sum += values.next().get();
        }
        context.write(key, new IntWritable(sum));
    }
}
