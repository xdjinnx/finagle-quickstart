import com.twitter.finagle.Http;
import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.http.Request;
import com.twitter.finagle.http.Response;
import com.twitter.finagle.http.Status;
import com.twitter.util.Await;

import com.twitter.finagle.Service;
import com.twitter.util.Future;
import com.twitter.util.TimeoutException;

public class Main {

    public static void main(String[] args) {

        Service service = new Service<Request, Response>() {
            @Override
            public Future<Response> apply(Request request) {
                Response response = Response.apply(request.version(), Status.Ok());
                return Future.value(response);
            }
        };

        ListeningServer server = Http.serve(":8080", service);
        try {
            Await.ready(server);
        } catch (TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
