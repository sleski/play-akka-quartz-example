package modules;

import akka.HelloActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension;
import play.Logger;
import play.api.Configuration;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Slawomir Leski on 05-07-2018.
 */
@Singleton
public class QuartzSchedulerHelper {

	private static final Logger.ALogger LOG = Logger.of(QuartzSchedulerHelper.class);

	static String SCHEDULED_JOB_CONFIG_PATH = "akka.quartz.schedules";

	@Inject
	public QuartzSchedulerHelper(ActorSystem registry, QuartzSchedulerExtension quartzSchedulerExtension, Configuration configuration) {

		ActorRef actor = registry.actorFor(HelloActor.NAME);
		quartzSchedulerExtension.schedule("HELLO_ACTOR", actor, "lala");
//		quartzSchedulerExtension.schedule(jobName, jobDispatcher, sftpUploadJobConfig);
		LOG.info("Job {} scheduled.", HelloActor.NAME);
	}
}
