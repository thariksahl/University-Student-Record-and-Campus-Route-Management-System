import java.util.ArrayDeque;
import java.util.Deque;

public class ActionStack {

    private final Deque<String> stack;

    public ActionStack() {
        stack = new ArrayDeque<>();
    }

    public void push(String action) {
        if (action == null || action.isBlank()) {
            return;
        }

        stack.push(action);
    }

    public String pop() {
        if (stack.isEmpty()) {
            return null;
        }

        return stack.pop();
    }

    public String peek() {
        if (stack.isEmpty()) {
            return null;
        }

        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public void display() {
        if (stack.isEmpty()) {
            System.out.println("No recent actions.");
            return;
        }

        System.out.println("\n===== RECENT ACTIONS =====");

        int number = 1;

        for (String action : stack) {
            System.out.println(number + ". " + action);
            number++;
        }

        System.out.println("==========================");
    }
}