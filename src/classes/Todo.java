package classes;

// Observer Pattern
public interface Todo {
    void subscribe(Observer observers);
    void unsubscribe(Observer observers);
    void notifySubTodo(String msg);
}