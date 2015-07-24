package com.eduonix.hadoop.partone;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by ubu on 09.07.15.
 */
public class SerdeRunner {

    private static final String ser_file = "ser_twitter.avro";
    private static final String raw_data = "raw_data.txt";

    private static final String projectRootPath = System.getProperty("user.dir");

    private static Path serdeOut;
    private static Path inputFile;
    private static InputStream is;

    public static void main(String[] args) {


        serdeOut = new Path(projectRootPath, ser_file);
        inputFile = new Path(projectRootPath, raw_data);
        System.out.println("inputFile "+inputFile.toString());
        Configuration config = new Configuration();
        FileSystem hdfs = null;
        try {
            hdfs = FileSystem.get(config);
            is = hdfs.open(inputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        SerDe serilaiser_deserilaiser = new SerDe();
        /**
         * serilaise takes 73k to 4.3k
         */
        serilaiser_deserilaiser.serilaiseRawTwitterDataToArvo( is, serdeOut);

        /**
         * test serialisation / deserialisation
         */
        serilaiser_deserilaiser.deserilaiseCompressedTwitterData(serdeOut);






    }

}