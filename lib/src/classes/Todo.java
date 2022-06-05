package classes;

// Observer Pattern
public interface Todo {
    void subscribe(Observer subTodo);
    void unsubscribe(Observer subTodo);
    void notifySubTodo(String msg);
}