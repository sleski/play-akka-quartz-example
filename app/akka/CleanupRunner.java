package akka;

import akka.actor.UntypedActor;
import play.Logger;

import javax.inject.Inject;

/**
 * Created by Slawomir Leski on 06-07-2018.
 */
public class CleanupRunner extends UntypedActor {

	public static final String NAME = "CLEANUP_ACTOR";

	private static final Logger.ALogger LOG = Logger.of(CleanupRunner.class);

	@Inject
	public CleanupRunner() {
		LOG.info("Create CleanupRunner!");
	}

	@Override
	public void onReceive(Object message) {
		System.out.println("----------- >Running cleanup of temporary files <-------------------");
		System.out.println("-------------------");
	}
}
