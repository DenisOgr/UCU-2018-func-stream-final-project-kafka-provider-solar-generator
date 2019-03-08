package ua.ucu.edu

import java.util

import org.apache.kafka.common.config.ConfigDef
import org.apache.kafka.connect.connector.Task
import org.apache.kafka.connect.source.SourceConnector

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

class SolarPanelConnector  extends SourceConnector{

  private var props: Map[String, String] = _

  override def version(): String = "v0.1"

  override def start(props: util.Map[String, String]): Unit = {
    this.props =  config().parse(props).map(x => (x._1, x._2.toString())).toMap
  }

  override def taskClass(): Class[_ <: Task] = classOf[SolarPanelTask]

  override def taskConfigs(maxTasks: Int): util.List[util.Map[String, String]] = {
    // always return single config, twitter stream api only allows one connection.
    List(this.props.asJava).asJava
  }

  override def stop(): Unit = {}

  override def config(): ConfigDef = {
    val config = new ConfigDef()
    config.define("PanelUIDs", ConfigDef.Type.LIST, ConfigDef.Importance.HIGH, "Panel UIDs")
    config.define("KafkaTopic", ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, "Topic for weather data")
  }
}