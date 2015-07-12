package com.eduonix.hadoop.partone;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.hadoop.fs.Path;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ubu on 11.07.15.
 */
public class SerDe {


    public void serilaiseRawTwitterDataToArvo( InputStream is, Path serdeOut) {
        System.out.println("serdeOut.toString() "+serdeOut.toString());
        File file = new File(serdeOut.toString());
        System.out.println("file.getPath()  "+file.getPath());
        DatumWriter<tweets_ser> datumWriter = new SpecificDatumWriter<>(tweets_ser.class);
        DataFileWriter<tweets_ser> fileWriter = new DataFileWriter<>(datumWriter);

        if(is == null) {
            System.out.println("SerDe inputstream is null " );
        }


            byte[] buffer = new byte[4096];
            int n;
            try {

                while ((n = is.read(buffer)) > 0) {

                    String tmp = new String(buffer, "UTF-8");
                    if (tmp.contains("delete")) {
                        continue;
                    }
                    tweets_ser tweet = new tweets_ser();
                    tweet.setText(tmp);

                    fileWriter.create(tweet.getSchema(), file);
                    fileWriter.append(tweet);
                    fileWriter.close();

                }


            } catch (IOException ie) {
                System.out.println("Error parsing or writing file.");
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    public void deserilaiseCompressedTwitterData(Path serdeOut) {

        File file = new File(serdeOut.toString());

        DatumReader<tweets_ser> datumReader = new SpecificDatumReader<>(tweets_ser.class);
        DataFileReader<tweets_ser> fileReader = null;
        try {
            fileReader = new DataFileReader<>(file, datumReader);
            while (fileReader.hasNext()) {
                // go to hdfs here
                tweets_ser tweet = fileReader.next( );
                System.out.println(tweet.getText());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
