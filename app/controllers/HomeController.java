package controllers;

import akka.*;
import akka.actor.*;

import javax.inject.*;

import play.*;
import play.mvc.*;

import views.html.*;

import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;
import scala.concurrent.ExecutionContextExecutor;

import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final ActorSystem actorSystem;
    private final ExecutionContextExecutor exec;

    private final Logger.ALogger logger = Logger.of(this.getClass());

    /**
     * @param actorSystem We need the {@link ActorSystem}'s
     * {@link Scheduler} to run code after a delay.
     * @param exec We need a Java {@link Executor} to apply the result
     * of the {@link CompletableFuture} and a Scala
     * {@link ExecutionContext} so we can use the Akka {@link Scheduler}.
     * An {@link ExecutionContextExecutor} implements both interfaces.
     */
    @Inject
    public HomeController(ActorSystem actorSystem, ExecutionContextExecutor exec) {
        this.actorSystem = actorSystem;
        this.exec = exec;

        ActorSystem system = ActorSystem.create("my-system");
        ActorRef helloActor = system.actorOf(Props.create(HelloActor.class), "hello-actor");
        ActorRef cleanupActor = system.actorOf(Props.create(CleanupRunner.class), "cleanup-actor");

        QuartzSchedulerExtension scheduler = QuartzSchedulerExtension.get(system);
        java.util.Date next;
        next = scheduler.schedule("HELLO_ACTOR", helloActor, (Object)"Hello!");
        System.out.println("next scheduled at " + next.toString());
        next = scheduler.schedule("CLEANUP_ACTOR", cleanupActor, (Object)"Clean!");
        System.out.println("next scheduled at " + next.toString());
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

}
