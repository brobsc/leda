public class A02 {
  public interface I1 {
    public abstract void m3(String x);
  }

  public abstract class A implements I1 {
    public abstract void m1(int x);
    public abstract void m2(double x);
  }

  public abstract class B extends A {
    public abstract void m1();
    public void m2(double x) {
      System.out.println("m2 executado");
    }
  }

  public interface I2 {
    public abstract void m4();
  }

  public abstract class C extends A implements I2 {
    public void m1(int x) {
      System.out.println("m1 executado");
    }
  }

  public class E extends C {
    @Override
    public void m3(String x) {
      System.out.println("m3 executado");
    }

    @Override
    public void m2(double x) {
      System.out.println("m2 executado");
    }

    @Override
    public void m4() {
      System.out.println("m4 executado");
    }
  }

  public class D extends B {
    @Override
    public void m3(String x) {
      System.out.println("m3 executado");
    }

    @Override
    public void m1(int x) {
      System.out.println("m1 executado");
    }

    @Override
    public void m1() {
      System.out.println("m1 executado");
    }
  }
}
