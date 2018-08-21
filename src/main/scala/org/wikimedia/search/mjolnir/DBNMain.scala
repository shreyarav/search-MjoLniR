package org.wikimedia.search.mjolnir

import com.recipegrace.biglibrary.electric.{ElectricJob, ElectricSession}

case class DBNInput(input:String, output:String)



object DBNMain extends ElectricJob[DBNInput]{
  override def execute(t: DBNInput)(implicit ec: ElectricSession): Unit = {



    val sparkSession = ec.getSparkSession

    val sessions = sparkSession.read.json(t.input)


    val sessionsDF = sessions.select("norm_query_id","norm_query_str").distinct()
    val urlRelevances = DBN.train(sessions, Map()).join(sessionsDF,"norm_query_id")
      .select("norm_query_str","hit_page_id","relevance")
        .withColumnRenamed("norm_query_str","query")
        .withColumnRenamed("hit_page_id","omsId")
        .withColumnRenamed("relevance","score")



    urlRelevances.show(10,false)



  }
}
