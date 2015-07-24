package com.eduonix.hadoop.partone.mapred;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.mapred.AvroKey;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by ubu on 19.07.15.
 */
public class SimpleArvoMapper  extends Mapper<AvroKey<GenericRecord>, NullWritable, Text, IntWritable> {


    @Override
    public void map(AvroKey<GenericRecord> key, NullWritable value, Context context) throws IOException, InterruptedException {
        CharSequence text = (CharSequence) key.datum().get("text");
        if (text == null) {
            text = "none";
        }
        context.write(new Text(text.toString()), new IntWritable(1));
    }

}
