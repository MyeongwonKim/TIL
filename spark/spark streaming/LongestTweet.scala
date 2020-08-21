package com.sundogsoftware.sparkstreaming

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.StreamingContext._
import Utilities._
import java.util.concurrent._
import java.util.concurrent.atomic._
import scala.math.max

// ���� �� Ʈ���� ���̸� ���ϴ� ���α׷� 
object LongestTweet {
  
  def main(args: Array[String]) {

    setupTwitter()
    
    val ssc = new StreamingContext("local[*]", "AverageTweetLength", Seconds(1))
    
    setupLogging()

    val tweets = TwitterUtils.createStream(ssc, None)
    
    val statuses = tweets.map(status => status.getText())
    
    val lengths = statuses.map(status => status.length())
    
    // longestTweet ���� ����
    var LongestTweet = new AtomicLong(0)
    
    // ������ tweet�� ���� ���̰� �� ���� longestTweet�� setting�ϰ� �ִ밪�� ���
    lengths.foreachRDD((rdd, time) => {
      
      LongestTweet.set(rdd.reduce((x,y) => max(x,y) ))
        
        println("LongestTweetLength: " + LongestTweet.get())
      })
    
    var LongestTweetText: String = ""
    var num = 0
    
    // ���� �� tweet�� ���� ����ϱ�
    statuses.foreachRDD((rdd, time) => {
      
      LongestTweetText = rdd.reduce((x,y) => 
        if(x.length() >= y.length()) x
        else y
      )
      
      println("LongestTweetText: " + LongestTweetText)
    })
    
    ssc.checkpoint("C:/checkpoint/")
    ssc.start()
    ssc.awaitTermination()
  }  
}
