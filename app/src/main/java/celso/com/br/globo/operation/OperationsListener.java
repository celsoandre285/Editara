package celso.com.br.globo.operation;

public abstract class OperationsListener<T> {

    public abstract void OnSuccess(T result);
    public abstract void OnFailure(int  errorCode,  String errorMessage);

}
