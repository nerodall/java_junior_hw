package lesson2.test;

public class TestRunnerDemo {
    public static void main(String[] args) {
        TestRunner.run(TestRunnerDemo.class);
    }

    @Test
    void test1() {
        System.out.println("test 1");
    }

    @Test
    public void test2() {
        System.out.println("test 2");
    }

    @BeforeAll
    public void BeforeAll(){
        System.out.println("Before all start");
    }

    @BeforeEach
    public void BeforeEach(){
        System.out.println("Before each start");
    }

    @AfterAll
    public void AfterAll (){
        System.out.println("After all start");
    }

    @AfterEach
    public void AfterEach(){
        System.out.println("After each start");
    }
}
