package fr.xebia.ldi.ratatouille

import fr.xebia.ldi.ratatouille.codec.Lunch
import fr.xebia.ldi.ratatouille.codec.Lunch.{LunchError, dishToString}
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler
import org.apache.kafka.streams.kstream.Printed
import org.apache.kafka.streams.scala.kstream.{Consumed, Produced}
import org.apache.kafka.streams.scala.{Serdes, StreamsBuilder}
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}
import purecsv.safe._

/**
  * Created by loicmdivad.
  */
object ExampleTwo extends App with Example {

  val config = Map(
    StreamsConfig.APPLICATION_ID_CONFIG -> "answer-two-lunch",
    StreamsConfig.BOOTSTRAP_SERVERS_CONFIG -> "localhost:9092",
    // TODO: [2] change the default handler
    //StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG -> classOf[LogAndContinueExceptionHandler]
  )

  val builder: StreamsBuilder = new StreamsBuilder()

  val meals = builder

    // TODO: [2] consume
    .stream[String, String]("exercise-lunch")(???)

    .flatMapValues { line =>

      CSVReader[Lunch]

        .readCSVFromString(line, ',')

        // TODO [2] flatten the try
    }

  // TODO: [2] branch

  // TODO: [2] log the errors

  meals
    .map((_, meal) => (dishToString.to(meal.get.`type`), meal.get.price))

    .print(Printed.toSysOut[String, Double])

  val streams: KafkaStreams = new KafkaStreams(builder.build(), config.toProperties)

  streams.cleanUp()

  streams.start()

  sys.ShutdownHookThread {
    streams.close()
    streams.cleanUp()
  }
}
