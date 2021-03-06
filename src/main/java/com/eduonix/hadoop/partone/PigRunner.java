package com.eduonix.hadoop.partone;

import com.eduonix.hadoop.partone.pig.PigETL;
import org.apache.pig.ExecType;

/**
 * Created by ubu on 21.07.15.
 */
public class PigRunner {


    private static final boolean  isLocal = false;

    public static void main(String[] args) {


       PigETL pigETL = null;

        try {
            pigETL = new PigETL(isLocal, ExecType.LOCAL);

            // loads data into hdfs use this first
              pigETL.loadData();
            // loads data into mongo use this second
         //     pigETL.transformData();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
