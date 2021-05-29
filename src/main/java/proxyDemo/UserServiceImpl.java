package proxyDemo;

public class UserServiceImpl implements IUserService {
    @Override
    public void add() {
        System.out.println("--------------------add----------------------");
    }

    @Override
    public void del() {
        System.out.println("--------------------delete----------------------");
    }
}
