package com.eduonix.hadoop.partone.pig;

import org.apache.pig.ExecType;
import org.apache.pig.PigServer;

import java.io.IOException;

/**
 * https://cwiki.apache.org/confluence/display/PIG/AvroStorage
 * http://hortonworks.com/blog/pig-as-connector-part-one-pig-mongodb-and-node-js/
 *
 * http://chimera.labs.oreilly.com/books/1234000001811/ch05.html#filter
 *
 */
public class PigETL {

    private PigServer pigServer;

    private static  String projectRootPath = System.getProperty("user.dir");

    private static String regex = "'.*please.*'";
    private boolean isLocal;


    public PigETL( boolean isLocal, ExecType ex) throws Exception{

        this.isLocal = isLocal;

        if (isLocal) {
            projectRootPath = String.format("%s/%s", projectRootPath, "hadoop-serde" );
        }

//
        String jarPathUDF =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "pigmodule.jar");
        String jarPathJson =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "json-simple-1.1.jar");
        String jarPathMon =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "mongo-java-driver-3.0.0.jar");
        String jarPathMonH =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "mongo-hadoop-core-1.4.1-SNAPSHOT.jar");
        String jarPathMonP =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "mongo-hadoop-pig-1.4.1-SNAPSHOT.jar");
        String jarPathJm =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "jackson-mapper-asl-1.9.13.jar");
        String jarPathJc =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "jackson-core-asl-1.9.13.jar");

        String jarPathPiggy =  String.format("%s/%s/%s",projectRootPath, "pig-jars"  , "piggybank-0.14.0.jar");

        // run pig in local mode
            pigServer = new PigServer(ex);
       try {

            pigServer.registerJar(jarPathPiggy);
            pigServer.registerJar(jarPathUDF);
            pigServer.registerJar(jarPathJson);
            pigServer.registerJar(jarPathMon);
            pigServer.registerJar(jarPathMonH);
            pigServer.registerJar(jarPathMonP);
            pigServer.registerJar(jarPathJm);
            pigServer.registerJar(jarPathJc);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void loadData() throws IOException {

        pigServer.registerQuery("raw = load 'raw_data.txt' as (str:chararray);");

        pigServer.registerQuery("C = filter raw by str matches '.*please.*';" );


        if (!isLocal) {
            pigServer.registerQuery("STORE C into './pig_out/first_collection.json' USING JsonStorage();");
        } else {

            pigServer.registerQuery("STORE C into './pig_out/first_collection.json' USING JsonStorage();");

        }



    }



    public void transformData() throws IOException {

        //STORE data INTO 'mongodb://<username>:<password>@<host>:<port>/<database>.<collection>'
        //   USING com.mongodb.hadoop.pig.MongoInsertStorage('<id_alias>');

        pigServer.registerQuery("mongoJson =  LOAD './pig_out/first_collection.json' USING JsonLoader();");

        pigServer.registerQuery("STORE mongoJson INTO 'mongodb://localhost:27017/test.tweets' USING com.mongodb.hadoop.pig.MongoInsertStorage;");

    }




}
