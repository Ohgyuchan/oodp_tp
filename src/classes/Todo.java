package classes;

// Observer Pattern
public interface Todo {
    void subscribe(Subtodo subtodo);
    void unsubscribe(Subtodo subtodo);
    void notifySubtodo(String msg);
}