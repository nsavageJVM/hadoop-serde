package com.eduonix.hadoop.partone;

import com.eduonix.hadoop.partone.pig.PigETL;
import org.apache.pig.ExecType;

/**
 * Created by ubu on 21.07.15.
 */
public class PigRunner {


    private static final boolean  isLocal = false;

    public static void main(String[] args) {


       PigETL pigResult = null;

        try {
              pigResult = new PigETL(isLocal, ExecType.LOCAL);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
