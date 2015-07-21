package com.eduonix.hadoop.partone.pig;

import org.apache.pig.ExecType;
import org.apache.pig.PigServer;

import java.io.IOException;

/**
 * https://cwiki.apache.org/confluence/display/PIG/AvroStorage
 * http://hortonworks.com/blog/pig-as-connector-part-one-pig-mongodb-and-node-js/
 *
 */
public class PigETL {

    private PigServer pigServer;

    private static  String projectRootPath = System.getProperty("user.dir");




    public PigETL( boolean isLocal, ExecType ex) throws Exception{

        if (!isLocal) {
            projectRootPath = String.format("%s/%s", projectRootPath, "hadoop-serde" );
        }


        String jarPathUDF =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "pigmodule.jar");
        String jarPathPiggy =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "piggybank-0.12.0.jar");
        String jarPathArvo =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "avro-1.5.3.jar");
        String jarPathJson =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "json-simple-1.1.jar");
        String jarPathMon =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "mongo-java-driver-3.0.0.jar");
        String jarPathMonH =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "mongo-hadoop-core-1.4.1-SNAPSHOT.jar");
        String jarPathMonP =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "mongo-hadoop-pig-1.4.1-SNAPSHOT.jar");
        // run pig in local mode
        pigServer = new PigServer(ex);
        try {
            pigServer.registerJar(jarPathUDF);
            pigServer.registerJar(jarPathPiggy);
            pigServer.registerJar(jarPathArvo);
            pigServer.registerJar(jarPathJson);
            pigServer.registerJar(jarPathMon);
            pigServer.registerJar(jarPathMonH);
            pigServer.registerJar(jarPathMonP);

        } catch (IOException e) {
            e.printStackTrace();
        }

        pigServer.registerQuery("avros = load 'ser_twitter.avro' using  org.apache.pig.piggybank.storage.avro.AvroStorage();");

        pigServer.registerQuery("avros = store avros into 'mongodb://localhost:27017/twitts' using com.mongodb.hadoop.pig.MongoStorage('', '');");

    }





}
