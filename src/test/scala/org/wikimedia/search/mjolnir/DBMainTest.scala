package org.wikimedia.search.mjolnir

import com.recipegrace.biglibrary.electric.tests.ElectricJobTest

class DBMainTest extends ElectricJobTest {

  test("dbn main") {
    launch(DBNMain, DBNInput("/tmp/dbnfinal.txt", createTempPath()))
  }
}
