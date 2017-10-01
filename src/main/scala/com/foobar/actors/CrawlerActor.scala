package com.foobar.actors

import java.net.URL

import akka.actor.Actor
import com.foobar.SiteMap
import com.foobar.models.{PageData, Visit}
import org.apache.commons.validator.routines.UrlValidator
import org.jsoup.Jsoup

import scala.collection.JavaConverters._

class CrawlerActor extends Actor {

  val urlValidator = new UrlValidator()

  override def receive: Receive = {

    case Visit(url: URL) =>
      println(s"url::${url.toString}")
        if (!SiteMap.tree.contains(url.toString) && url.toString.contains("wiprodigital.com") && !(url.toString.contains("facebook")) &&
            !(url.toString.contains("twitter")) && !(url.toString.contains("linkedin"))) {
          val response = Jsoup.connect(url.toString).ignoreContentType(true)
            .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1").execute()
          val contentType: String = response.contentType
          if (contentType.startsWith("text/html")) {
            val doc = response.parse()
            val title: String = doc.getElementsByTag("title").asScala.map(e => e.text()).head
            val descriptionTag = doc.getElementsByTag("meta").asScala.filter(e => e.attr("name") == "description")
            val description = if (descriptionTag.isEmpty) "" else descriptionTag.map(e => e.attr("content")).head
            val links: List[URL] = doc.getElementsByTag("a").asScala.map(e => e.attr("href")).filter(s =>
              urlValidator.isValid(s)).map(link => (new URL(link))).toList
            SiteMap.tree += Tuple2(url.toString, PageData(url, title, links))
            links.map(a => self ! Visit(a))
          }
        }
  }

  override def postStop(): Unit = {
    println("InsidePostStop:::")
  }
}

