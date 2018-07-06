package akka;

import akka.actor.UntypedActor;
import play.Logger;

/**
 * Created by Slawomir Leski on 06-07-2018.
 */
public class CleanupRunner extends UntypedActor {

	public static final String NAME = "CLEANUP_ACTOR";

	private static final Logger.ALogger LOG = Logger.of(CleanupRunner.class);

	public CleanupRunner() {
		LOG.info("Create CleanupRunner!");
	}

	@Override
	public void onReceive(Object message) {
		LOG.info("----------- >Running cleanup of temporary files <-------------------");
	}
}
