package learn.spring.service.loop;

public class Dog {
    private Cat cat;

//    public Dog(Cat cat) {
//        this.cat = cat;
//    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Cat getCat() {
        return cat;
    }
}
