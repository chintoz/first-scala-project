package es.menasoft.mongodb {

  import com.mongodb.{DB, DBCollection, Mongo, DB => MongoDB}
  import scala.collection.convert.Wrappers._

  class MongoClient(val host: String, val port: Int) {
    require(host != null, "You have to provide a host name")
    private val underlying = new Mongo(host, port)
    def this() = this("127.0.0.1", 27017)

    def version = underlying.getVersion

    def dropDB(name:String) = underlying.dropDatabase(name)

    def createDB(name:String) = DB(underlying.getDB(name))

    def db(name:String) = DB(underlying.getDB(name))
  }

  object DB {
    def apply (underlying:MongoDB) = new DB(underlying)
  }

  class DB private (val underlying: MongoDB) {
    def  collectionNames = for (name <- new JSetWrapper(underlying.getCollectionNames)) yield name
  }

  class DBCollection (override val underlying: MongoDBCollection) extends ReadOnly

}
