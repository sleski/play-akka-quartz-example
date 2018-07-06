package akka;

import akka.actor.AbstractLoggingActor;
import play.Logger;

import javax.inject.Inject;

/**
 * Created by Slawomir Leski on 05-07-2018.
 */
public class HelloActor extends AbstractLoggingActor {

	private static final Logger.ALogger LOG = Logger.of(HelloActor.class);

	public final static String NAME = "HELLO_ACTOR";

	@Inject
	public HelloActor() {
		LOG.info("-----------------------------------");
		LOG.info("HelloActor was created");
		LOG.info("-----------------------------------");

		receiveBuilder().match(String.class, s -> {
			log().info("=============================" + s);
		}).build();
	}

	@Override
	public Receive createReceive() {
		System.out.println("------------------------");
		LOG.info("--------createReceive()-------");
		log().info("createReceive ----------------------------------------");
		return receiveBuilder().match(String.class, s -> {
			log().info("Received String message: {}", s);
		}).matchAny(o -> log().info("received unknown message")).build();
	}

	@Override
	public void postStop() throws Exception {

		log().info("----------->POST STOP<----------");

		super.postStop();
	}



}
