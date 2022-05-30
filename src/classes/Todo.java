package classes;

// Observer Pattern
public interface Todo {
    void subscribe(SubTodo subTodo);
    void unsubscribe(SubTodo subTodo);
    void notifySubTodo(String msg);
}