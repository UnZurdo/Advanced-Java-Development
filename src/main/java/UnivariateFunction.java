package main.java;

public interface UnivariateFunction<T extends Number> {
  T apply(T x);
}
