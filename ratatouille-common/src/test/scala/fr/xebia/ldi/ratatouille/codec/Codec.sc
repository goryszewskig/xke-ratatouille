
import cats.Applicative
import fr.xebia.ldi.ratatouille.codec._
import scodec.bits.BitVector
import scodec.codecs._
import cats.implicits._
import scodec.codecs.implicits._
import scodec.{Attempt, Codec, DecodeResult}

implicit val attemptApplicative: Applicative[Attempt] = new Applicative[Attempt]{
  override def pure[A](x: A): Attempt[A] = Attempt.successful(x)
  override def ap[A, B](ff: Attempt[A => B])(fa: Attempt[A]): Attempt[B] = for { a <- fa; f <- ff } yield f(a)
}

// TODO: [1] implicit lazy val codec: Codec[Either[Meat, Vector[Pastry]]] = ...

case class Breakfast1(lang: Lang,
                      drink: Drink,
                      fruit: Fruit,
                      dishes: Vector[Pastry] = Vector.empty)

// TODO: [1] Breakfast2
// TODO: [1] Explain Meat

val frame1: Array[Byte] = Array(0x33, 0xd4, 0xfc, 0x00, 0x00, 0x00, 0x01, 0xa5).map(_.toByte)

val frame2: Array[Byte] = Array(0x44, 0xd2, 0xfe, 0x10, 0x02, 0x03, 0x01).map(_.toByte)

Codec.decode[Breakfast1](BitVector(frame1))

Codec.decode[Breakfast1](BitVector(frame2))