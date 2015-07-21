package com.eduonix.hadoop.partone.mapred;

import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by ubu on 19.07.15.
 */
public class SimpleArvoReducer  extends Reducer<Text, IntWritable, AvroKey<CharSequence>, AvroValue<Integer>> {

    @Override
    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        context.write(new AvroKey<CharSequence>(key.toString()), new AvroValue<Integer>(sum));
    }


}
