package nl.knaw.dans.ersy.textmining.clustering;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.mahout.clustering.classify.WeightedVectorWritable;
import org.apache.mahout.math.NamedVector;

public class ClusterOutput {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
	        BufferedWriter bw;
	        Configuration conf = new Configuration();
	        FileSystem fs = FileSystem.get(conf);
	        File pointsFolder = new File("/Users/akmi/tmp/1resuptiontoken/outputdata-from-easy/tokenized-documents");
	        File files[] = pointsFolder.listFiles();
	        bw = new BufferedWriter(new FileWriter(new File("/Users/akmi/tmp/1resuptiontoken/eko.txt")));
	        HashMap<String, Integer> clusterIds;
	        clusterIds = new HashMap<String, Integer>(5000);
	        for(File file:files){
	            if(file.getName().indexOf("part-m")<0)
	                continue;
	            SequenceFile.Reader reader = new SequenceFile.Reader(fs,  new Path(file.getAbsolutePath()), conf);
	            IntWritable key = new IntWritable();
	            WeightedVectorWritable value = new WeightedVectorWritable();
	            while (reader.next(key, value)) {
	                NamedVector vector = (NamedVector) value.getVector();
	                String vectorName = vector.getName();
	                bw.write(vectorName + "\t" + key.toString()+"\n");
	                if(clusterIds.containsKey(key.toString())){
	                    clusterIds.put(key.toString(), clusterIds.get(key.toString())+1);
	                }
	                else
	                    clusterIds.put(key.toString(), 1);
	            }
	            bw.flush();
	            reader.close(); 
	        }
	        bw.flush();
	        bw.close();
	        bw = new BufferedWriter(new FileWriter(new File("/Users/akmi/tmp/1resuptiontoken/eko2.txt")));
	        Set<String> keys=clusterIds.keySet();
	        for(String key:keys){
	            bw.write(key+" "+clusterIds.get(key)+"\n");
	        }
	        bw.flush();
	        bw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	}

}