package com.superisong.GeneratePdf;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class hello{
    public static void main(String[] args) {
        String fileName="E:/工作/开发需求/中央图库/3.5版效果图3.14"+File.separator;
        File f=new File(fileName);
        print(f);
    }
    public static void print(File f){
        if(f!=null){
            if(f.isDirectory()){
            	System.out.println(f.getName());
                File[] fileArray=f.listFiles();
                if(fileArray!=null){
                	List<File> fileList=new ArrayList<File>();
                    for (int i = 0; i < fileArray.length; i++) {
                    	fileList.add(fileArray[i]);
                    }
                    Collections.sort(fileList,new Comparator<File>(){  
                        public int compare(File o1,File o2){
                        	String o1intStr=o1.getName().replaceAll("[^0-9]+", "");
                        	String o2intStr=o2.getName().replaceAll("[^0-9]+", "");
                        	if(!o1intStr.equals("") && !o2intStr.equals("")){
                        		Integer o1int=Integer.valueOf(o1intStr);
                        		Integer o2int=Integer.valueOf(o2intStr);
                        		return o1int.compareTo(o2int);
                        	}
                            return o1.compareTo(o2);  
                        }
                    }); 
                    for (File file : fileList) {
                    	//递归调用
                    	print(file);
                    }
                }
            }
            else{
                System.out.println(f);
            }
        }
    }
}
 