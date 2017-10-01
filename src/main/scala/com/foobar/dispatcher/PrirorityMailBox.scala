package com.foobar.dispatcher

import akka.actor.{ActorSystem, PoisonPill}
import akka.dispatch.PriorityGenerator
import akka.dispatch.UnboundedStablePriorityMailbox
import com.foobar.models.Visit
import com.typesafe.config.Config

// We inherit, in this case, from UnboundedStablePriorityMailbox
// and seed it with the priority generator
class PrirorityMailBox(settings: ActorSystem.Settings, config: Config)
  extends UnboundedStablePriorityMailbox(
    // Create a new PriorityGenerator, lower prio means more important
    PriorityGenerator {
      case PoisonPill => 3
      case a: Visit => 0
    })