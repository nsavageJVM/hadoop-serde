## hadoop-serde

In com.eduonix.hadoop.partone.PigRunner  
  * local testing and development set isLocal = false  
  * cluster set isLocal = true    
  
  

1. in /python_scripts run python2.7 stream-text.py > raw_data.txt with your twitter api credentials  

2. in com.eduonix.hadoop.partone.PigRunner, first run with pigETL.loadData(), then run with pigETL.transformData()  

3.  build with maven profile mvn clean package -Ppig copy ubu-pig.jar to local directory for hadoop job  

4.  assumes mongo is listening on default port 27017, db = test, collection = tweets  





currently struggeling with exceptions when running ArvoJob on Hortonworks and Cloudera VM's
see   my stackoverflow question for this
http://stackoverflow.com/questions/31519705/how-to-run-class-org-apache-avro-mapred-avrojob-on-hadoop-cluster-without-throwi    
    
while waiting for some help looking at hadoop-mongo bridge    
https://github.com/rjurney/mongo-hadoop    
and pig-arvo-mongo    
https://github.com/rjurney/enron-node-mongo    

so far working out however running mongo on cento 6 java driver seems flakey
