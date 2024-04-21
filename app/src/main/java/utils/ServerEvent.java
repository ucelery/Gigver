package utils;

public interface ServerEvent<T> {
    void OnComplete(T result);
    void OnFailure(String errorMessage);
}
