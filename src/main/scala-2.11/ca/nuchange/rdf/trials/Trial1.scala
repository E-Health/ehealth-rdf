package ca.nuchange.rdf.trials

/**
 * Created by beapen on 15-09-17.
 * Experimenting with Scala
 * I have setup a local fuseki 2.3 server with reasoning.
 */

import org.apache.jena.query.Query
import org.apache.jena.query.QueryExecution
import org.apache.jena.query.QueryExecutionFactory
import org.apache.jena.query.QueryFactory
import org.apache.jena.query.ResultSetFactory
import org.apache.jena.query.ResultSetFormatter
import org.apache.jena.query.ResultSetRewindable
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.util.FileManager
import org.apache.jena.query.DatasetAccessorFactory
import org.apache.jena.query.DatasetAccessor

object Trial1 {
  def main (args: Array[String]) {
    val serviceURL:String = "http://192.168.0.250:3030/data/get"
    val accessor:DatasetAccessor = DatasetAccessorFactory.createHTTP(serviceURL)
    val model:Model = accessor.getModel
    val queryString:String = "SELECT * { ?s ?p ?o }"
    val query:Query = QueryFactory.create(queryString)
    val qexec:QueryExecution = QueryExecutionFactory.create(query,model)
    try{
      val results:ResultSetRewindable = ResultSetFactory.makeRewindable(qexec.execSelect())
      println("---- XML ----")
      ResultSetFormatter.outputAsXML(System.out, results)
      results.reset()
      println("---- Text ----")
      ResultSetFormatter.out(System.out, results)
      results.reset()
    }finally {qexec.close()}



  }
}
