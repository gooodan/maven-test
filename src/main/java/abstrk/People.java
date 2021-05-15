package abstrk;

public abstract class People {
    protected abstract void run();

    public void go() {
        People.this.run();
    }
}
