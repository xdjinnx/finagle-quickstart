import com.twitter.finagle.Service
import com.twitter.finagle.Http
import com.twitter.finagle.http.Request
import com.twitter.finagle.http.Response
import com.twitter.finagle.http.Status

import com.twitter.util.Await
import com.twitter.util.Future

fun main(args: Array<String>) {

    val service = object : Service<Request, Response>() {

        override fun apply(request: Request): Future<Response> {
            val res = Response.apply(request.version(), Status.Ok())
            return Future.value<Response>(res)
        }
    }

    val server = Http.serve(":8080", service)
    Await.ready(server)
}

