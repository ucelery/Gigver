package utils;

public interface IServerEvent<T> {
    void OnComplete(T result);
    void OnFailure(String errorMessage);
}
