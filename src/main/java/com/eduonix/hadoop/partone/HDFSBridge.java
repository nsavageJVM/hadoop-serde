package com.eduonix.hadoop.partone;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;


/**
 * Created by ubu on 10.07.15.
 */
public class HDFSBridge {

    private static final String raw_file = "ser_twitter.avro";

    private  static Path serdeOut;

  //  serdeIn = new Path(input.toString(),"lines.avro" );


    public static void main(String[] args) {

        Configuration conf = new Configuration();
        Path path = new Path(raw_file);
        try {
            FileSystem fs = FileSystem.get(path.toUri(), conf);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
