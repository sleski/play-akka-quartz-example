package modules;

import akka.HelloActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension;
import org.apache.commons.lang3.RandomStringUtils;
import play.Logger;
import play.api.Configuration;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Slawomir Leski on 05-07-2018.
 */
@Singleton
public class QuartzSchedulerHelper {

	private static final Logger.ALogger LOG = Logger.of(QuartzSchedulerHelper.class);

	@Inject
	public QuartzSchedulerHelper(ActorSystem registry, QuartzSchedulerExtension quartzSchedulerExtension, Configuration configuration) {

		List<String> actors = Arrays.asList(HelloActor.NAME);
		for (String name : actors) {
			ActorRef actor = registry.actorFor(name);
			schedule(quartzSchedulerExtension, actor, name, RandomStringUtils.randomAlphabetic(10));
		}

	}

	private void schedule(QuartzSchedulerExtension quartzExtension, ActorRef actor, String actorName, String message) {
		Date schedule = quartzExtension.schedule(actorName, actor, message);
		LOG.info("Job {} scheduled at {}.", actorName, schedule);
	}
}
