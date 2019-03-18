package hello.models;

public abstract class AbstractModel<T> {
    public abstract T fill(T instance) throws java.rmi.RemoteException;
}