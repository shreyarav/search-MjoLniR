package org.wikimedia.search.mjolnir

import com.recipegrace.biglibrary.electric.tests.ElectricJobTest

class DBMainTest extends ElectricJobTest {

  test("dbn main") {
    launch(DBNMain, DBNInput("/Users/sxr1pxy/Documents/spark-DBN/files/dbn_final_data.json", createTempPath()))
  }
}
