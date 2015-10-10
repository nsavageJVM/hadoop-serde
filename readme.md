# hadoop-serde

## Demo code for mongo-hadoop connector via pig

In com.eduonix.hadoop.partone.PigRunner  
  
  * local testing and development set isLocal = false  
  * cluster set isLocal = true    
  
  

* 1. in /python_scripts run python2.7 stream-text.py > raw_data.txt with your twitter api credentials  

* 2. in com.eduonix.hadoop.partone.PigRunner, first run with `pigETL.loadData()`, then run with `pigETL.transformData()`  

* 3.  build with maven profile `mvn clean package -Ppig` then copy ubu-pig.jar to local directory for hadoop job  

* 4.  assumes mongo is listening on default port 27017, db = test, collection = tweets  



