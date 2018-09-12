package akka;

import akka.actor.AbstractLoggingActor;

import javax.inject.Inject;

/**
 * Created by Slawomir Leski on 05-07-2018.
 */
public class HelloActor extends AbstractLoggingActor {

	public final static String NAME = "HELLO_ACTOR";

	@Inject
	public HelloActor() {

	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(String.class, s -> log().info("Received String message: {}", s)).matchAny(o -> log().info("received unknown message")).build();
	}

}
