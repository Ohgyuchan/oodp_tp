package classes;

// Observer Pattern
public interface Todo {
    void subscribe(SubTodo subtodo);
    void unsubscribe(SubTodo subtodo);
    void notifySubtodo(String msg);
}