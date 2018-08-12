package modules;

import akka.CleanupRunner;
import akka.HelloActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension;
import org.apache.commons.lang3.RandomStringUtils;
import play.Logger;
import play.api.Configuration;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Slawomir Leski on 05-07-2018.
 */
@Singleton
public class QuartzSchedulerHelper {

	private static final Logger.ALogger LOG = Logger.of(QuartzSchedulerHelper.class);

	@Inject
	public QuartzSchedulerHelper(ActorSystem registry, QuartzSchedulerExtension quartzSchedulerExtension, Configuration configuration) {

		List<String> actors = Arrays.asList(HelloActor.NAME, CleanupRunner.NAME);
		ActorSelection selection = registry.actorSelection("/user/*");
		for (String name : actors) {
			ActorRef actor = registry.actorFor(name);
			LOG.info("Actor Path = " + actor.path());
			schedule(quartzSchedulerExtension, actor, name, RandomStringUtils.randomAlphabetic(10));
		}

	}

	private void schedule(QuartzSchedulerExtension quartzExtension, ActorRef actor, String actorName, String message) {
		quartzExtension.schedule(actorName, actor, message);
		LOG.info("Job {} scheduled.", actorName);
	}
}
