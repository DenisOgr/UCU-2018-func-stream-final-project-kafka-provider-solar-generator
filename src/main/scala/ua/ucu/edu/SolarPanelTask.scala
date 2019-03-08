package ua.ucu.edu

import java.time.Instant
import java.util
import java.util.Collections

import io.circe.generic.auto._
import io.circe.syntax._
import org.apache.kafka.connect.data.Schema
import org.apache.kafka.connect.source.{SourceRecord, SourceTask}

import scala.io.Source

class SolarPanelTask  extends SourceTask{

  private var topic: String = _
  private var panel_uids: List[String] = _

  override def start(props: util.Map[String, String]): Unit = {
    this.topic = props.get("KafkaTopic")
    this.panel_uids = props.get("PanelUIDs").split(", ").toList
    println(this.topic)
    println(this.panel_uids)
  }

  override def poll(): util.List[SourceRecord] = {
    val sourcePartitionKey = Collections.singletonMap("Partition", 0)

    val records = new util.ArrayList[SourceRecord]
    for (panel_uid <- this.panel_uids) {
      println("Panel: "+ panel_uid.toString)

      records.add(new SourceRecord(
        sourcePartitionKey,
        Collections.singletonMap("Offset", Instant.now().toEpochMilli()),
        this.topic,
        Schema.STRING_SCHEMA,
        panel_uid,
        Schema.STRING_SCHEMA,
        SolarPanelGenerator.generate(panel_uid).asJson.noSpaces
      ))

      println(SolarPanelGenerator.generate(panel_uid).asJson.noSpaces)
    }
    records
  }

  override def stop(): Unit = {}

  override def version(): String = "v0.1"
}
