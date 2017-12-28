package es.menasoft.mongodb

import com.mongodb.{DBObject, DBCollection => MongoDBCollection}

trait ReadOnly {
  var underlying : MongoDBCollection

  def name = underlying.getName
  def fullName = underlying.getFullName
  def find(doc:DBObject) = underlying find doc
  def findOne(doc:DBObject) = underlying find doc
  def findOne = underlying findOne
  def getCount(doc: DBObject) = underlying getCount doc

}

trait Administrable extends ReadOnly {
  def drop : Unit = underlying drop
  def dropIndexes : Unit = underlying dropIndexes
}

trait Updatable extends ReadOnly {
  def -= (doc: DBObject) : Unit = underlying remove doc
  def += (doc: DBObject) : Unit = underlying save doc
}
