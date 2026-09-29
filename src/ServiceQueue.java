import java.util.LinkedList;
import java.util.Queue;

public class ServiceQueue {

    private final Queue<ServiceRequest> queue;

    public ServiceQueue() {
        queue = new LinkedList<>();
    }

    public boolean addRequest(ServiceRequest request) {
        if (request == null) {
            return false;
        }

        return queue.offer(request);
    }

    public ServiceRequest processNextRequest() {
        return queue.poll();
    }

    public ServiceRequest peekNextRequest() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void displayRequests() {
        if (queue.isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }

        System.out.println("\n===== SERVICE REQUEST QUEUE =====");

        for (ServiceRequest request : queue) {
            System.out.println(request);
        }

        System.out.println("=================================");
    }
}