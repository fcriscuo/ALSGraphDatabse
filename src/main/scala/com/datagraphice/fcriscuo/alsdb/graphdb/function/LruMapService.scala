package com.datagraphice.fcriscuo.alsdb.graphdb.function

import com.twitter.util.LruMap;
class LruMapService {
  val map = new LruMap[String, String](1000);

  def getMapValue (key: String) : String = {
    map.apply(key)
  }

}
