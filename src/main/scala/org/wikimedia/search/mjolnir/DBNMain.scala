package org.wikimedia.search.mjolnir

import com.recipegrace.biglibrary.electric.{ElectricJob, ElectricSession}
import org.apache.spark.sql.Dataset

import scala.io.Source

case class DBNInput(input:String, output:String)



object DBNMain extends ElectricJob[DBNInput]{
  override def execute(t: DBNInput)(implicit ec: ElectricSession): Unit = {


//    import scala.reflect.ClassTag
//    implicit def kryoEncoder[A](implicit ct: ClassTag[A]) =
//      org.apache.spark.sql.Encoders.kryo[A](ct)



    val sparkSession = ec.getSparkSession
    import

    val file = Source.fromFile(t.input)
    val ir = new InputReader(1, 20, true)
    val sessions = ir.read(file.getLines())
    val config = ir.config(0.5D, 1)
    val model = new DbnModel(0.9D, config)
    val urlRelevances = model.train(sessions)
    val relevances = ir.toRelevances(urlRelevances)


   /* println("Sessions:")
    sessions.foreach(println)
    println("relevances:")
    relevances.foreach(println)*/



    //val relevanceRDD = sparkSession.sparkContext.parallelize(relevances)

    val relevanceDF: Dataset[RelevanceResult] = sparkSession.createDataset(relevances)

    relevanceDF.show(10,false)
  }
}
