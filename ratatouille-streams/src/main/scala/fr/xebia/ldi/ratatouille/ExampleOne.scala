package fr.xebia.ldi.ratatouille

import fr.xebia.ldi.ratatouille.codec.Breakfast
import fr.xebia.ldi.ratatouille.serde.BreakfastSerde
import org.apache.kafka.common.utils.Bytes
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler
import org.apache.kafka.streams.kstream.Printed
import org.apache.kafka.streams.scala.kstream.Consumed
import org.apache.kafka.streams.scala.{Serdes, StreamsBuilder}
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}


/**
  * Created by loicmdivad.
  */
object ExampleOne extends App with Example {

  val config = Map(
    StreamsConfig.APPLICATION_ID_CONFIG -> "answer-one-breakfast",
    StreamsConfig.BOOTSTRAP_SERVERS_CONFIG -> "localhost:9092",
    // TODO: [1] add an ExceptionHandler
  )

  val consumed: Consumed[Bytes, Breakfast] = Consumed.`with`(Serdes.Bytes, BreakfastSerde.Serde.breakfast)

  val builder: StreamsBuilder = new StreamsBuilder()

  builder

    // TODO: [1] consume
    .stream[Nothing, Nothing]("exercise-breakfast")(???)

    .mapValues { meal =>

      //s"lang: ${meal.lang},  drink: ${meal.drink},  fruits ${meal.fruit},  ${meal.dishes.right.get}"

    }

    // TODO: [1] print
    .print(???)


  val streams: KafkaStreams = new KafkaStreams(builder.build(), config.toProperties)

  streams.cleanUp()

  streams.start()

  sys.ShutdownHookThread {
    streams.close()
    streams.cleanUp()
  }
}