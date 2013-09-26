package topn;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Arrays;

/**
 * User: ChenLong
 * Created Date: 9/18/13 6:50 下午
 * Description:
 */
public class Reduce extends Reducer<IntWritable, Text, Text, IntWritable>
{
    private WordPair[] topN;

    protected void setup(Context context) {
        topN = new WordPair[5];
        for(int i = 0;i < topN.length;i++)
        {
            topN[i] = new WordPair(" ", 0);
        }
    }

    protected void reduce(IntWritable key, Iterable<Text> values,Context context)
            throws IOException, InterruptedException {
        for(Text text: values)
        {
            String line = text.toString();
            String[] tokens = line.split("\t");

            int count = Integer.parseInt(tokens[1]);
            if (count > topN[0].getCount())
            {
                topN[0] = new WordPair(tokens[0], count);
                Arrays.sort(topN);
            }
        }
    }

    protected void cleanup(Context context) throws IOException, InterruptedException {
        for( int i = 0; i < topN.length; i++) {

            context.write(new Text(topN[i].toString()), new IntWritable(topN[i].getCount()));

        }
    }
}