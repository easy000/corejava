package com.xiong.core.utils;
import java.io.File;
/**
 * 

 *�Զ�ɨ���ļ����µ��ļ�
 *��;���Զ�ɨ��ű�����·�������Ҫִ�е�sql�������ݿ⣻
 */
public class OutSql { 
 /**
  * @param args
  */
 public static void main(String[] args) {
   // TODO Auto-generated method stub
   String path ="C:/Users/Administrator/Desktop/�Ա�/whlib";
   GetSql(path);
 }
 /*
  * �ݹ���ò���ָ���ļ����������ļ�
  */
 public static  String GetSql(String path){
  File rootDir = new File(path);
   if(!rootDir.isDirectory()){
    System.out.println("�ļ���"+rootDir.getAbsolutePath());
   }else{
    String[] fileList =  rootDir.list();
    for (int i = 0; i < fileList.length; i++) {
     path = rootDir.getAbsolutePath()+"\\"+fileList[i];
     GetSql(path);      
      } 
   }    
  return null;    
 }
}