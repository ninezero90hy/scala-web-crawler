package com.foobar

import java.net.URL

import akka.actor.{ActorSystem, PoisonPill, Props}
import akka.util.Timeout
import com.foobar.actors.CrawlerActor
import com.foobar.models.{PageData, Visit}

import scala.concurrent.duration._
import scala.language.postfixOps

object Boot extends App {
  val url = "http://wiprodigital.com/"

  implicit val system = ActorSystem("AskTestSystem")
  val myActor = system.actorOf(Props[CrawlerActor].withDispatcher(
    "prio-dispatcher"), name = "crawlerActor")
  myActor ! Visit(new URL(url))
  Thread.sleep(5000)
  myActor ! PoisonPill
}

object SiteMap {
  var tree = scala.collection.mutable.Map[String, PageData]()
}
