# Hibernate Reactive Transactions with Reactive REST Client

Example repo for micronaut Hibernate Reactive Transactions with Reactive REST Client issue.

When using Hibernate Reactive with vertx-mysql-client and have service method annotated with transaction, vert.x thread is used.
If there are no other async calls, except to Hibernate Reactive repository, all works good.

But, if in this execution chane, we need to call Reactive REST client, this call will switch to different thread (not vert.x), and exception will be thrown on callback, as new session will be created instead of re-using the previously existing session residing in context.

```
HR000069: Detected use of the reactive Session from a different Thread than the one which was used to open the reactive Session - this suggests an invalid integration; original thread [52]: 'vert.x-eventloop-thread-1' current Thread [55]: 'vert.x-eventloop-thread-2
```
