import fr.xebia.ldi.ratatouille.codec._
implicit val appAttempt = Worksheet.AttemptA()
implicit val bf2Codec = Worksheet.CodecB()




import cats.Applicative
import scodec.bits.BitVector
import scodec.{Attempt, Codec}
import scodec.codecs._
import scodec.codecs.implicits._





case class Breakfast1(lang: Lang,
                      drink: Drink,
                      fruit: Fruit,
                      dishes: Vector[Pastry] = Vector.empty)

// TODO: [1] add Breakfast2

val frame1: Array[Byte] = Array(0x33, 0xd4, 0xfc, 0x00, 0x00, 0x00, 0x01, 0xa5).map(_.toByte)

val frame2: Array[Byte] = Array(0x44, 0xd2, 0xfe, 0x10, 0x02, 0x03, 0x01).map(_.toByte)

// TODO: [1] add Breakfast1

Codec.decode[Breakfast1](BitVector(frame1))

Codec.decode[Breakfast1](BitVector(frame2))

