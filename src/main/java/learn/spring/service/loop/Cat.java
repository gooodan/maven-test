package learn.spring.service.loop;

public class Cat {
    private Dog dog;

//    public Cat(Dog dog) {
//        this.dog = dog;
//    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Dog getDog() {
        return dog;
    }
}
